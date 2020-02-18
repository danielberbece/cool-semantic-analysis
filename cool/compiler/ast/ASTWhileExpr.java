package cool.compiler.ast;

import cool.compiler.visitors.ASTVisitor;
import org.antlr.v4.runtime.Token;

public class ASTWhileExpr extends ASTExpr {
    public ASTExpr conditionExpr;
    public ASTExpr bodyExpr;

    public ASTWhileExpr(Token token, ASTExpr conditionExpr, ASTExpr bodyExpr) {
        this.token = token;
        this.conditionExpr = conditionExpr;
        this.bodyExpr = bodyExpr;
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
