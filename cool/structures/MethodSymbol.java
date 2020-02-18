package cool.structures;

import cool.compiler.ast.ASTMethod;

public class MethodSymbol extends Symbol {
    public ASTMethod methodNode;

    public MethodSymbol(ASTMethod methodNode) {
        super(methodNode.id.getText(), methodNode.returnType.getText());
        this.methodNode = methodNode;
    }
}
