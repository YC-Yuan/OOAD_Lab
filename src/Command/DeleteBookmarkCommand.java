package Command;

import BookMarkTree.*;

public class DeleteBookmarkCommand extends Command implements Reversible {

    private final String name;

    public DeleteBookmarkCommand(String name) {
        super();
        this.name = name;
    }

    private int index = -1;
    private Folder destination = null;
    private Link target = null;


    @Override
    public boolean execute() {
        BookMarkTree bmt = BookMarkTree.getInstance();
        Link link = bmt.getLink(name);
        target = link;
        destination = link.getPrev();
        index = link.deleteSelf();
        return index != -1;
    }

    // 把link加回到特定位置
    @Override
    public void undo() {
        destination.getLinks().add(index, target);
    }

    @Override
    public void redo() {
        destination.getLinks().remove(index);
    }
}
