import Commands.Reverse.ReversibleManager;
import Utils.FileManager;
import org.junit.After;
import org.junit.Before;
import org.junit.Test;

import static org.junit.Assert.*;

import java.io.ByteArrayOutputStream;
import java.io.PrintStream;

public class BookmarkTest {
    private PrintStream console = null;
    private ByteArrayOutputStream bytes = null;
    private final Bookmark bm = new Bookmark();

    @Before
    public void setUp() {
        bytes = new ByteArrayOutputStream();
        console = System.out;
        System.setOut(new PrintStream(bytes));
        bm.loadParsers();
    }

    @After
    public void tearDown() {
        System.setOut(console);
    }

    @Test
    public void requireWorkSpace() {
        FileManager.refresh();
        bm.inputLine("add-title \"1\"");
        assertEquals("请先通过bookmark指令指定工作空间\n", bytes.toString());
    }

    @Test
    public void invalidInput() {
        bm.inputLine("djqidqiqd");
        assertEquals("无效指令，请检查输入并重试\n", bytes.toString());
    }

    @Test
    public void openBmk() {
        bm.inputLine("open \"standard.bmk\"");
        bm.inputLine("show-tree");
        assertEquals("└──个人收藏\n" +
                "   ├──课程\n" +
                "   │  └──\"elearning\"\n" +
                "   ├──参考资料\n" +
                "   │  ├──\"Markdown Guide\"\n" +
                "   │  ├──函数式\n" +
                "   │  │  └──\"JFP\"\n" +
                "   │  └──面向对象\n" +
                "   └──待阅读\n" +
                "      └──\"Category Theory\"\n", bytes.toString());
    }

    @Test
    public void addTitle() {
        bm.inputLine("open \"new.bmk\"");
        bm.inputLine("add-title \"标题\"");
        bm.inputLine("show-tree");
        assertEquals("└──标题\n", bytes.toString());
        bm.inputLine("add-title \"标题1\" at \"标题\"");
        bm.inputLine("add-title \"标题2\" at \"标题\"");
        bm.inputLine("add-title \"标题3\" at \"标题\"");
        bm.inputLine("add-title \"标题2.1\" at \"标题2\"");
        bm.inputLine("show-tree");
        assertEquals("└──标题\n" +
                "└──标题\n" +
                "   ├──标题1\n" +
                "   ├──标题2\n" +
                "   │  └──标题2.1\n" +
                "   └──标题3\n", bytes.toString());
    }

    @Test
    public void addBookmark() {
        bm.inputLine("open \"new.bmk\"");
        bm.inputLine("add-title \"标题\"");
        bm.inputLine("add-title \"标题1\" at \"标题\"");
        bm.inputLine("add-title \"标题2\" at \"标题\"");
        bm.inputLine("add-title \"标题3\" at \"标题\"");
        bm.inputLine("add-title \"标题2.1\" at \"标题2\"");
        bm.inputLine("add-bookmark \"link\"@\"www.baidu.com\" at \"标题1\"");
        bm.inputLine("show-tree");
        assertEquals("└──标题\n" +
                "   ├──标题1\n" +
                "   │  └──\"link\"\n" +
                "   ├──标题2\n" +
                "   │  └──标题2.1\n" +
                "   └──标题3\n", bytes.toString());
    }

    @Test
    public void addTitleToNull() {
        bm.inputLine("open \"new.bmk\"");
        bm.inputLine("add-title \"标题\"");
        bm.inputLine("add-title \"标题1\" at \"null\"");
        assertEquals("添加失败，文件夹不存在\n", bytes.toString());
    }

    @Test
    public void addBookmarkToNull() {
        bm.inputLine("open \"new.bmk\"");
        bm.inputLine("add-title \"标题\"");
        bm.inputLine("add-bookmark \"link\"@\"www.baidu.com\" at \"标题1\"");
        assertEquals("添加失败，文件夹不存在\n", bytes.toString());
    }

    @Test
    public void deleteTitle() {
        bm.inputLine("open \"new.bmk\"");
        bm.inputLine("add-title \"标题\"");
        bm.inputLine("add-title \"标题1\" at \"标题\"");
        bm.inputLine("add-title \"标题2\" at \"标题\"");
        bm.inputLine("add-title \"标题3\" at \"标题\"");
        bm.inputLine("add-title \"标题2.1\" at \"标题2\"");
        bm.inputLine("add-title \"标题2.1\" at \"标题2\"");
        bm.inputLine("add-title \"标题2.2\" at \"标题2\"");
        bm.inputLine("delete-title \"标题2.1\"");
        bm.inputLine("show-tree");
        assertEquals("└──标题\n" +
                "   ├──标题1\n" +
                "   ├──标题2\n" +
                "   │  └──标题2.2\n" +
                "   └──标题3\n", bytes.toString());
        bytes.reset();
        bm.inputLine("delete-title \"标题2\"");
        bm.inputLine("show-tree");
        assertEquals("└──标题\n" +
                "   ├──标题1\n" +
                "   └──标题3\n", bytes.toString());
    }

