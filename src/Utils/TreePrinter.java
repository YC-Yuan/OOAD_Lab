package Utils;


import java.util.ArrayList;
import java.util.LinkedList;
import java.util.List;

// 以确定的方式打印树
public class TreePrinter {
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
        // 记录了每个等级在第几行结束（索引和结果都从0而不是1开始）
        int[] levelEndIndex = getLevelEndIndex();
        StringBuilder sb = new StringBuilder();
        // 开始打印，i是行数
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
        System.out.println(isEnd);
    }

    private int[] getLevelEndIndex() {
        // 记录每个等级结束的位置
        int maxLevel = getMaxLevel();
        // 所有都在0结束
        int[] levelEndIndex = new int[maxLevel];
        for (int i = levels.size() - 1; i >= 0; i--) {
            int level = levels.get(i);
            // 让i处的level对应的结束为止记录为现有值和i的最大值
            levelEndIndex[level - 1] = Math.max(i, levelEndIndex[level - 1]);
        }
        return levelEndIndex;
    }

    public static void main(String[] args) {
        TreePrinter tp = new TreePrinter();
        tp.addNode(1, "aa");
        tp.addNode(2, "bb");
        tp.addNode(1, "bb");
        tp.addNode(2, "bb-bb");
        tp.addNode(2, "bb-bb");
        tp.addNode(3, "bb-bb");
        tp.addNode(2, "bb-bb");
        tp.addNode(1, "aha");
        tp.refreshIsEnd();
    }
}