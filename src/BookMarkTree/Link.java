package BookMarkTree;

public class Link extends Node {
    private boolean hasRead = false;
    protected String url;

    public Link(String name, String url) {
        super(name);
        this.url = url;
    }

    @Override
    public String toString() {
        return "[" + name + "](" + url + ")\n";
    }

    @Override
    public int deleteSelf() {
        if (this.prev == null) {
            return -1;
        } else {
            int index = this.prev.getLinks().indexOf(this);
            this.prev.getLinks().remove(index);
            return index;
        }
    }

    @Override
    public String getTreeName() {
        String res = "\"";
        if (hasRead) res += "*";
        return res + name + "\"";
    }

    public void read() {
        hasRead = true;
    }
}
