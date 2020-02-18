package cool.compiler.ast;

import cool.compiler.visitors.ASTVisitor;
import cool.structures.IdSymbol;
import cool.structures.Symbol;
import org.antlr.v4.runtime.Token;

public class ASTAttribute extends ASTFeature {
    public Token id;
    public Token type;
    public ASTExpr expr;
    public Symbol symbol;
    public boolean hasError = false;

    public ASTAttribute(Token id, Token type, ASTExpr expr) {
        this.id = id;
        this.type = type;
        this.expr = expr;
        symbol = new IdSymbol(id.getText(), type.getText());
    }

    @Override
    public <T> T accept(ASTVisitor<T> visitor) {
        return visitor.visit(this);
    }

    @Override
    public String toString() {
        return "attribute";
    }
}
