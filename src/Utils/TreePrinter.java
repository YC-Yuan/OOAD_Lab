package Utils;


import java.util.ArrayList;
import java.util.LinkedList;
import java.util.List;

// 以确定的方式打印树
public class TreePrinter {
    private static final String space = "   ";
    private static final String stick = "│  ";
    private static final String prefixEnd = "└──";
    private static final String prefixMiddle = "├──";
    private final List<Integer> levels = new LinkedList<>();
    private final List<String> contents = new LinkedList<>();
    private final List<Boolean> isEnd = new ArrayList<>();

    private int getMaxLevel() {
        int res = 0;
        for (Integer i : levels
        ) {
            res = Math.max(i, res);
        }
        return res;
    }

    public void addNode(int level, String str) {
        levels.add(level);
        contents.add(str);
    }

    public String toString() {
        refreshIsEnd();
        StringBuilder sb = new StringBuilder();
        int maxLevel = getMaxLevel();
        boolean[] flags = new boolean[maxLevel]; // 存储对应等级处是否需要|
        // 开始打印，i是行数
        for (int i = 0; i < levels.size(); i++) {
            int level = levels.get(i);
            // j相当于每一行的列，逐列考虑当前是否需要用竖杠
            for (int j = 0; j < level - 1; j++) {
                if (flags[j]) {
                    sb.append(stick);
                } else {
                    sb.append(space);
                }
            }
            // 考虑j=level-1这一列，即内容前的最后一列：是end，则└──，不是end，则├──
            if (isEnd.get(i)) {
                sb.append(prefixEnd);
                flags[level - 1] = false;
            } else {
                sb.append(prefixMiddle);
                flags[level - 1] = true;
            }
            sb.append(contents.get(i)).append("\n");
        }
        return sb.toString();
    }

    // 记录每个内容是否是末子
    private void refreshIsEnd() {
        isEnd.clear();
        int maxLevel = getMaxLevel();
        // 是否已经出现
        boolean[] flags = new boolean[maxLevel];
        // 倒着遍历,还没出现的都认为是末子
        for (int i = levels.size() - 1; i >= 0; i--) {
            int level = levels.get(i);
            if (flags[level - 1]) {
                // 已经出现了，是哥哥
                isEnd.add(0, false);

            } else {
                // 还没出现，是末弟
                isEnd.add(0, true);
                // 末弟出现了，标记一下flag
                flags[level - 1] = true;
            }
            // 把更高等级的flag刷新掉
            for (int curFlag = level; curFlag < maxLevel; curFlag++) {
                flags[curFlag] = false;
            }
        }
    }

    public static void main(String[] args) {
        TreePrinter tp = new TreePrinter();
        tp.addNode(1, "aa");
//        tp.addNode(2, "bb");
//        tp.addNode(1, "bb");
//        tp.addNode(2, "bb-bb");
//        tp.addNode(2, "bb-bb");
//        tp.addNode(3, "bb-bb");
//        tp.addNode(4, "bb-bb");
//        tp.addNode(2, "bb-bb");
//        tp.addNode(1, "aha");
        System.out.println(tp);
    }
}