package Parser;

import Command.*;

public class AddBookmarkParser extends Parser {
    public AddBookmarkParser(String keyword) {
        super(keyword);
    }

    @Override
    protected Command parse(String[] cmds) {
        if (cmds.length == 4) {
            return addBookmark(cmds);
        }
        System.out.println("add-bookmark指令参数数量错误，应为3个");
        return null;
    }

    private Command addBookmark(String[] cmds) {
        if (cmds[2].equals("at")) {
            // 解析@
            String[] left = cmds[1].split("@");
            if (left.length == 2) {
                String name = left[0];
                String url = left[1];
                String dir = cmds[3];
                if (Utils.isQuoted(name) && Utils.isQuoted(url) && Utils.isQuoted(dir)) {
                    // 全部带有引号
                    name = Utils.extractQuoted(name);
                    url = Utils.extractQuoted(url);
                    dir = Utils.extractQuoted(dir);
                    return new AddBookmarkCommand(name, url, dir);
                } else {
                    System.out.println("名称参数需要被双引号包含且不为空");
                    return null;
                }
            } else {
                System.out.println("add-bookmark指令第一个参数应被@分割");
                return null;
            }
        } else {
            System.out.println("add-bookmark指令第二个参数应为at");
            return null;
        }
    }
}
