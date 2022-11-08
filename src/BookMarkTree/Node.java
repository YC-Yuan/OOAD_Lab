package BookMarkTree;

public class Node {
    protected String name;
    protected Node prev;

    public String toString() {
        return name + "\n";
    }

    public Node(String name) {
        name = name;
    }

    public void setPrev(Node prev) {
        this.prev = prev;
    }

    public String getName() {
        return name;
    }

    // 查看层级,只有root返回1
    public int getLevel() {
        if (prev == null) return 1;
        else return prev.getLevel() + 1;
    }
}
