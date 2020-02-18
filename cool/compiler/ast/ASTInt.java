package cool.compiler.ast;

import cool.compiler.visitors.ASTVisitor;
import org.antlr.v4.runtime.Token;

public class ASTInt extends ASTExpr {
    public ASTInt(Token token) {
        this.token = token;
    }

    @Override
    public <T> T accept(ASTVisitor<T> visitor) {
        return visitor.visit(this);
    }
}
