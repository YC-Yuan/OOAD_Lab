package Parsers;

import Commands.Command;
import Commands.ListTreeCommand;

public class ListTreeParser extends Parser {
    public ListTreeParser(String keyword) {
        super(keyword);
    }

    @Override
    protected Command parse(String[] cmds) {
        if (cmds.length == 1) {
            return new ListTreeCommand();
        } else {
            System.out.println(keyWord + "指令参数数量错误，不应有参数");
        }
        return null;
    }
}
