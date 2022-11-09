package BookMarkTree;

public class Link extends Node {
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
    public boolean deleteSelf() {
        if (this.prev == null) {
            return false;
        } else {
            return this.prev.getLinks().remove(this);
        }
    }
}
