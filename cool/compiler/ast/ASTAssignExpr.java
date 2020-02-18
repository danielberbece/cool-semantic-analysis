package cool.compiler.ast;

import cool.compiler.visitors.ASTVisitor;
import org.antlr.v4.runtime.Token;

public class ASTAssignExpr extends ASTExpr {
    public Token id;
    public ASTExpr expr;

    public ASTAssignExpr(Token token, Token id, ASTExpr expr) {
        this.token = token;
        this.id = id;
        this.expr = expr;
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
