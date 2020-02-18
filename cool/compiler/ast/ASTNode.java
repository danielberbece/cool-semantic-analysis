package cool.compiler.ast;

import cool.compiler.visitors.ASTVisitor;

public abstract class ASTNode {
    public abstract <T> T accept(ASTVisitor<T> visitor);
}