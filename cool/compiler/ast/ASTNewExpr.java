package cool.compiler.ast;

import cool.compiler.visitors.ASTVisitor;
import org.antlr.v4.runtime.Token;

public class ASTNewExpr extends ASTExpr {
    public Token newToken;
    public Token typeToken;

    public ASTNewExpr(Token newToken, Token typeToken) {
        this.newToken = newToken;
        this.typeToken = typeToken;
        token = newToken;
    }

    @Override
    public <T> T accept(ASTVisitor<T> visitor) {
        return visitor.visit(this);
    }

    @Override
    public String toString() {
        return newToken.getText();
    }
}
