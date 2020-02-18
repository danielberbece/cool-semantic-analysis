package cool.compiler.visitors;

import cool.compiler.Utils;
import cool.compiler.ast.*;
import cool.structures.Scope;
import cool.structures.SymbolTable;

import static cool.compiler.Utils.OBJECT_TYPE;

public class HierarchyVisitor implements ASTVisitor<Void> {
    @Override
    public Void visit(ASTProg prog) {
        for (ASTClass c : prog.classes) {
            c.accept(this);
        }
        return null;
    }

    @Override
    public Void visit(ASTClass class_) {
        if (class_.inheritType != null && SymbolTable.globals.lookup(class_.inheritType.getText(), Scope.CLASS_ID) == null
                && !class_.inheritType.getText().contentEquals(OBJECT_TYPE)) {
            Utils.showError(class_.context, class_.inheritType,"Class " + class_.classType.getText() + " has undefined parent " + class_.inheritType.getText());
        } else if (class_.inheritType != null) {
            class_.setParent(SymbolTable.idToAstClassMap.get(class_.inheritType.getText()));
        }
        return null;
    }

    @Override
    public Void visit(ASTAttribute attribute) {
        return null;
    }

    @Override
    public Void visit(ASTMethod method) {
        return null;
    }

    @Override
    public Void visit(ASTFormal astFormal) {
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
        return null;
    }

    @Override
    public Void visit(ASTMinus astMinus) {
        return null;
    }

    @Override
    public Void visit(ASTMult astMult) {
        return null;
    }

    @Override
    public Void visit(ASTDiv astDiv) {
        return null;
    }

    @Override
    public Void visit(ASTComplement astComplement) {
        return null;
    }

    @Override
    public Void visit(ASTNotExpr astNotExpr) {
        return null;
    }

    @Override
    public Void visit(ASTLessEqualExpr astLessEqualExpr) {
        return null;
    }

    @Override
    public Void visit(ASTEqualExpr astEqualExpr) {
        return null;
    }

    @Override
    public Void visit(ASTLessThanExpr astLessThanExpr) {
        return null;
    }

    @Override
    public Void visit(ASTAssignExpr astAssignExpr) {
        return null;
    }

    @Override
    public Void visit(ASTIsvoidExpr astIsvoidExpr) {
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
        return null;
    }

    @Override
    public Void visit(ASTWhileExpr astWhileExpr) {
        return null;
    }

    @Override
    public Void visit(ASTLetExpr astLetExpr) {
        return null;
    }

    @Override
    public Void visit(ASTBlockExpr astBlockExpr) {
        return null;
    }

    @Override
    public Void visit(ASTCaseExpr astCaseExpr) {
        return null;
    }

    @Override
    public Void visit(ASTLocalVar astLocalVar) {
        return null;
    }
}
