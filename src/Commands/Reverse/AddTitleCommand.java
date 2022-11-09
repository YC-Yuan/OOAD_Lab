package Commands.Reverse;

import BookMarkTree.BookMarkTree;
import BookMarkTree.Folder;
import Commands.Command;

public class AddTitleCommand extends Command implements Reversible {
    private final String name;
    private String directory = null;

    public AddTitleCommand(String name) {
        super();
        this.name = name;
    }

    public AddTitleCommand(String name, String directory) {
        super();
        this.name = name;
        this.directory = directory;
    }

    // 返回执行成功与否
    @Override
    public boolean execute() {
        BookMarkTree bmt = BookMarkTree.getInstance();
        if (directory == null) {
            // 在根部添加
            index = bmt.addRootTitle(name);
            executeWithRecord();
            return true;
        } else {
            // 在指定位置添加
            destination = bmt.getFolder(directory);
            index = bmt.addTitle(name, destination);
            if (index == -1) System.out.println("添加失败，文件夹不存在");
            else executeWithRecord();
            return index != -1;
        }
    }

    // 需要记录undo redo所需要的信息
    private int index = -1;
    private Folder destination = null;
    private Folder self = null;

    @Override
    public void undo() {
        BookMarkTree bmt = BookMarkTree.getInstance();
        if (directory == null) {
            // 在根部删除
            self = bmt.getRootTitle(index);
            bmt.deleteRootTitle(index);
        } else {
            // 在指定位置删除
            self = destination.getFolders().get(index);
            destination.getFolders().remove(index);
        }
    }

    @Override
    public void redo() {
        BookMarkTree bmt = BookMarkTree.getInstance();
        if (directory == null) {
            bmt.addRootTitle(index, self);
        } else {
            destination.getFolders().add(index, self);
        }
    }
}
