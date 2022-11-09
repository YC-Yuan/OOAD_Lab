package BookMarkTree;

import java.util.LinkedList;
import java.util.List;

public class Folder extends Node {
    protected List<Link> links = new LinkedList<>();
    protected List<Folder> folders = new LinkedList<>();

    public Folder(String name) {
        super(name);
    }

    @Override
    public boolean deleteSelf() {
        if (this.prev==null){
            return false;
        }else{
            return this.prev.getFolders().remove(this);
        }
    }

    public List<Folder> getFolders() {
        return folders;
    }

    public List<Link> getLinks() {
        return links;
    }

    public void addFolder(Folder folder) {
        folder.setPrev(this);
        folders.add(folder);
    }

    public void addLink(Link link) {
        link.setPrev(this);
        links.add(link);
    }

    public Folder getFolder(String name) {
        return BookMarkTree.getFolder(name, folders);
    }

    public Link getLink(String name) {
        for (Link link : links) {
            if (link.checkName(name)) return link;
        }
        for (Folder folder : folders) {
            Link res = folder.getLink(name);
            if (res != null) return res;
        }
        return null;
    }

    @Override
    public String toString() {
        int level = getLevel();
        StringBuilder res = new StringBuilder("#".repeat(level) + " " + name + "\n");
        for (Link link : links) {
            res.append(link.toString());
        }
        for (Folder folder : folders) {
            res.append(folder.toString());
        }
        return res.toString();
    }
}
