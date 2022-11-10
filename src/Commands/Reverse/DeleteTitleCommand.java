package Commands.Reverse;

import BookMarkTree.*;
import Commands.Command;

import java.util.LinkedList;
import java.util.List;

public class DeleteTitleCommand extends Command implements Reversible {
    private final String name;

    public DeleteTitleCommand(String name) {
        super();
        this.name = name;
    }


    private final List<Integer> indexes = new LinkedList<>();
    private final List<Folder> destinations = new LinkedList<>();
    private final List<Folder> targets = new LinkedList<>();

    @Override
    public boolean execute() {
        BookMarkTree bmt = BookMarkTree.getInstance();
        // 找出所有的Folder
        List<Folder> folders = bmt.getFolder(name);
        int saveIndex = -1;
        for (Folder f : folders
        ) {
            targets.add(f);
            if (f.getPrev() != null) {
                destinations.add(f.getPrev());
                int index = f.deleteSelf();
                indexes.add(index);
                saveIndex = Math.max(index, saveIndex);
            } else {
                // 有可能找不到？
                int index = bmt.getRoots().indexOf(f);
                bmt.getRoots().remove(index);
                indexes.add(index);
                destinations.add(null);
                saveIndex = Math.max(index, saveIndex);
            }
        }
        if (saveIndex == -1) {
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
        for (int i = 0; i < indexes.size(); i++) {
            int index = indexes.get(i);
            Folder target = targets.get(i);
            Folder destination = destinations.get(i);
            if (destination == null) {
                // add at root
                bmt.getRoots().add(index, target);
            } else {
                destination.getFolders().add(index, target);
            }
        }
    }

    @Override
    public void redo() {
        BookMarkTree bmt = BookMarkTree.getInstance();
        for (int i = 0; i < indexes.size(); i++) {
            int index = indexes.get(i);
            Folder destination = destinations.get(i);
            if (destination == null) {
                // add at root
                bmt.getRoots().remove(index);
            } else {
                destination.getFolders().remove(index);
            }
        }
    }
}
