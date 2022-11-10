package Parsers;

import Commands.Command;
import Commands.OpenCommand;
import Commands.ReadBookmarkCommand;
import Utils.Utils;

public class ReadBookmarkParser extends Parser{
    public ReadBookmarkParser(String keyword) {
        super(keyword);
    }

    @Override
    protected Command parse(String[] cmds) {
        if (cmds.length == 2) {
            return read(cmds);
        }
        System.out.println("read-bookmark指令参数数量错误，应为1个");
        return null;
    }

    private Command read(String[] cmds){
        String name=cmds[1];
        if (Utils.isQuoted(name)) {
            name = Utils.extractQuoted(name);
            return new ReadBookmarkCommand(name);
        } else {
            System.out.println("名称参数需要被双引号包含且不为空");
        }
        return null;
    }
}
