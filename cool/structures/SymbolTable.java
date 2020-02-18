package cool.structures;

import java.util.HashMap;
import java.util.Map;

import cool.compiler.Utils;
import cool.compiler.ast.ASTClass;
import cool.structures.basics.ASTio;
import cool.structures.basics.ObjectBase;
import cool.structures.basics.StringBase;

import static cool.compiler.Utils.*;

public class SymbolTable {
    public static Scope globals;
    
    private static boolean semanticErrors;
    private static Map<String, TreeNodeType> idToNodeMap = new HashMap<>();
    public static Map<String, ASTClass> idToAstClassMap = new HashMap<>();
    
    public static void defineBasicClasses() {
        globals = new DefaultScope();
        semanticErrors = false;

        globals.add(new Symbol(Utils.SELF_TYPE, Utils.SELF_TYPE));

        ASTClass base = ObjectBase.create();
        globals.add(base.symbol);
        idToAstClassMap.put(base.classType.getText(), base);
        addType(OBJECT_TYPE, null);

        ASTClass c = ASTio.create();
        c.setParent(base);
        globals.add(c.symbol);
        idToAstClassMap.put(c.classType.getText(), c);
        addType(IO_TYPE, OBJECT_TYPE);

        c = StringBase.create();
        c.setParent(base);
        globals.add(c.symbol);
        idToAstClassMap.put(c.classType.getText(), c);
        addType(STRING_TYPE, OBJECT_TYPE);


        globals.add(new Symbol(INT_TYPE, INT_TYPE));
        addType(INT_TYPE, OBJECT_TYPE);

        globals.add(new Symbol(Utils.BOOL_TYPE, Utils.BOOL_TYPE));
        addType(BOOL_TYPE, OBJECT_TYPE);
    }

    public static void addError() {
        semanticErrors = true;
    }
    
    public static boolean hasSemanticErrors() {
        return semanticErrors;
    }

    public static boolean isOfType(String childType, String parentType) {
        if (childType.isEmpty()) return true;
        if (parentType.contentEquals(childType)) return true;
        if (parentType.contentEquals(OBJECT_TYPE)) {
            return true;
        }
        TreeNodeType classNode = idToNodeMap.get(childType);
        while (classNode != null) {
            if (classNode.seen) {
                break;
            }
            classNode.seen = true;
            classNode = classNode.parentNode;
        }
        classNode = idToNodeMap.get(parentType);
        if (classNode != null && classNode.seen) {
            resetVisited();
            return true;
        }
        resetVisited();
        return false;
    }

    public static void addType(String type, String parentType) {
        if (idToNodeMap.containsKey(type)) {
            return;
        }

        if (idToNodeMap.containsKey(parentType)) {
            idToNodeMap.put(type, new TreeNodeType(type, idToNodeMap.get(parentType)));
        } else {
            idToNodeMap.put(type, new TreeNodeType(type, parentType));
        }
    }

    public static void establishHierarchy() {
        for (TreeNodeType t: idToNodeMap.values()) {

            if (t.parentNode == null) {
                TreeNodeType p = idToNodeMap.get(t.parentName);
                if (p != null) {
                    t.parentNode = p;
                } else if (!t.type.contentEquals(OBJECT_TYPE)){
                    t.parentNode = idToNodeMap.get(OBJECT_TYPE);
                }
            }
        }
    }

    public static void printChain(String start) {
        if (start != null) {
            System.out.println("Chain: " + start);
            TreeNodeType n = idToNodeMap.get(start);
            printChain(n.parentName);
        }
    }

    public static boolean hasCycle(String classType) {
        TreeNodeType classNode = idToNodeMap.get(classType);
        while (classNode != null) {
            if (classNode.seen) {
                resetVisited();
                return true;
            }
            classNode.seen = true;
            classNode = classNode.parentNode;
        }

        resetVisited();
        return false;
    }

    public static String getCommonType(String t1, String t2) {
        if (t1.isEmpty() || t2.isEmpty()) return OBJECT_TYPE;
        TreeNodeType classNode = idToNodeMap.get(t1);
        while (classNode != null) {
            if (classNode.seen) {
                break;
            }
            classNode.seen = true;
            classNode = classNode.parentNode;
        }
        classNode = idToNodeMap.get(t2);
        while (classNode != null) {
            if (classNode.seen) {
                resetVisited();
                return classNode.type;
            }
            classNode = classNode.parentNode;
        }
        resetVisited();
        return OBJECT_TYPE;
    }

    public static boolean hasMain() {
        if (idToAstClassMap.containsKey(MAIN_TYPE) &&
                idToAstClassMap.get(MAIN_TYPE).lookupCurrent("main", Scope.CLASS_METHOD) != null) {
            return true;
        }
        return false;
    }

    private static void resetVisited() {
        for (TreeNodeType t: idToNodeMap.values()) {
            t.seen = false;
        }
    }
}
