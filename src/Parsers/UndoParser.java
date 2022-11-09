package Parsers;

import Commands.Command;
import Commands.UndoCommand;

public class UndoParser extends Parser {

    public UndoParser(String keyword) {
        super(keyword);
    }

    @Override
    protected Command parse(String[] cmds) {
        if (cmds.length == 1) {
            return new UndoCommand();
        } else {
            System.out.println("undo指令参数数量错误，不应有参数");
            return null;
        }
    }
}
