package Commands;

import BookMarkTree.*;

import java.awt.print.Book;
import java.io.BufferedReader;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;
import java.util.Stack;

public class OpenCommand extends Command {
    private final String path;

    public OpenCommand(String path) {
        this.path = path;
    }

    @Override
    public boolean execute() {
        BufferedReader reader;
        BookMarkTree.refresh();
        BookMarkTree bmt = BookMarkTree.getInstance();
        try {
            reader = new BufferedReader(new FileReader(path));
            String str;
            List<Folder> folderList = new ArrayList<>();
            Folder lastFolder = new Folder("");
            while ((str = reader.readLine()) != null) {
                // 每行处理
                Node n = NodeFactory.getNode(str);
                if (n instanceof Folder) {
                    Folder f = (Folder) n;
                    // 查看等级
                    int level = NodeFactory.getLevel(str);
                    // 更新当前等级的目录
                    if (level > folderList.size()) {
                        folderList.add(f);
                    } else {
                        folderList.remove(level - 1);
                        folderList.add(level - 1, f);
                    }
                    if (level == 1) {
                        bmt.getRoots().add(f);
                    } else {
                        Folder curFolder = folderList.get(level - 2);
                        f.setPrev(curFolder);
                        curFolder.getFolders().add(f);
                    }
                    lastFolder = f;
                } else if (n instanceof Link) {
                    Link l = (Link) n;
                    l.setPrev(lastFolder);
                    lastFolder.getLinks().add(l);
                }
            }
            return true;
        } catch (FileNotFoundException e) {
            System.out.println("文件读取出错，请检查路径");
            e.printStackTrace();
        } catch (IOException e) {
            e.printStackTrace();
        }
        return false;
    }
}
