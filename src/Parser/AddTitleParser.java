package Parser;

import Command.Command;

public class AddTitleParser extends Parser {
    @Override
    protected Command parse(String[] cmds) {
        switch (cmds.length) {
            case 2:
                // 添加一级标题
                return addRootTitle(cmds);
            case 4:
                // 添加多级标题
                return addNormalTitle(cmds);
            default:
                return null;
        }
    }

    private Command addRootTitle(String[] cmds) {
        return null;
    }

    private Command addNormalTitle(String[] cmds) {
        return null;
    }
}
