package cool.compiler.ast;

import cool.compiler.visitors.ASTVisitor;
import org.antlr.v4.runtime.Token;

import java.util.List;

public class ASTCaseExpr extends ASTExpr {
    public ASTExpr caseExpr;
    public List<Token> ids;
    public List<Token> types;
    public List<ASTExpr> exprs;

    public ASTCaseExpr(Token token, ASTExpr caseExpr, List<Token> ids, List<Token> types, List<ASTExpr> exprs) {
        this.token = token;
        this.caseExpr = caseExpr;
        this.ids = ids;
        this.types = types;
        this.exprs = exprs;
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
