package Command;

import BookMarkTree.BookMarkTree;
import BookMarkTree.Folder;

public class AddTitleCommand extends Command implements Reversible {
    private String name = "";
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

    // 需要记录undo redo所需要的信息
    private int index = -1;
    private Folder destination = null;

    // 返回执行成功与否
    @Override
    public boolean execute() {
        BookMarkTree bmt = BookMarkTree.getInstance();
        if (directory == null) {
            // 在根部添加
            index = bmt.addRootTitle(name);
            return true;
        } else {
            // 在指定位置添加
            destination = bmt.getFolder(directory);
            index = bmt.addTitle(name, destination);
            if (index == -1) System.out.println("添加失败，文件夹不存在");
            return index != -1;
        }
    }


    @Override
    public void undo() {
        BookMarkTree bmt = BookMarkTree.getInstance();
        if (directory == null) {
            // 在根部删除
            bmt.deleteRootTitle(index);
        } else {
            // 在指定位置删除
            destination.getNodes().remove(index);
        }
    }

    @Override
    public void redo() {
        execute();
    }
}
