package BookMarkTree;

import java.util.LinkedList;
import java.util.List;

public class BookMarkTree {
    private static BookMarkTree instance;

    private BookMarkTree() {
    }

    public String toString() {
        StringBuilder res = new StringBuilder();
        for (Folder f : roots
        ) {
            res.append(f.toString());
        }
        return res.toString();
    }

    public static BookMarkTree getInstance() {
        if (instance == null) {
            instance = new BookMarkTree();
        }
        return instance;
    }

    private final List<Folder> roots = new LinkedList<>();

    // 添加一级标题,返回添加位置的index
    public int addRootTitle(String name) {
        Folder folder = new Folder(name);
        roots.add(folder);
        return roots.size() - 1;
    }

    public int addRootTitle(int index, Folder title) {
        roots.add(index, title);
        return index;
    }

    public Folder getRootTitle(int index) {
        return roots.get(index);
    }

    public void deleteRootTitle(int index) {
        roots.remove(index);
    }

    // 添加标题到指定位置,返回指定位置
    public int addTitle(String name, Folder folder) {
        if (folder == null) return -1;
        folder.addFolder(new Folder(name));
        return folder.getFolders().size() - 1;
    }

    public int addLink(String name, String url, Folder folder) {
        if (folder == null) return -1;
        folder.addLink(new Link(name, url));
        return folder.getLinks().size() - 1;
    }

    public Folder getFolder(String name) {
        return getFolder(name, roots);
    }

    public Link getLink(String name) {
        return getLink(name, roots);
    }

    protected static Folder getFolder(String name, List<Folder> folders) {
        for (Folder folder : folders) {
            if (folder.checkName(name)) return folder;
        }
        for (Folder folder : folders) {
            Folder res = folder.getFolder(name);
            if (res != null) return res;
        }
        return null;
    }

    protected static Link getLink(String name, List<Folder> folders) {
        for (Folder folder : folders
        ) {
            Link res = folder.getLink(name);
            if (res != null) return res;
        }
        return null;
    }
}
