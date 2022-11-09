package Commands;

import BookMarkTree.BookMarkTree;
import Utils.FileManager;

import java.io.IOException;

public class SaveCommand extends Command {
    @Override
    public boolean execute() {
        BookMarkTree bmt = BookMarkTree.getInstance();
        String str = bmt.toString();
        FileManager fm = FileManager.getInstance();
        try {
            fm.save(str);
        } catch (IOException e) {
            throw new RuntimeException(e);
        }
        return true;
    }
}
