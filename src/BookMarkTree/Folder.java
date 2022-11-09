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

    // 在文件夹中搜索Node
    public Node getNode(String name, Class c) {
        for (Node node : nodes
        ) {
            if (node.checkSelf(name, c)) return node;
        }
        for (Folder folder : getFolders()) {
            Node res = folder.getNode(name, c);
            if (res != null) return res;
        }
        return null;
    }

    // 获取Folder下面的Folder
    public List<Folder> getFolders() {
        List<Folder> res = new LinkedList<>();
        for (Node node : nodes
        ) {
            if (node instanceof Folder) res.add((Folder) node);
        }
        return res;
    }

    public boolean deleteNode(String name, Class c) {
        Node node = getNode(name, c);
        if (node == null) return false;
        else {
            return node.deleteSelf();
        }
    }

    @Override
    public String toString() {
        int level = getLevel();
        return "#".repeat(level) + " " + name + "\n";
    }


}
