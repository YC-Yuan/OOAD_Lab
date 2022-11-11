package Commands;

import Utils.TreePrinter;

import java.io.File;
import java.util.LinkedList;
import java.util.List;

public class ListTreeCommand extends Command {
    private final List<Integer> levels = new LinkedList<>();
    private final List<String> names = new LinkedList<>();

    @Override
    public boolean execute() {
        File dir = new File(".");
        File[] fs = dir.listFiles();

        levels.clear();
        names.clear();
        loadFiles(fs, 1);
        TreePrinter tp = new TreePrinter();
        for (int i = 0; i < levels.size(); i++) {
            tp.addNode(levels.get(i), names.get(i));
        }
        System.out.println(tp);
        return false;
    }

    private void loadFiles(File[] fs, int level) {
        if (fs == null) return;
        // 先载入文件，再载入文件夹
        for (File f : fs) {
            if (f.isFile()) {
                levels.add(level);
                names.add("\"" + f.getName() + "\"");
            }
        }
        for (File f : fs) {
            if (f.isDirectory()) {
                levels.add(level);
                names.add(f.getName());
                loadFiles(f.listFiles(), level + 1);
            }
        }
    }
}