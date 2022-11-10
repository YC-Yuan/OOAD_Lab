package Commands.Reverse;

import BookMarkTree.*;
import Commands.Command;

import java.util.List;

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
    private Link self = null;

    @Override
    public boolean execute() {
        BookMarkTree bmt = BookMarkTree.getInstance();
        // 在指定位置添加
        List<Folder> folders = bmt.getFolder(directory);
        if (folders.isEmpty()) {
            destination = null;
        } else {
            destination = folders.get(0);
        }
        index = bmt.addLink(name, url, destination);
        if (index == -1) System.out.println("添加失败，文件夹不存在");
        else executeWithRecord();
        return index != -1;
    }

    @Override
    public void undo() {
        self = destination.getLinks().get(index);
        destination.getLinks().remove(index);
    }

    @Override
    public void redo() {
        destination.getLinks().add(index, self);
    }
}
