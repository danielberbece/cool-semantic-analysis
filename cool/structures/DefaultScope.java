package cool.structures;

import java.util.*;

public class DefaultScope implements Scope {

    private Map<String, Symbol> idSymbols = new LinkedHashMap<>();
    private Map<String, Symbol> methodSymbols = new LinkedHashMap<>();

    private Scope parent = null;

    public DefaultScope() {
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
    
    @Override
    public String toString() {
        return idSymbols.values().toString();
    }

}
