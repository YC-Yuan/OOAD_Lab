package Parser;

public class Utils {
    public static boolean isQuoted(String str) {
        if (str.length() >= 3) {
            return str.indexOf("\"") == 0 && str.lastIndexOf("\"") == (str.length() - 1);
        }
        return false;
    }

    public static String extractQuoted(String str) {
        return str.substring(1, str.length() - 1);
    }

    public static void main(String[] args) {
        System.out.println(isQuoted("\"123\""));
        System.out.println(extractQuoted("\"123\""));

    }
}
