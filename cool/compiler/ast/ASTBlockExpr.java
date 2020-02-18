package cool.compiler.ast;

import cool.compiler.visitors.ASTVisitor;
import org.antlr.v4.runtime.Token;

import java.util.List;

public class ASTBlockExpr extends ASTExpr {
    public List<ASTExpr> exprs;

    public ASTBlockExpr(Token token, List<ASTExpr> exprs) {
        this.token = token;
        this.exprs = exprs;
    }

    @Override
    public <T> T accept(ASTVisitor<T> visitor) {
        return visitor.visit(this);
    }

    @Override
    public String toString() {
        return "block";
    }
}
