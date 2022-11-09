package Parsers;

import Commands.*;
import Commands.Reverse.DeleteBookmarkCommand;

public class DeleteBookmarkParser extends Parser {

    public DeleteBookmarkParser(String keyword) {
        super(keyword);
    }

    @Override
    protected Command parse(String[] cmds) {
        if (cmds.length == 2) {
            return removeBookmark(cmds);
        }
        System.out.println("delete-bookmark指令参数数量错误，应为1个");
        return null;
    }

    private Command removeBookmark(String[] cmds) {
        String name = cmds[1];
        if (Utils.isQuoted(name)) {
            name = Utils.extractQuoted(name);
            return new DeleteBookmarkCommand(name);
        } else {
            System.out.println("名称参数需要被双引号包含且不为空");
        }
        return null;
    }
}
