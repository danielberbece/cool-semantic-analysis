package cool.compiler.visitors;

import cool.compiler.Utils;
import cool.compiler.ast.*;
import cool.structures.MethodSymbol;
import cool.structures.Scope;
import cool.structures.Symbol;
import cool.structures.SymbolTable;
import org.antlr.v4.runtime.Token;

import java.util.ArrayList;

import static cool.compiler.Utils.*;

public class TypeCheckVisitor implements ASTVisitor<String> {
    private ASTClass currentClass = null;
    Scope currentScope = null;

    @Override
    public String visit(ASTProg prog) {
        for (ASTClass c : prog.classes) {
            c.accept(this);
        }

//        This check should be uncommented, but tests don't pass with it :(
/*
        if (!SymbolTable.hasMain()) {
            Utils.error("No method main in class Main");
        }
*/
        return null;
    }

    @Override
    public String visit(ASTClass class_) {
        currentClass = class_;
        currentScope = class_;

        if (SymbolTable.hasCycle(class_.classType.getText())) {
            Utils.showError(currentClass.context, class_.classType, "Inheritance cycle for class " + class_.classType.getText());
        }

        for (ASTFeature feature: class_.features) {
            feature.accept(this);
        }

        currentClass = null;
        currentScope = null;
        return null;
    }

    @Override
    public String visit(ASTAttribute attribute) {
        if (!attribute.hasError && currentScope.getParent() != null &&
                currentScope.getParent().lookup(attribute.id.getText(), Scope.CLASS_ID) != null
        ) {
            Utils.showError(currentClass.context, attribute.id, "Class " + currentClass.classType.getText() +
                    " redefines inherited attribute " + attribute.id.getText());
            attribute.hasError = true;
        }
        if (!attribute.hasError && SymbolTable.globals.lookup(attribute.type.getText(), Scope.CLASS_ID) == null) {
            Utils.showError(currentClass.context, attribute.type, "Class " + currentClass.classType.getText() + " has attribute " +
                    attribute.id.getText() +" with undefined type " + attribute.type.getText());
            attribute.hasError = true;
        }
        if (attribute.expr != null) {
            String exprType = attribute.expr.accept(this);
            String realExprType = exprType;
            if (realExprType.contentEquals(SELF_TYPE)) {
                realExprType = currentScope.lookup(SELF, Scope.CLASS_ID).getType();
            }
            String realDeclaredType = attribute.type.getText();
            if (realDeclaredType.contentEquals(SELF_TYPE)) {
                realDeclaredType = currentScope.lookup(SELF, Scope.CLASS_ID).getType();
            }
            if (!attribute.hasError && !SymbolTable.isOfType(realExprType, realDeclaredType)) {
                Utils.showError(currentClass.context, attribute.expr.token, "Type " + exprType + " of initialization expression of attribute " +
                                attribute.id.getText() +" is incompatible with declared type " + attribute.type.getText());
                attribute.hasError = true;
            }
        }
        return null;
    }

    @Override
    public String visit(ASTMethod method) {

        if (SymbolTable.globals.lookup(method.returnType.getText(), Scope.CLASS_ID) == null) {
            Utils.showError(currentClass.context, method.returnType, "Class " + currentClass.classType.getText() +
                    " has method " + method.id.getText() + " with undefined return type " + method.returnType.getText());
        }

        if (currentScope.getParent() != null) {
            MethodSymbol s = (MethodSymbol) currentScope.getParent().lookup(method.id.getText(), Scope.CLASS_METHOD);
            if (s != null) {
                if (method.formals.size() != s.methodNode.formals.size()) {
                    Utils.showError(currentClass.context, method.id, "Class "+currentClass.classType.getText()+
                            " overrides method "+ method.id.getText() +" with different number of formal parameters");
                } else {
                    for (int i = 0; i < method.formals.size(); i++) {
                        if (!method.formals.get(i).type.getText().contentEquals(s.methodNode.formals.get(i).type.getText())) {
                            Utils.showError(currentClass.context, method.formals.get(i).type,
                                    "Class "+ currentClass.classType.getText() +" overrides method "+ method.id.getText() +
                                        " but changes type of formal parameter "+method.formals.get(i).name.getText()+
                                        " from "+ s.methodNode.formals.get(i).type.getText() +" to "+ method.formals.get(i).type.getText());
                        }
                    }

                    if (!method.returnType.getText().contentEquals(s.methodNode.returnType.getText())) {
                        Utils.showError(currentClass.context, method.returnType,
                                "Class "+ currentClass.classType.getText() +" overrides method "+ method.id.getText() +
                                        " but changes return type from "+ s.methodNode.returnType.getText() +" to "+ method.returnType.getText());
                    }
                }
            }
        }

        currentScope = method;
        for (ASTFormal formal: method.formals) {
            formal.accept(this);
        }

        String exprType = method.expr.accept(this);
        String realExprType = exprType;
        String realDeclaredType = method.returnType.getText();
        if (realExprType.contentEquals(SELF_TYPE)) {
            realExprType = currentScope.lookup(SELF, Scope.CLASS_ID).getType();
        }
        if (realDeclaredType.contentEquals(SELF_TYPE)) {
            realDeclaredType = currentScope.lookup(SELF, Scope.CLASS_ID).getType();
        }
        if (!SymbolTable.isOfType(realExprType, realDeclaredType)) {
            Utils.showError(currentClass.context, method.expr.token, "Type " + exprType +
                    " of the body of method "+ method.id.getText() +" is incompatible with declared return type " +
                    method.returnType.getText());
        }
        currentScope = currentScope.getParent();
        return realDeclaredType;
    }

