package cool.structures;

public class TreeNodeType {
    public TreeNodeType parentNode;
    public String parentName;
    public String type;
    public boolean seen = false;

    public TreeNodeType(String nodeId, TreeNodeType parent) {
        this.type = nodeId;
        this.parentNode = parent;
        parentName = parent.type;
    }

    public TreeNodeType(String nodeId, String parentName) {
        this.type = nodeId;
        this.parentName = parentName;
    }
}
