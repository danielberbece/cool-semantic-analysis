package cool.compiler.ast;

import cool.compiler.visitors.ASTVisitor;
import cool.structures.Symbol;
import org.antlr.v4.runtime.Token;

public class ASTLocalVar extends ASTExpr {
    public Token id;
    public Token type;
    public ASTExpr expr;
    public Symbol symbol;

    public ASTLocalVar(Token id, Token type, ASTExpr expr) {
        token = id;
        this.id = id;
        this.type = type;
        this.expr = expr;
        symbol = new Symbol(id.getText(), type.getText());
    }


    @Override
    public <T> T accept(ASTVisitor<T> visitor) {
        return visitor.visit(this);
    }

    @Override
    public String toString() {
        return "local";
    }
}