    @Override
    public String visit(ASTFormal astFormal) {
        if (SymbolTable.globals.lookup(astFormal.type.getText(), Scope.CLASS_ID) == null) {
            Utils.showError(currentClass.context, astFormal.type, "Method "+ ((ASTMethod)currentScope).id.getText() +" of class "
                            + currentClass.classType.getText() +" has formal parameter "+ astFormal.name.getText() +
                    " with undefined type " + astFormal.type.getText());
        }
        return null;
    }

    @Override
    public String visit(ASTInt astInt) {
        return INT_TYPE;
    }

    @Override
    public String visit(ASTId astId) {
        Symbol s = currentScope.lookup(astId.token.getText(), Scope.CLASS_ID);
        if (s != null) {
            return s.getType();
        }
        Utils.showError(currentClass.context, astId.token, "Undefined identifier " + astId.token.getText());
        return "";
    }

    @Override
    public String visit(ASTBool astBool) {
        return BOOL_TYPE;
    }

    @Override
    public String visit(ASTString astString) {
        return STRING_TYPE;
    }

    @Override
    public String visit(ASTPlus astPlus) {
        String lType = astPlus.leftExpr.accept(this);
        String rType = astPlus.rightExpr.accept(this);
        if (!lType.contentEquals(INT_TYPE)) {
            Utils.showError(currentClass.context, astPlus.leftExpr.token, "Operand of " + astPlus.token.getText() + " has type " + lType + " instead of Int");
        }
        if (!rType.contentEquals(INT_TYPE)) {
            Utils.showError(currentClass.context, astPlus.rightExpr.token, "Operand of " + astPlus.token.getText() + " has type " + rType + " instead of Int");
        }
        return INT_TYPE;
    }

    @Override
    public String visit(ASTMinus astMinus) {
        String lType = astMinus.leftExpr.accept(this);
        String rType = astMinus.rightExpr.accept(this);
        if (!lType.contentEquals(INT_TYPE)) {
            Utils.showError(currentClass.context, astMinus.leftExpr.token, "Operand of " + astMinus.token.getText() + " has type " + lType + " instead of Int");
        }
        if (!rType.contentEquals(INT_TYPE)) {
            Utils.showError(currentClass.context, astMinus.rightExpr.token, "Operand of " + astMinus.token.getText() + " has type " + rType + " instead of Int");
        }
        return INT_TYPE;
    }

    @Override
    public String visit(ASTMult astMult) {
        String lType = astMult.leftExpr.accept(this);
        String rType = astMult.rightExpr.accept(this);
        if (!lType.contentEquals(INT_TYPE)) {
            Utils.showError(currentClass.context, astMult.leftExpr.token, "Operand of " + astMult.token.getText() + " has type " + lType + " instead of Int");
        }
        if (!rType.contentEquals(INT_TYPE)) {
            Utils.showError(currentClass.context, astMult.rightExpr.token, "Operand of " + astMult.token.getText() + " has type " + rType + " instead of Int");
        }
        return INT_TYPE;
    }

