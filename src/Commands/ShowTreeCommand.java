package Commands;

import BookMarkTree.BookMarkTree;

public class ShowTreeCommand extends Command {
    @Override
    public boolean execute() {
        BookMarkTree bmt = BookMarkTree.getInstance();
        // 打印的并不是show-tree

        System.out.print(bmt.toString());
        return true;
    }

    // 按层级打印内容
}
