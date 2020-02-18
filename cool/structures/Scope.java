package cool.structures;

public interface Scope {
    public static final int CLASS_ID = 1;
    public static final int CLASS_METHOD = 2;

    public boolean add(Symbol sym);

    public Symbol lookup(String str, int symbolClass);

    public Symbol lookupCurrent(String str, int symbolClass);

    public void setParent(Scope parent);

    public Scope getParent();
}
