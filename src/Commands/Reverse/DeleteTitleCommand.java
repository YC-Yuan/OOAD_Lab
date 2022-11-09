package Commands.Reverse;

import BookMarkTree.BookMarkTree;
import BookMarkTree.Folder;
import Commands.Command;

public class DeleteTitleCommand extends Command implements Reversible {
    private final String name;

    public DeleteTitleCommand(String name) {
        super();
        this.name = name;
    }

    private int index = -1;
    private Folder destination = null;
    private Folder target = null;

    @Override
    public boolean execute() {
        BookMarkTree bmt = BookMarkTree.getInstance();
        Folder folder = bmt.getFolder(name);
        target = folder;
        if (folder.getPrev() != null) {
            destination = folder.getPrev();
            index = folder.deleteSelf();
        } else {
            target = bmt.getRootTitle(name);
            if (target != null) {
                index = bmt.getRoots().indexOf(target);
                bmt.getRoots().remove(index);
            }
        }
        if (index == -1) {
            return false;
        } else {
            executeWithRecord();
            return true;
        }
    }

    // 加回去
    @Override
    public void undo() {
        BookMarkTree bmt = BookMarkTree.getInstance();
        bmt.getRoots().add(index, target);
    }

    @Override
    public void redo() {
        BookMarkTree bmt = BookMarkTree.getInstance();
        bmt.getRoots().remove(index);
    }
}
