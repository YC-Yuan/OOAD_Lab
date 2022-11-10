package BookMarkTree;

public class NodeFactory {
    public static Node getNode(String str) {
        if (str.length() == 0) return null;
        // 判断种类
        if (str.startsWith("#")) {
            int level = getLevel(str);
            String name = str.substring(level + 1);
            return new Folder(name);
        } else {
            int left = str.indexOf("]");
            String name = str.substring(1, left);
            String url = str.substring(left + 2, str.length() - 1);
            return new Link(name, url);
        }
    }

    public static int getLevel(String str) {
        return str.indexOf(" ");
    }
}
