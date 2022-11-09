package Commands.Reverse;

import java.util.Stack;

// 存储要undo redo的命令
public class ReversibleManager {
    private final Stack<Reversible> undo = new Stack<>();
    private final Stack<Reversible> redo = new Stack<>();
    private static ReversibleManager instance;

    private ReversibleManager() {
    }

    public static ReversibleManager getInstance() {
        if (instance == null) {
            instance = new ReversibleManager();
        }
        return instance;
    }

    public void record(Reversible r) {
        undo.push(r);
        redo.clear();
    }

    public boolean canUndo() {
        return !undo.isEmpty();
    }

    public boolean canRedo() {
        return !redo.isEmpty();
    }

    public void undo() {
        if (canUndo()) {
            Reversible r = undo.pop();
            r.undo();
            redo.push(r);
        }
    }

    public void redo() {
        if (canRedo()) {
            Reversible r = redo.pop();
            r.redo();
            undo.push(r);
        }
    }
}