    @Test
    public void deleteBookmark() {
        bm.inputLine("open \"new.bmk\"");
        bm.inputLine("add-title \"标题\"");
        bm.inputLine("add-title \"标题2\" at \"标题\"");
        bm.inputLine("add-title \"标题1\" at \"标题\"");
        bm.inputLine("add-title \"标题2.1\" at \"标题2\"");
        bm.inputLine("add-bookmark \"linkage\"@\"www.baidu.com\" at \"标题1\"");
        bm.inputLine("add-bookmark \"link\"@\"www.baidu.com\" at \"标题1\"");
        bm.inputLine("add-bookmark \"link\"@\"www.baidu.com\" at \"标题2\"");
        bm.inputLine("delete-bookmark \"link\"");
        bm.inputLine("show-tree");
        assertEquals("└──标题\n" +
                "   ├──标题2\n" +
                "   │  └──标题2.1\n" +
                "   └──标题1\n" +
                "      └──\"linkage\"\n", bytes.toString());
    }

    @Test
    public void readBookmark() {
        bm.inputLine("open \"standard.bmk\"");
        bm.inputLine("read-bookmark \"JFP\"");
        bm.inputLine("show-tree");
        assertEquals("└──个人收藏\n" +
                "   ├──课程\n" +
                "   │  └──\"elearning\"\n" +
                "   ├──参考资料\n" +
                "   │  ├──\"Markdown Guide\"\n" +
                "   │  ├──函数式\n" +
                "   │  │  └──\"*JFP\"\n" +
                "   │  └──面向对象\n" +
                "   └──待阅读\n" +
                "      └──\"Category Theory\"\n", bytes.toString());
        bytes.reset();
        bm.inputLine("save");
        bm.inputLine("open \"standard.bmk\"");
        bm.inputLine("show-tree");
        assertEquals("└──个人收藏\n" +
                "   ├──课程\n" +
                "   │  └──\"elearning\"\n" +
                "   ├──参考资料\n" +
                "   │  ├──\"Markdown Guide\"\n" +
                "   │  ├──函数式\n" +
                "   │  │  └──\"JFP\"\n" +
                "   │  └──面向对象\n" +
                "   └──待阅读\n" +
                "      └──\"Category Theory\"\n", bytes.toString());
    }

    @Test
    public void undo() {
        bm.inputLine("open \"new.bmk\"");
        bm.inputLine("add-title \"标题\"");
        bm.inputLine("add-title \"标题2\" at \"标题\"");
        bm.inputLine("add-title \"标题1\" at \"标题\"");
        bm.inputLine("add-title \"标题2.1\" at \"标题2\"");
        bm.inputLine("add-bookmark \"linkage\"@\"www.baidu.com\" at \"标题1\"");
        bm.inputLine("add-bookmark \"link\"@\"www.baidu.com\" at \"标题1\"");
        bm.inputLine("add-bookmark \"link\"@\"www.baidu.com\" at \"标题2\"");
        bm.inputLine("delete-bookmark \"link\"");
        bm.inputLine("undo");
        bm.inputLine("undo");
        bm.inputLine("show-tree");
        assertEquals("└──标题\n" +
                "   ├──标题2\n" +
                "   │  └──标题2.1\n" +
                "   └──标题1\n" +
                "      ├──\"linkage\"\n" +
                "      └──\"link\"\n", bytes.toString());
    }

    @Test
    public void redo() {
        bm.inputLine("open \"new.bmk\"");
        bm.inputLine("add-title \"标题\"");
        bm.inputLine("add-title \"标题2\" at \"标题\"");
        bm.inputLine("add-title \"标题1\" at \"标题\"");
        bm.inputLine("add-title \"标题2.1\" at \"标题2\"");
        bm.inputLine("add-bookmark \"linkage\"@\"www.baidu.com\" at \"标题1\"");
        bm.inputLine("add-bookmark \"link\"@\"www.baidu.com\" at \"标题1\"");
        bm.inputLine("add-bookmark \"link\"@\"www.baidu.com\" at \"标题2\"");
        bm.inputLine("delete-bookmark \"link\"");
        bm.inputLine("undo");
        bm.inputLine("redo");
        bm.inputLine("show-tree");
        assertEquals("└──标题\n" +
                "   ├──标题2\n" +
                "   │  └──标题2.1\n" +
                "   └──标题1\n" +
                "      └──\"linkage\"\n", bytes.toString());
    }

    @Test
    public void undoNull() {
        ReversibleManager.refresh();
        bm.inputLine("open \"undoNull.bmk\"");
        bm.inputLine("undo");
        assertEquals("没有可以undo的指令\n", bytes.toString());
    }

    @Test
    public void redoNull() {
        bm.inputLine("open \"standard.bmk\"");
        bm.inputLine("redo");
        assertEquals("没有可以redo的指令\n", bytes.toString());
        bytes.reset();
        bm.inputLine("add-title \"123\"");
        bm.inputLine("undo");
        bm.inputLine("add-title \"123\"");
        bm.inputLine("redo");
        assertEquals("没有可以redo的指令\n", bytes.toString());
    }
}
