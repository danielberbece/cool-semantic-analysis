package cool.compiler.ast;

import cool.compiler.visitors.ASTVisitor;
import cool.structures.IdSymbol;
import cool.structures.Symbol;
import org.antlr.v4.runtime.Token;

public class ASTFormal extends ASTNode {
    public Token name;
    public Token type;
    public Symbol symbol;

    public ASTFormal(Token name, Token type) {
        this.name = name;
        this.type = type;
        symbol = new IdSymbol(name.getText(), type.getText());
    }

    @Override
    public <T> T accept(ASTVisitor<T> visitor) {
        return visitor.visit(this);
    }

    @Override
    public String toString() {
        return "formal";
    }
}
