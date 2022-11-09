package BookMarkTree;

public abstract class Node {
    protected String name;
    protected Folder prev;

    public String toString() {
        return name + "\n";
    }

    public Node(String name) {
        this.name = name;
    }

    public void setPrev(Folder prev) {
        this.prev = prev;
    }

    public String getName() {
        return name;
    }

    public boolean checkName(String name) {
        return this.name.equals(name);
    }

    // 从父亲中删除自己
    abstract public int deleteSelf();

    // 查看层级,只有root返回1
    public int getLevel() {
        if (prev == null) return 1;
        else return prev.getLevel() + 1;
    }

    public Folder getPrev() {
        return prev;
    }
}
