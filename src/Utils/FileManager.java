package Utils;

import java.io.FileWriter;
import java.io.IOException;

public class FileManager {
    private static FileManager instance;
    private String path = "./test.bmk";

    private FileManager() {
    }

    public static FileManager getInstance() {
        if (instance == null) {
            instance = new FileManager();
        }
        return instance;
    }

    public void setPath(String path) {
        this.path = path;
    }


    public void save(String str) throws IOException {
        FileWriter fw = new FileWriter(path);
        fw.write(str);
        fw.flush();
        fw.close();
    }
}