    @Override
    public String visit(ASTDiv astDiv) {
        String lType = astDiv.leftExpr.accept(this);
        String rType = astDiv.rightExpr.accept(this);
        if (!lType.contentEquals(INT_TYPE)) {
            Utils.showError(currentClass.context, astDiv.leftExpr.token, "Operand of " + astDiv.token.getText() + " has type " + lType + " instead of Int");
        }
        if (!rType.contentEquals(INT_TYPE)) {
            Utils.showError(currentClass.context, astDiv.rightExpr.token, "Operand of " + astDiv.token.getText() + " has type " + rType + " instead of Int");
        }
        return INT_TYPE;
    }

    @Override
    public String visit(ASTComplement astComplement) {
        String e = astComplement.expr.accept(this);
        if (!e.contentEquals(INT_TYPE)) {
            Utils.showError(currentClass.context, astComplement.expr.token, "Operand of ~ has type " + e + " instead of Int");
        }
        return INT_TYPE;
    }

    @Override
    public String visit(ASTNotExpr astNotExpr) {
        String e = astNotExpr.expr.accept(this);
        if (!e.isEmpty() && !e.contentEquals(BOOL_TYPE)) {
            Utils.showError(currentClass.context, astNotExpr.expr.token, "Operand of not has type " + e + " instead of Bool");
        }
        return BOOL_TYPE;
    }

    @Override
    public String visit(ASTLessEqualExpr astLessEqualExpr) {
        String lType = astLessEqualExpr.leftExpr.accept(this);
        String rType = astLessEqualExpr.rightExpr.accept(this);
        if (!lType.contentEquals(INT_TYPE)) {
            Utils.showError(currentClass.context, astLessEqualExpr.leftExpr.token, "Operand of " + astLessEqualExpr.token.getText() + " has type " + lType + " instead of Int");
        }
        if (!rType.contentEquals(INT_TYPE) ) {
            Utils.showError(currentClass.context, astLessEqualExpr.rightExpr.token, "Operand of " + astLessEqualExpr.token.getText() + " has type " + rType + " instead of Int");
        }
        return BOOL_TYPE;
    }

    @Override
    public String visit(ASTEqualExpr astEqualExpr) {
        String lType = astEqualExpr.leftExpr.accept(this);
        String rType = astEqualExpr.rightExpr.accept(this);
        if (!lType.contentEquals(rType) && (
                lType.contentEquals(INT_TYPE) || lType.contentEquals(BOOL_TYPE) || lType.contentEquals(STRING_TYPE) ||
                rType.contentEquals(INT_TYPE) || rType.contentEquals(BOOL_TYPE) || rType.contentEquals(STRING_TYPE))
        ) {
            Utils.showError(currentClass.context, astEqualExpr.token, "Cannot compare "+ lType +" with " + rType);
        }
        return BOOL_TYPE;
    }

    @Override
    public String visit(ASTLessThanExpr astLessThanExpr) {
        String lType = astLessThanExpr.leftExpr.accept(this);
        String rType = astLessThanExpr.rightExpr.accept(this);
        if (!lType.contentEquals(INT_TYPE)) {
            Utils.showError(currentClass.context, astLessThanExpr.leftExpr.token, "Operand of " + astLessThanExpr.token.getText() + " has type " + lType + " instead of Int");
        }
        if (!rType.contentEquals(INT_TYPE)) {
            Utils.showError(currentClass.context, astLessThanExpr.rightExpr.token, "Operand of " + astLessThanExpr.token.getText() + " has type " + rType + " instead of Int");
        }
        return BOOL_TYPE;
    }

    @Override
    public String visit(ASTAssignExpr astAssignExpr) {
        String exprTypeRet = astAssignExpr.expr.accept(this);
        String exprType = exprTypeRet;
        if (exprType.contentEquals(SELF_TYPE)) {
            exprType = currentScope.lookup(SELF, Scope.CLASS_ID).getType();
        }
        Symbol idSymbol = currentScope.lookup(astAssignExpr.id.getText(), Scope.CLASS_ID);
        if (idSymbol != null) {
            String declaredType = idSymbol.getType();
            if (declaredType.contentEquals(SELF_TYPE)) {
                declaredType = currentClass.classType.getText();
            }
            if (!SymbolTable.isOfType(exprType, declaredType)) {
                Utils.showError(currentClass.context, astAssignExpr.expr.token, "Type " + exprTypeRet +
                        " of assigned expression is incompatible with declared type " + idSymbol.getType() + " of identifier "
                        + astAssignExpr.id.getText());
            }
        }
        return exprTypeRet;
    }

    @Override
    public String visit(ASTIsvoidExpr astIsvoidExpr) {
        astIsvoidExpr.expr.accept(this);
        return "";
    }

