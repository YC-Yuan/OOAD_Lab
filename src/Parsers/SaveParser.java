package Parsers;

import Commands.Command;
import Commands.SaveCommand;

public class SaveParser extends Parser {

    public SaveParser(String keyword) {
        super(keyword);
    }

    @Override
    protected Command parse(String[] cmds) {
        if (cmds.length == 1) {
            return new SaveCommand();
        } else {
            System.out.println("save指令参数数量错误，不应有参数");
            return null;
        }
    }
}
