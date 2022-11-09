package Parser;

import Command.Command;
import Command.AddTitleCommand;

public class AddTitleParser extends Parser {
    public AddTitleParser(String keyword) {
        super(keyword);
    }

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
                System.out.println("add-title指令参数数量错误，应为1或3个");
                return null;
        }
    }

    private Command addRootTitle(String[] cmds) {
        String name = cmds[1];
        // 检测是否被""包围
        if (Utils.isQuoted(name)) {
            name = Utils.extractQuoted(name);
            return new AddTitleCommand(name);
        } else {
            System.out.println("名称参数需要被双引号包含且不为空");
            return null;
        }
    }

    private Command addNormalTitle(String[] cmds) {
        if (cmds[2].equals("at")) {
            if (Utils.isQuoted(cmds[1]) && Utils.isQuoted(cmds[3])) {
                String name = Utils.extractQuoted(cmds[1]);
                String dir = Utils.extractQuoted(cmds[3]);
                return new AddTitleCommand(name, dir);
            } else {
                System.out.println("名称参数需要被双引号包含且不为空");
                return null;
            }
        } else {
            System.out.println("add-title指令第二个参数应为at");
            return null;
        }
    }
}
