package Commands;

import BookMarkTree.BookMarkTree;

public class ShowTreeCommand extends Command {
    @Override
    public boolean execute() {
        BookMarkTree bmt = BookMarkTree.getInstance();
        System.out.print(bmt.toString());
        return true;
    }
}
