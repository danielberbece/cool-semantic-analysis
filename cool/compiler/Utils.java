package cool.compiler;

import cool.compiler.ast.ASTFormal;
import cool.parser.CoolParser;
import cool.structures.SymbolTable;
import org.antlr.v4.runtime.CharStream;
import org.antlr.v4.runtime.ParserRuleContext;
import org.antlr.v4.runtime.Token;
import org.antlr.v4.runtime.TokenSource;
import org.antlr.v4.runtime.misc.Pair;

import java.io.File;
import java.util.ArrayList;
import java.util.List;

public class Utils {
    public static final String BOOL_TYPE = "Bool";
    public static final String INT_TYPE = "Int";
    public static final String OBJECT_TYPE = "Object";
    public static final String SELF_TYPE = "SELF_TYPE";
    public static final String STRING_TYPE = "String";
    public static final String SELF = "self";
    public static final String IO_TYPE = "IO";
    public static final String MAIN_TYPE = "Main";

    /**
     * Displays a semantic error message.
     *
     * @param ctx Used to determine the enclosing class context of this error,
     *            which knows the file name in which the class was defined.
     * @param info Used for line and column information.
     * @param str The error message.
     */
    public static void showError(ParserRuleContext ctx, Token info, String str) {
        while (! (ctx.getParent() instanceof CoolParser.ProgramContext))
            ctx = ctx.getParent();

        String message = "\"" + new File(Compiler.fileNames.get(ctx)).getName()
                + "\", line " + info.getLine()
                + ":" + (info.getCharPositionInLine() + 1)
                + ", Semantic error: " + str;

        System.err.println(message);

        SymbolTable.addError();
    }

    public static void error(String str) {
        String message = "Semantic error: " + str;

        System.err.println(message);

        SymbolTable.addError();
    }

    public static Token createPlainToken(String text) {
        return new Token() {
            @Override
            public String getText() {
                return text;
            }

            @Override
            public int getType() {
                return 0;
            }

            @Override
            public int getLine() {
                return 0;
            }

            @Override
            public int getCharPositionInLine() {
                return 0;
            }

            @Override
            public int getChannel() {
                return 0;
            }

            @Override
            public int getTokenIndex() {
                return 0;
            }

            @Override
            public int getStartIndex() {
                return 0;
            }

            @Override
            public int getStopIndex() {
                return 0;
            }

            @Override
            public TokenSource getTokenSource() {
                return null;
            }

            @Override
            public CharStream getInputStream() {
                return null;
            }
        };
    }

    public static List<ASTFormal> createFormals(List<Pair<String, String>> types) {
        ArrayList<ASTFormal> list = new ArrayList<>();
        if (types == null) {
            return list;
        }

        for (Pair<String, String> formal: types) {
            list.add(new ASTFormal(createPlainToken(formal.a), createPlainToken(formal.b)));
        }

        return list;
    }
}
