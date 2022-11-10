package Commands.Reverse;

import BookMarkTree.*;
import Commands.Command;

import java.util.LinkedList;
import java.util.List;

public class DeleteBookmarkCommand extends Command implements Reversible {

    private final String name;

    public DeleteBookmarkCommand(String name) {
        super();
        this.name = name;
    }

    private final List<Integer> indexes = new LinkedList<>();
    private final List<Folder> destinations = new LinkedList<>();
    private final List<Link> targets = new LinkedList<>();


    @Override
    public boolean execute() {
        BookMarkTree bmt = BookMarkTree.getInstance();
        // 找一个link
        List<Link> links = bmt.getLink(name);
        int index = -1;
        for (Link l : links
        ) {
            targets.add(l);
            destinations.add(l.getPrev());
            int outcome = l.deleteSelf();
            indexes.add(outcome);
            index = Math.max(index, outcome);
        }
        if (index != -1) executeWithRecord();
        return index != -1;
    }

    // 把link加回到特定位置
    @Override
    public void undo() {
        for (int i = 0; i < indexes.size(); i++) {
            int index = indexes.get(i);
            Folder destination = destinations.get(i);
            Link target = targets.get(i);
            destination.getLinks().add(index, target);
        }
    }

    @Override
    public void redo() {
        for (int i = 0; i < indexes.size(); i++) {
            int index = indexes.get(i);
            Folder destination = destinations.get(i);
            destination.getLinks().remove(index);
        }
    }
}
