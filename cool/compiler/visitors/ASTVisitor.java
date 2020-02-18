package cool.compiler.visitors;

import cool.compiler.ast.*;

public interface ASTVisitor<T> {
    T visit(ASTProg prog);
    T visit(ASTClass class_);
    T visit(ASTAttribute attribute);
    T visit(ASTMethod method);
    T visit(ASTFormal astFormal);
    T visit(ASTInt astInt);
    T visit(ASTId astId);
    T visit(ASTBool astBool);
    T visit(ASTString astString);
    T visit(ASTPlus astPlus);
    T visit(ASTMinus astMinus);
    T visit(ASTMult astMult);
    T visit(ASTDiv astDiv);
    T visit(ASTComplement astComplement);
    T visit(ASTNotExpr astNotExpr);
    T visit(ASTLessEqualExpr astLessEqualExpr);
    T visit(ASTEqualExpr astEqualExpr);
    T visit(ASTLessThanExpr astLessThanExpr);
    T visit(ASTAssignExpr astAssignExpr);
    T visit(ASTIsvoidExpr astIsvoidExpr);
    T visit(ASTNewExpr astNewExpr);
    T visit(ASTImplicitDispatchExpr astImplicitDispatchExpr);
    T visit(ASTExplicitDispatch astExplicitDispatch);
    T visit(ASTIfExpr astIfExpr);
    T visit(ASTWhileExpr astWhileExpr);
    T visit(ASTLetExpr astLetExpr);
    T visit(ASTBlockExpr astBlockExpr);
    T visit(ASTCaseExpr astCaseExpr);
    T visit(ASTLocalVar astLocalVar);
}
