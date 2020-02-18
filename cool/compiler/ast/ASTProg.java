package cool.compiler.ast;

import cool.compiler.visitors.ASTVisitor;

import java.util.ArrayList;
import java.util.List;

public class ASTProg extends ASTNode {
    public ArrayList<ASTClass> classes;

    public ASTProg(List<ASTClass> classes) {
        this.classes = new ArrayList<>(classes);
    }

    public <T> T accept(ASTVisitor<T> visitor) {
        return visitor.visit(this);
    }

    @Override
    public String toString() {
        return "program";
    }
}
