package Parsers;

import Commands.Command;
import Commands.OpenCommand;
import Utils.Utils;

public class OpenParser extends Parser {
    public OpenParser(String keyword) {
        super(keyword);
    }

    @Override
    protected Command parse(String[] cmds) {
        if (cmds.length == 2) {
            return open(cmds);
        }
        System.out.println("open指令参数数量错误，应为1个");
        return null;
    }

    private Command open(String[] cmds) {
        String path = cmds[1];
        if (Utils.isQuoted(path)) {
            path = Utils.extractQuoted(path);
            return new OpenCommand(path);
        } else {
            System.out.println("路径参数需要被双引号包含且不为空");
        }
        return null;
    }
}
