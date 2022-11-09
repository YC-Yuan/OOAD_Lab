import Command.Command;
import Parser.Parser;
import Parser.AddTitleParser;

import java.util.LinkedList;
import java.util.List;
import java.util.Scanner;

public class Main {
    public static void main(String[] args) {
        System.out.println("主人，输入指令吧>.<");
        Scanner sc = new Scanner(System.in);

        // 载入解析器
        List<Parser> parsers = new LinkedList<Parser>();
        parsers.add(new AddTitleParser("add-title"));

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
                // 执行命令
                command.execute();
            } else {
                // 无效命令
                System.out.println("无效指令，请重新输入");
            }
        }
    }
}
