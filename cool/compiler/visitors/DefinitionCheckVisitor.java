package cool.compiler.visitors;

import cool.compiler.Utils;
import cool.compiler.ast.*;
import cool.structures.*;
import org.antlr.v4.runtime.Token;

import static cool.compiler.Utils.*;

public class DefinitionCheckVisitor implements ASTVisitor<Void> {
    private ASTClass currentClass = null;
    Scope currentScope = null;

    @Override
    public Void visit(ASTProg prog) {
//        currentScope = SymbolTable.globals;
        for (ASTClass c : prog.classes) {
            c.accept(this);
        }
        return null;
    }

    @Override
    public Void visit(ASTClass class_) {
        currentClass = class_;

        if (class_.classType.getText().contentEquals(SELF_TYPE)) {
            Utils.showError(currentClass.context, class_.classType,"Class has illegal name SELF_TYPE");
        } else if (SymbolTable.globals.lookup(class_.classType.getText(), Scope.CLASS_ID) != null) {
            Utils.showError(currentClass.context, class_.classType,"Class " + class_.classType.getText() +" is redefined");
        }

        if (class_.inheritType != null && (class_.inheritType.getText().contentEquals(SELF_TYPE)
                || class_.inheritType.getText().contentEquals(INT_TYPE)
                || class_.inheritType.getText().contentEquals(BOOL_TYPE)
                || class_.inheritType.getText().contentEquals(STRING_TYPE)
                || class_.inheritType.getText().contentEquals(SELF_TYPE))
        ) {
            Utils.showError(currentClass.context, class_.inheritType,"Class " + class_.classType.getText() + " has illegal parent " + class_.inheritType.getText());
        }

        if (class_.inheritType == null) {
            SymbolTable.addType(class_.classType.getText(), OBJECT_TYPE);
        } else {
            SymbolTable.addType(class_.classType.getText(), class_.inheritType.getText());
        }

        SymbolTable.globals.add(class_.symbol);
        SymbolTable.idToAstClassMap.put(class_.classType.getText(), class_);

        currentScope = class_;
        for (ASTFeature feature: class_.features) {
            feature.accept(this);
        }

//        class_.setParent(savedParentScope);
        currentClass = null;
        currentScope = null;
        return null;
    }

    @Override
    public Void visit(ASTAttribute attribute) {
        if (attribute.id.getText().contentEquals(SELF)) {
            Utils.showError(currentClass.context, attribute.id,
                    "Class " + currentClass.classType.getText() + " has attribute with illegal name self");
            attribute.hasError = true;
        }

        Symbol s = currentScope.lookup(attribute.id.getText(), Scope.CLASS_ID);
        if (s instanceof IdSymbol) {
            Utils.showError(currentClass.context, attribute.id,
                    "Class " + currentClass.classType.getText() + " redefines attribute " + attribute.id.getText()
            );
            attribute.hasError = true;
        }

        if (attribute.expr != null) {
            attribute.expr.accept(this);
        }

        currentScope.add(attribute.symbol);
        return null;
    }

    @Override
    public Void visit(ASTMethod method) {
        boolean hasError = false;
        if (currentScope.lookup(method.id.getText(), Scope.CLASS_METHOD) != null) {
            Utils.showError(currentClass.context, method.id,
                    "Class " + currentClass.classType.getText() + " redefines method " + method.id.getText()
            );
            hasError = true;
        }

        Scope parentScope = currentScope;
        currentScope = method;
        currentScope.setParent(parentScope);

        for (ASTFormal formal: method.formals) {
            formal.accept(this);
        }

        method.expr.accept(this);

        currentScope = parentScope;
        if (!hasError) {
            currentScope.add(method.symbol);
        }
        return null;
    }

    @Override
    public Void visit(ASTFormal astFormal) {
        if (astFormal.name.getText().contentEquals(SELF)) {
            Utils.showError(currentClass.context, astFormal.name,
                    "Method " + ((ASTMethod)currentScope).id.getText() + " of class " + currentClass.classType.getText() +
                            " has formal parameter with illegal name self");
        }

        if (currentScope.lookupCurrent(astFormal.name.getText(), Scope.CLASS_ID) != null) {
            Utils.showError(currentClass.context, astFormal.name,
                    "Method " + ((ASTMethod)currentScope).id.getText() + " of class " + currentClass.classType.getText() +
                    " redefines formal parameter " + astFormal.name.getText());
        }

        if (astFormal.type.getText().contentEquals(SELF_TYPE)) {
            Utils.showError(currentClass.context, astFormal.type,
                    "Method " + ((ASTMethod)currentScope).id.getText() + " of class " + currentClass.classType.getText() +
                            " has formal parameter " + astFormal.name.getText() + " with illegal type SELF_TYPE");
        }

        currentScope.add(astFormal.symbol);
        return null;
    }

