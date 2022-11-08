package BookMarkTree;

import java.util.LinkedList;
import java.util.List;

public class Folder extends Node {
    protected List<Node> nodes = new LinkedList<Node>();

    public Folder(String name) {
        super(name);
    }

    public List<Node> getNodes() {
        return nodes;
    }

    public void addNode(Node node) {
        nodes.add(node);
    }

    public boolean deleteNode(String name, Class c) {
        // 检查孩子
        for (Node node : nodes
        ) {
            // 找到了，删掉
            if (node.getName().equals(name) && node.getClass() == c) {
                nodes.remove(node);
                return true;
            }
        }
        // 逐级检查
        for (Node node : nodes) {
            if (node instanceof Folder) {
                Folder folder = (Folder) node;
                boolean hasDeleted = folder.deleteNode(name, c);
                if (hasDeleted) return true;
            }
        }
        return false;
    }

    @Override
    public String toString() {
        int level = getLevel();
        return "#".repeat(level) + " " + name + "\n";
    }
}
