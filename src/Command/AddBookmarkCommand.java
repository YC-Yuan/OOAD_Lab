package Command;

import BookMarkTree.*;

public class AddBookmarkCommand extends Command implements Reversible {
    private final String name;
    private final String url;
    private final String directory;

    public AddBookmarkCommand(String name, String url, String directory) {
        super();
        this.name = name;
        this.url = url;
        this.directory = directory;
    }

    private int index = -1;
    private Folder destination = null;

    @Override
    public boolean execute() {
        BookMarkTree bmt = BookMarkTree.getInstance();
        destination = bmt.getFolder(directory);
        index = bmt.addLink(name, url, destination);
        if (index == -1) System.out.println("添加失败，文件夹不存在");
        return index != -1;
    }

    @Override
    public void undo() {
        destination.getLinks().remove(index);
    }

    @Override
    public void redo() {
        execute();
    }
}
