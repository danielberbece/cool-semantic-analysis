package cool.compiler.ast;

import cool.compiler.visitors.ASTVisitor;
import org.antlr.v4.runtime.Token;

public class ASTIfExpr extends ASTExpr {
    public Token ifToken;
    public ASTExpr ifExpr;
    public ASTExpr thenExpr;
    public ASTExpr elseExpr;

    public ASTIfExpr(Token ifToken, ASTExpr ifExpr, ASTExpr thenExpr, ASTExpr elseExpr) {
        this.token = ifToken;
        this.ifToken = ifToken;
        this.ifExpr = ifExpr;
        this.thenExpr = thenExpr;
        this.elseExpr = elseExpr;
    }

    @Override
    public <T> T accept(ASTVisitor<T> visitor) {
        return visitor.visit(this);
    }

    @Override
    public String toString() {
        return ifToken.getText();
    }
}
