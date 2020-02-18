package cool.compiler.ast;

import cool.compiler.visitors.ASTVisitor;
import org.antlr.v4.runtime.Token;

import java.util.List;

public class ASTExplicitDispatch extends ASTExpr {
    public Token staticType;
    public ASTExpr objectExpr;
    public List<ASTExpr> exprs;
    public Token id;
    public ASTExplicitDispatch(ASTExpr objectExpr, Token staticType, Token id, List<ASTExpr> exprs) {
        this.id = id;
        this.objectExpr = objectExpr;
        token = objectExpr.token;
        this.exprs = exprs;
        this.staticType = staticType;
    }

    @Override
    public <T> T accept(ASTVisitor<T> visitor) {
        return visitor.visit(this);
    }

    @Override
    public String toString() {
        return token.getText();
    }
}