    @Override
    public Void visit(ASTInt astInt) {
        return null;
    }

    @Override
    public Void visit(ASTId astId) {
        return null;
    }

    @Override
    public Void visit(ASTBool astBool) {
        return null;
    }

    @Override
    public Void visit(ASTString astString) {
        return null;
    }

    @Override
    public Void visit(ASTPlus astPlus) {
        astPlus.leftExpr.accept(this);
        astPlus.rightExpr.accept(this);
        return null;
    }

    @Override
    public Void visit(ASTMinus astMinus) {
        astMinus.leftExpr.accept(this);
        astMinus.rightExpr.accept(this);
        return null;
    }

    @Override
    public Void visit(ASTMult astMult) {
        astMult.leftExpr.accept(this);
        astMult.rightExpr.accept(this);
        return null;
    }

    @Override
    public Void visit(ASTDiv astDiv) {
        astDiv.leftExpr.accept(this);
        astDiv.rightExpr.accept(this);
        return null;
    }

    @Override
    public Void visit(ASTComplement astComplement) {
        astComplement.expr.accept(this);
        return null;
    }

    @Override
    public Void visit(ASTNotExpr astNotExpr) {
        astNotExpr.expr.accept(this);
        return null;
    }

    @Override
    public Void visit(ASTLessEqualExpr astLessEqualExpr) {
        astLessEqualExpr.leftExpr.accept(this);
        astLessEqualExpr.rightExpr.accept(this);
        return null;
    }

    @Override
    public Void visit(ASTEqualExpr astEqualExpr) {
        astEqualExpr.leftExpr.accept(this);
        astEqualExpr.rightExpr.accept(this);
        return null;
    }

    @Override
    public Void visit(ASTLessThanExpr astLessThanExpr) {
        astLessThanExpr.leftExpr.accept(this);
        astLessThanExpr.rightExpr.accept(this);
        return null;
    }

    @Override
    public Void visit(ASTAssignExpr astAssignExpr) {
        if (astAssignExpr.id.getText().contentEquals(SELF)) {
            Utils.showError(currentClass.context, astAssignExpr.id, "Cannot assign to self");
        }
        astAssignExpr.expr.accept(this);
        return null;
    }

    @Override
    public Void visit(ASTIsvoidExpr astIsvoidExpr) {
        astIsvoidExpr.expr.accept(this);
        return null;
    }

    @Override
    public Void visit(ASTNewExpr astNewExpr) {
        return null;
    }

    @Override
    public Void visit(ASTImplicitDispatchExpr astImplicitDispatchExpr) {
        return null;
    }

    @Override
    public Void visit(ASTExplicitDispatch astExplicitDispatch) {
        return null;
    }

    @Override
    public Void visit(ASTIfExpr astIfExpr) {
        astIfExpr.ifExpr.accept(this);
        astIfExpr.thenExpr.accept(this);
        astIfExpr.elseExpr.accept(this);
        return null;
    }

    @Override
    public Void visit(ASTWhileExpr astWhileExpr) {
        astWhileExpr.conditionExpr.accept(this);
        astWhileExpr.bodyExpr.accept(this);
        return null;
    }

    @Override
    public Void visit(ASTLetExpr astLetExpr) {
        astLetExpr.setParent(currentScope);
        currentScope = astLetExpr;

        for (ASTLocalVar localVar: astLetExpr.locals) {
            localVar.accept(this);
        }

        astLetExpr.bodyExpr.accept(this);

        currentScope = astLetExpr.getParent();
        return null;
    }

    @Override
    public Void visit(ASTBlockExpr astBlockExpr) {
        for (ASTExpr e: astBlockExpr.exprs) {
            e.accept(this);
        }
        return null;
    }

    @Override
    public Void visit(ASTCaseExpr astCaseExpr) {
        for (int i = 0; i < astCaseExpr.ids.size(); i++) {
            processCaseEntry(astCaseExpr.ids.get(i), astCaseExpr.types.get(i), astCaseExpr.exprs.get(i));
        }
        return null;
    }

    @Override
    public Void visit(ASTLocalVar astLocalVar) {
        if (astLocalVar.id.getText().contentEquals(SELF)) {
            Utils.showError(currentClass.context, astLocalVar.id, "Let variable has illegal name self");
        }

        if (astLocalVar.expr != null) {
            astLocalVar.expr.accept(this);
        }

        return null;
    }

    void processCaseEntry(Token id, Token type, ASTExpr expr) {
        expr.accept(this);
    }
}
