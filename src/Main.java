import Commands.Command;
import Commands.OpenCommand;
import Parsers.*;
import Utils.*;

import java.util.LinkedList;
import java.util.List;
import java.util.Scanner;

public class Main {
    public static void main(String[] args) {
        System.out.println("主人，输入指令吧>.<");
        Scanner sc = new Scanner(System.in);
        // 载入解析器
        List<Parser> parsers = new LinkedList<>();
        parsers.add(new OpenParser("open"));
        parsers.add(new OpenParser("bookmark"));
        parsers.add(new AddTitleParser("add-title"));
        parsers.add(new ShowTreeParser("show-tree"));
        parsers.add(new AddBookmarkParser("add-bookmark"));
        parsers.add(new DeleteBookmarkParser("delete-bookmark"));
        parsers.add(new DeleteTitleParser("delete-title"));
        parsers.add(new UndoParser("undo"));
        parsers.add(new RedoParser("redo"));
        parsers.add(new SaveParser("save"));
        parsers.add(new ReadBookmarkParser("read-bookmark"));
        parsers.add(new ListTreeParser("ls-tree"));
        // 主循环
        while (true) {
            // 输入指令
            String input = sc.nextLine();
            // 解析指令，获取命令
            Command command = null;
            for (Parser parser : parsers
            ) {
                command = parser.parse(input);
                if (command != null) {
                    break;
                }
            }
            if (command != null) {
                // 特殊对待一下open
                if (command instanceof OpenCommand) {
                    command.execute();
                } else {
                    // 需要设置了path才能执行
                    if (FileManager.getInstance().getPath() == null) {
                        System.out.println("请先通过bookmark指令指定工作空间");
                    } else {
                        command.execute();
                    }
                }
            } else {
                // 无效命令
                System.out.println("无效指令，请检查输入并重试");
            }
        }
    }
}
