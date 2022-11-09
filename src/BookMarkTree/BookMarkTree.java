package BookMarkTree;

import java.util.LinkedList;
import java.util.List;

public class BookMarkTree {
    private static BookMarkTree instance;

    private BookMarkTree() {
    }

    public static BookMarkTree getInstance() {
        if (instance == null) {
            instance = new BookMarkTree();
        }
        return instance;
    }

    private List<Folder> roots = new LinkedList<>();

    // 添加一级标题,返回添加位置的index
    public int addRootTitle(String name) {
        Folder folder = new Folder(name);
        roots.add(folder);
        return roots.size() - 1;
    }

    public void deleteRootTitle(int index) {
        roots.remove(index);
    }

    // 添加标题到指定位置,返回指定位置
    public int addTitle(String name, Folder folder) {
        if (folder == null) return -1;
        folder.addNode(new Folder(name));
        return folder.getNodes().size() - 1;
    }

    public Folder getFolder(String name) {
        for (Folder folder : roots
        ) {
            if (folder.checkName(name)) return folder;
        }
        for (Folder folder : roots
        ) {
            Node node = folder.getNode(name, Folder.class);
            if (node != null) return (Folder) node;
        }
        return null;
    }
}
