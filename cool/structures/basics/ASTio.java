package cool.structures.basics;

import cool.compiler.Utils;
import cool.compiler.ast.ASTClass;
import cool.compiler.ast.ASTFeature;
import cool.compiler.ast.ASTMethod;
import cool.structures.MethodSymbol;
import org.antlr.v4.runtime.CharStream;
import org.antlr.v4.runtime.ParserRuleContext;
import org.antlr.v4.runtime.Token;
import org.antlr.v4.runtime.TokenSource;
import org.antlr.v4.runtime.misc.Pair;

import java.util.ArrayList;
import java.util.List;

import static cool.compiler.Utils.*;

public class ASTio extends ASTClass {
    public ASTio(ParserRuleContext context, Token classType, Token inheritType, List<ASTFeature> features) {
        super(context, classType, inheritType, features);
        ArrayList<Pair<String, String>> types = new ArrayList<>();
        types.add(new Pair<>("x", "String"));
        add(new MethodSymbol(new ASTMethod(Utils.createPlainToken("out_string"),
                Utils.createFormals(types),
                Utils.createPlainToken(SELF_TYPE),
                null)));
        types.clear();
        types.add(new Pair<>("x","Int"));
        add(new MethodSymbol(new ASTMethod(Utils.createPlainToken("out_int"),
                Utils.createFormals(types),
                Utils.createPlainToken(SELF_TYPE),
                null)));
        add(new MethodSymbol(new ASTMethod(Utils.createPlainToken("in_string"),
                Utils.createFormals(null),
                Utils.createPlainToken(STRING_TYPE),
                null)));
        add(new MethodSymbol(new ASTMethod(Utils.createPlainToken("in_int"),
                Utils.createFormals(null),
                Utils.createPlainToken(INT_TYPE),
                null)));
    }

    public static ASTio create() {
        return new ASTio(null, Utils.createPlainToken(IO_TYPE),Utils.createPlainToken(OBJECT_TYPE), null);
    }
}
