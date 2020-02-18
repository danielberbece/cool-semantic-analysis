package cool.compiler.ast;

import cool.compiler.visitors.ASTVisitor;
import org.antlr.v4.runtime.Token;

import java.util.List;

public class ASTImplicitDispatchExpr extends ASTExpr {
    public List<ASTExpr> args;

    public ASTImplicitDispatchExpr(Token symbol, List<ASTExpr> exprs) {
        this.token = symbol;
        this.args = exprs;
    }

    @Override
    public <T> T accept(ASTVisitor<T> visitor) {
        return visitor.visit(this);
    }

    @Override
    public String toString() {
        return "implicit dispatch";
    }
}
