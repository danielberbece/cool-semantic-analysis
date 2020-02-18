package cool.compiler.ast;

import cool.compiler.visitors.ASTVisitor;
import cool.structures.IdSymbol;
import cool.structures.MethodSymbol;
import cool.structures.Scope;
import cool.structures.Symbol;
import org.antlr.v4.runtime.ParserRuleContext;
import org.antlr.v4.runtime.Token;

import java.util.LinkedHashMap;
import java.util.List;
import java.util.Map;

public class ASTClass extends ASTNode implements Scope {
    public Token classType;
    public Token inheritType;
    public List<ASTFeature> features;
    public ParserRuleContext context;
    public Symbol symbol;
    private Scope parent = null;
    private Map<String, Symbol> idSymbols = new LinkedHashMap<>();
    private Map<String, Symbol> methodSymbols = new LinkedHashMap<>();

    public ASTClass(ParserRuleContext context, Token classType, Token inheritType, List<ASTFeature> features) {
        this.classType = classType;
        this.inheritType = inheritType;
        this.features = features;
        this.context = context;
        symbol = new IdSymbol(classType.getText(), null);
        add(new Symbol("self", classType.getText()));
    }

    public <T> T accept(ASTVisitor<T> visitor) {
        return visitor.visit(this);
    }

    @Override
    public String toString() {
        return "class";
    }

    @Override
    public boolean add(Symbol sym) {
        if (sym instanceof MethodSymbol) {
            if (methodSymbols.containsKey(sym)) {
                return false;
            }
            methodSymbols.put(sym.getName(), sym);
        } else {
            if (idSymbols.containsKey(sym)) {
                return false;
            }
            idSymbols.put(sym.getName(), sym);
        }

        return true;
    }

    @Override
    public Symbol lookup(String name, int symbolClass) {
        Symbol sym = null;
        if (symbolClass == Scope.CLASS_ID) {
            sym = idSymbols.get(name);
        } else if (symbolClass == Scope.CLASS_METHOD) {
            sym = methodSymbols.get(name);
        }
        if (sym != null)
            return sym;

        if (parent != null)
            return parent.lookup(name, symbolClass);

        return null;
    }

    @Override
    public Symbol lookupCurrent(String name, int symbolClass) {
        Symbol sym = null;
        if (symbolClass == Scope.CLASS_ID) {
            sym = idSymbols.get(name);
        } else if (symbolClass == Scope.CLASS_METHOD) {
            sym = idSymbols.get(name);
        }

        if (sym != null)
            return sym;

        return null;
    }

    @Override
    public void setParent(Scope parent) {
        this.parent = parent;
    }

    @Override
    public Scope getParent() {
        return parent;
    }
}
