package Parsers;

import Commands.Command;
import Commands.RedoCommand;

public class RedoParser extends Parser {
    public RedoParser(String keyword) {
        super(keyword);
    }

    @Override
    protected Command parse(String[] cmds) {
        if (cmds.length == 1) {
            return new RedoCommand();
        } else {
            System.out.println("undo指令参数数量错误，不应有参数");
            return null;
        }
    }
}
