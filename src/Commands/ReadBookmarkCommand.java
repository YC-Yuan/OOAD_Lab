package Commands;

import BookMarkTree.*;

public class ReadBookmarkCommand extends Command {
    private final String name;

    public ReadBookmarkCommand(String name) {
        this.name = name;
    }

    @Override
    public boolean execute() {
        BookMarkTree bmt = BookMarkTree.getInstance();
        for (Link l : bmt.getLink(name)) {
            l.read();
        }
        return false;
    }
}
