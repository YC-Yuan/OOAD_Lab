package Utils;

import java.io.FileWriter;
import java.io.IOException;

public class FileManager {
    private static FileManager instance;
    private String path = null;

    private FileManager() {
    }

    public String getPath() {
        return path;
    }

    public static FileManager getInstance() {
        if (instance == null) {
            instance = new FileManager();
        }
        return instance;
    }

    public static void refresh() {
        instance = new FileManager();
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