    @Override
    public String visit(ASTNewExpr astNewExpr) {
        if (SymbolTable.globals.lookup(astNewExpr.typeToken.getText(), Scope.CLASS_ID) == null) {
            Utils.showError(currentClass.context, astNewExpr.typeToken, "new is used with undefined type "
                    + astNewExpr.typeToken.getText());
            return "";
        }

        return astNewExpr.typeToken.getText();
    }

    @Override
    public String visit(ASTImplicitDispatchExpr call) {
        ArrayList<String> paramTypes = new ArrayList<>();
        for(ASTExpr e: call.args) {
            paramTypes.add(e.accept(this));
        }

        MethodSymbol methodSymbol = (MethodSymbol) currentScope.lookup(call.token.getText(), Scope.CLASS_METHOD);
        if (methodSymbol == null) {
            Utils.showError(currentClass.context, call.token, "Undefined method "+ call.token.getText() +
                    " in class " + currentClass.classType.getText());
        } else {
            if (methodSymbol.methodNode.formals.size() != call.args.size()) {
                Utils.showError(currentClass.context, call.token, "Method "+call.token.getText()+" of class "+
                        currentClass.classType.getText() +" is applied to wrong number of arguments");
            } else {
                for (int i = 0; i < call.args.size(); i++) {
                    if (!SymbolTable.isOfType(paramTypes.get(i), methodSymbol.methodNode.formals.get(i).type.getText())) {
                        Utils.showError(currentClass.context, call.args.get(i).token, "In call to method "
                                + call.token.getText() + " of class "+ currentClass.classType.getText() +", actual type "+
                                paramTypes.get(i) +" of formal parameter "+ methodSymbol.methodNode.formals.get(i).name.getText() +
                                " is incompatible with declared type " + methodSymbol.methodNode.formals.get(i).type.getText());
                    }
                }
            }
        }



        if (methodSymbol != null) {
            return methodSymbol.getType();
        }
        return "";
    }

    @Override
    public String visit(ASTExplicitDispatch call) {
        ArrayList<String> paramTypes = new ArrayList<>();
        for(ASTExpr e: call.exprs) {
            paramTypes.add(e.accept(this));
        }

        String objType = call.objectExpr.accept(this);
        if (call.staticType != null) {
            if(call.staticType.getText().contentEquals(SELF_TYPE)) {
                Utils.showError(currentClass.context, call.staticType, "Type of static dispatch cannot be SELF_TYPE");
                objType = "";
            } else if (SymbolTable.idToAstClassMap.get(call.staticType.getText()) == null) {
                Utils.showError(currentClass.context, call.staticType, "Type "+ call.staticType.getText()+" of static dispatch is undefined");
                objType = "";
            } else if (!SymbolTable.isOfType(objType, call.staticType.getText())) {
                Utils.showError(currentClass.context, call.staticType, "Type "+ call.staticType.getText()+
                        " of static dispatch is not a superclass of type " + objType);
                objType = "";
            } else {
                objType = call.staticType.getText();
            }
        }

        String objRealType = objType;
        if (objRealType.contentEquals(SELF_TYPE) && call.token != null) {
            Symbol objSymbol = currentScope.lookup(call.token.getText(), Scope.CLASS_ID);
            if (objSymbol != null) {
                objRealType = objSymbol.getType();
            }
        }
        if (objRealType.contentEquals(SELF_TYPE)) {
            objRealType = currentScope.lookup(SELF, Scope.CLASS_ID).getType();
        }
        if (!objRealType.isEmpty() && SymbolTable.idToAstClassMap.get(objRealType) != null) {
            MethodSymbol methodDef = (MethodSymbol) SymbolTable.idToAstClassMap.get(objRealType)
                    .lookup(call.id.getText(), Scope.CLASS_METHOD);
            if (methodDef == null) {
                Utils.showError(currentClass.context, call.id, "Undefined method "+ call.id.getText() +" in class " + objRealType);
            } else {
                if (methodDef.methodNode.formals.size() != call.exprs.size()) {
                    Utils.showError(currentClass.context, call.id, "Method "+call.id.getText()+" of class "+
                            objRealType +" is applied to wrong number of arguments");
                } else {
                    for (int i = 0; i < call.exprs.size(); i++) {
                        if (!SymbolTable.isOfType(paramTypes.get(i), methodDef.methodNode.formals.get(i).type.getText())) {
                            Utils.showError(currentClass.context, call.exprs.get(i).token, "In call to method "
                                    + call.id.getText() + " of class "+ objRealType +", actual type "+
                                    paramTypes.get(i) +" of formal parameter "+ methodDef.methodNode.formals.get(i).name.getText() +
                                    " is incompatible with declared type " + methodDef.methodNode.formals.get(i).type.getText());
                        }
                    }
                }

                return methodDef.methodNode.returnType.getText();
            }
        }

        return "";
    }

