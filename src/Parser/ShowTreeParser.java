package Parser;

import Command.*;

public class ShowTreeParser extends Parser {
    public ShowTreeParser(String keyword) {
        super(keyword);
    }

    @Override
    protected Command parse(String[] cmds) {
        if (cmds.length == 1) {
            return new ShowTreeCommand();
        } else {
            System.out.println("show-tree指令参数数量错误，不应有参数");
            return null;
        }
    }
}
