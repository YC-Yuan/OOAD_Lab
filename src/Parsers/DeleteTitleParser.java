package Parsers;

import Commands.Command;
import Commands.Reverse.DeleteTitleCommand;
import Utils.Utils;

public class DeleteTitleParser extends Parser {

    public DeleteTitleParser(String keyword) {
        super(keyword);
    }

    @Override
    protected Command parse(String[] cmds) {
        if (cmds.length == 2) {
            return removeTitle(cmds);
        }
        System.out.println("delete-title指令参数数量错误，应为1个");
        return null;
    }

    private Command removeTitle(String[] cmds) {
        String name = cmds[1];
        if (Utils.isQuoted(name)) {
            name = Utils.extractQuoted(name);
            return new DeleteTitleCommand(name);
        } else {
            System.out.println("名称参数需要被双引号包含且不为空");
        }
        return null;
    }
}