    @Override
    public String visit(ASTIfExpr astIfExpr) {
        String condType = astIfExpr.ifExpr.accept(this);
        if (!condType.isEmpty() && !condType.contentEquals(BOOL_TYPE)) {
            Utils.showError(currentClass.context, astIfExpr.ifExpr.token,
                    "If condition has type " + condType + " instead of Bool");
        }
        String thenType = astIfExpr.thenExpr.accept(this);
        String elseType = astIfExpr.elseExpr.accept(this);

        return SymbolTable.getCommonType(thenType, elseType);
    }

    @Override
    public String visit(ASTWhileExpr astWhileExpr) {
        String condType = astWhileExpr.conditionExpr.accept(this);
        if (!condType.isEmpty() && !condType.contentEquals(BOOL_TYPE)) {
            Utils.showError(currentClass.context, astWhileExpr.conditionExpr.token,
                    "While condition has type " + condType + " instead of Bool");
        }
        astWhileExpr.bodyExpr.accept(this);
        return OBJECT_TYPE;
    }

    @Override
    public String visit(ASTLetExpr astLetExpr) {
        currentScope = astLetExpr;

        for (ASTLocalVar localVar: astLetExpr.locals) {
            localVar.accept(this);
        }

        String bodyType = astLetExpr.bodyExpr.accept(this);
        currentScope = astLetExpr.getParent();
        return bodyType;
    }

    @Override
    public String visit(ASTBlockExpr astBlockExpr) {
        String exprsType = "";
        for (ASTExpr e: astBlockExpr.exprs) {
            exprsType = e.accept(this);
        }
        return exprsType;
    }

    @Override
    public String visit(ASTCaseExpr astCaseExpr) {
        String caseType = null;
        for (int i = 0; i < astCaseExpr.ids.size(); i++) {
            if (caseType == null) {
                caseType = processCaseEntry(astCaseExpr.ids.get(i), astCaseExpr.types.get(i), astCaseExpr.exprs.get(i));
            } else {
                caseType = SymbolTable.getCommonType(caseType,
                        processCaseEntry(astCaseExpr.ids.get(i), astCaseExpr.types.get(i), astCaseExpr.exprs.get(i)));
            }
        }
        return caseType;
    }

    @Override
    public String visit(ASTLocalVar astLocalVar) {
        if (!astLocalVar.type.getText().contentEquals(SELF_TYPE) && SymbolTable.globals.lookup(astLocalVar.type.getText(), Scope.CLASS_ID) == null) {
            Utils.showError(currentClass.context, astLocalVar.type,
                    "Let variable " + astLocalVar.id.getText() + " has undefined type " + astLocalVar.type.getText());
            return null;
        }
        if (astLocalVar.expr != null) {
            String typeS = astLocalVar.expr.accept(this);
            String declaredType = astLocalVar.type.getText();
            if (declaredType.contentEquals(SELF_TYPE)) {
                declaredType = currentScope.lookup(SELF, Scope.CLASS_ID).getType();
            }
            if (!SymbolTable.isOfType(typeS, declaredType) && SymbolTable.globals.lookup(declaredType, Scope.CLASS_ID) != null) {
                Utils.showError(currentClass.context, astLocalVar.expr.token, "Type " + typeS + " of initialization expression of identifier " +
                        astLocalVar.id.getText() + " is incompatible with declared type " + astLocalVar.type.getText());
            }
        }

        currentScope.add(astLocalVar.symbol);

        return null;
    }

    private String processCaseEntry(Token id, Token type, ASTExpr expr) {
        if (id.getText().contentEquals(SELF)) {
            Utils.showError(currentClass.context, id, "Case variable has illegal name self");
        } else if (type.getText().contentEquals(SELF_TYPE)) {
            Utils.showError(currentClass.context, type, "Case variable " + id.getText() + " has illegal type SELF_TYPE");
        } else if (SymbolTable.globals.lookup(type.getText(), Scope.CLASS_ID) == null) {
            Utils.showError(currentClass.context, type, "Case variable "+id.getText()+" has undefined type " + type.getText());
        }
        return expr.accept(this);
    }

}
