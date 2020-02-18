package cool.compiler.ast;

import cool.compiler.visitors.ASTVisitor;
import org.antlr.v4.runtime.Token;

public class ASTString extends ASTExpr {
    public String value;

    public ASTString(Token value) {
        token = value;
        String tmp = value.getText().substring(1, value.getText().length() - 1);
        tmp = tmp.replace("\\n", "\n");
        tmp = tmp.replace("\\t", "\t");
        tmp = tmp.replace("\\b", "\b");
        tmp = tmp.replace("\\f", "\f");

        StringBuilder sb = new StringBuilder(tmp);
        for (int i = 0; i < sb.length(); i++) {
            if (sb.charAt(i) == '\\' && i + 1 < sb.length()) {
                sb.deleteCharAt(i);
                i++;
            }
        }
        this.value = sb.toString();
    }

    @Override
    public <T> T accept(ASTVisitor<T> visitor) {
        return visitor.visit(this);
    }
}
