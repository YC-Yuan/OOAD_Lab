package BookMarkTree;

import java.awt.print.Book;
import java.util.LinkedList;
import java.util.List;

public class Folder extends Node {
    protected List<Link> links = new LinkedList<>();
    protected List<Folder> folders = new LinkedList<>();

    public Folder(String name) {
        super(name);
    }

    @Override
    public int deleteSelf() {
        if (this.prev == null) {
            return -1;
        } else {
            int index = this.prev.getFolders().indexOf(this);
            this.prev.getFolders().remove(index);
            return index;
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

    public List<Folder> getFolder(String name) {
        return BookMarkTree.getFolder(name, folders);
    }

    public List<Link> getLink(String name) {
        List<Link> res = new LinkedList<>();
        for (Link link : links) {
            if (link.checkName(name)) res.add(link);
        }
        res.addAll(BookMarkTree.getLink(name, folders));
        return res;
    }

    public List<Node> getAll() {
        List<Node> res = new LinkedList<>();
        res.add(this);
        res.addAll(links);
        for (Folder f : folders
        ) {
            res.addAll(f.getAll());
        }
        return res;
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
