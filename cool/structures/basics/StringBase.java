package cool.structures.basics;

import cool.compiler.Utils;
import cool.compiler.ast.ASTClass;
import cool.compiler.ast.ASTFeature;
import cool.compiler.ast.ASTMethod;
import cool.structures.MethodSymbol;
import org.antlr.v4.runtime.ParserRuleContext;
import org.antlr.v4.runtime.Token;
import org.antlr.v4.runtime.misc.Pair;

import java.util.ArrayList;
import java.util.List;

import static cool.compiler.Utils.*;

public class StringBase extends ASTClass {
    public StringBase(ParserRuleContext context, Token classType, Token inheritType, List<ASTFeature> features) {
        super(context, classType, inheritType, features);
        ArrayList<Pair<String,String>> types = new ArrayList<>();
        types.add(new Pair<>("s","String"));
        add(new MethodSymbol(new ASTMethod(Utils.createPlainToken("concat"),
                Utils.createFormals(types),
                Utils.createPlainToken(STRING_TYPE),
                null)));
        types.clear();
        types.add(new Pair<>("i", "Int"));
        types.add(new Pair<>("l", "Int"));
        add(new MethodSymbol(new ASTMethod(Utils.createPlainToken("substr"),
                Utils.createFormals(types),
                Utils.createPlainToken(STRING_TYPE),
                null)));
        add(new MethodSymbol(new ASTMethod(Utils.createPlainToken("length"),
                Utils.createFormals(null),
                Utils.createPlainToken(INT_TYPE),
                null)));
    }

    public static StringBase create() {
        return new StringBase(null, Utils.createPlainToken(STRING_TYPE), Utils.createPlainToken(OBJECT_TYPE), null);
    }
}
