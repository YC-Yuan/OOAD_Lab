import BookMarkTree.Folder;
import BookMarkTree.Node;

import java.io.File;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;


public class Draft {

    public static void main(String[] args) {
        File dir=new File(".");
        File[] files = dir.listFiles();
        for (File f:files
             ) {
            System.out.println(f.getName());
        }
    }
}
