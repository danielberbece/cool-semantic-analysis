package cool.compiler.ast;

import cool.compiler.visitors.ASTVisitor;
import org.antlr.v4.runtime.Token;

public class ASTComplement extends ASTExpr {
    public ASTExpr expr;

    public ASTComplement(Token token, ASTExpr expr) {
        this.token = token;
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
