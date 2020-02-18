package cool.compiler.ast;

import cool.compiler.visitors.ASTVisitor;
import cool.structures.MethodSymbol;
import cool.structures.Scope;
import cool.structures.Symbol;
import org.antlr.v4.runtime.Token;

import java.util.LinkedHashMap;
import java.util.List;
import java.util.Map;

public class ASTLetExpr extends ASTExpr implements Scope {
    public List<ASTLocalVar> locals;
    public ASTExpr bodyExpr;
    private Scope parent = null;
    private Map<String, Symbol> idSymbols = new LinkedHashMap<>();
    private Map<String, Symbol> methodSymbols = new LinkedHashMap<>();

    public ASTLetExpr(Token token, List<ASTLocalVar> locals, ASTExpr bodyExpr) {
        this.token = token;
        this.locals = locals;
        this.bodyExpr = bodyExpr;
    }

    @Override
    public <T> T accept(ASTVisitor<T> visitor) {
        return visitor.visit(this);
    }
    @Override
    public String toString() {
        return token.getText();
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
