package BookMarkTree;

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

    private List<Folder> roots;
}
