package Commands;

import Commands.Reverse.ReversibleManager;

public class UndoCommand extends Command {

    @Override
    public boolean execute() {
        ReversibleManager rm = ReversibleManager.getInstance();
        if (rm.canUndo()) {
            rm.undo();
            return true;
        } else {
            System.out.println("没有可以undo的指令");
            return false;
        }
    }
}
