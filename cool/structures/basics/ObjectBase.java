package cool.structures.basics;

import cool.compiler.Utils;
import cool.compiler.ast.ASTClass;
import cool.compiler.ast.ASTFeature;
import cool.compiler.ast.ASTMethod;
import cool.structures.MethodSymbol;
import org.antlr.v4.runtime.ParserRuleContext;
import org.antlr.v4.runtime.Token;

import java.util.List;

import static cool.compiler.Utils.*;

public class ObjectBase extends ASTClass {

    public ObjectBase(ParserRuleContext context, Token classType, Token inheritType, List<ASTFeature> features) {
        super(context, classType, inheritType, features);

        add(new MethodSymbol(new ASTMethod(Utils.createPlainToken("abort"),
                Utils.createFormals(null),
                Utils.createPlainToken(OBJECT_TYPE),
                null)));

        add(new MethodSymbol(new ASTMethod(Utils.createPlainToken("type_name"),
                Utils.createFormals(null),
                Utils.createPlainToken(STRING_TYPE),
                null)));

        add(new MethodSymbol(new ASTMethod(Utils.createPlainToken("copy"),
                Utils.createFormals(null),
                Utils.createPlainToken(SELF_TYPE),
                null)));
    }

    public static ObjectBase create() {
        return new ObjectBase(null, Utils.createPlainToken(OBJECT_TYPE),null, null);
    }
}
