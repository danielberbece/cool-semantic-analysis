package cool.compiler.ast;

import cool.compiler.visitors.ASTVisitor;
import org.antlr.v4.runtime.Token;

public class ASTLessEqualExpr extends ASTExpr {
    public ASTExpr leftExpr;
    public ASTExpr rightExpr;

    public ASTLessEqualExpr(Token symbol, ASTExpr leftExpr, ASTExpr rightExpr) {
        token = symbol;
        this.leftExpr = leftExpr;
        this.rightExpr = rightExpr;
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
