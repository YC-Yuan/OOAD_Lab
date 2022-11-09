package Parser;

import Command.Command;

public abstract class Parser {
    public Parser(String keyword) {
        this.keyWord = keyword;
    }

    protected String keyWord;

    // 进行通用的检测，继承者不需要重写
    public Command parse(String cmd) {
        if (cmd.isEmpty()) return null;
        String[] cmds = cmd.split(" ");
        // 指令不匹配则返回
        if (!cmds[0].equals(keyWord)) return null;
        return parse(cmds);
    }

    // 保证cmd类型正确，需要进一步检测合法性，构造指令
    abstract protected Command parse(String[] cmds);
}
