package cool.compiler.ast;

import cool.compiler.visitors.ASTVisitor;
import org.antlr.v4.runtime.Token;

public class ASTNotExpr extends ASTExpr {
    public ASTExpr expr;

    public ASTNotExpr(Token symbol, ASTExpr expr) {
        token = symbol;
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
