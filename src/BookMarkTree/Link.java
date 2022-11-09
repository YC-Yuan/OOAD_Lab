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
    public int deleteSelf() {
        if (this.prev == null) {
            return -1;
        } else {
            int index = this.prev.getLinks().indexOf(this);
            this.prev.getLinks().remove(index);
            return index;
        }
    }
}
