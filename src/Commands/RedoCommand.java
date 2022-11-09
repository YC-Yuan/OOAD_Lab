package Commands;

import Commands.Reverse.ReversibleManager;

public class RedoCommand extends Command {

    @Override
    public boolean execute() {
        ReversibleManager rm = ReversibleManager.getInstance();
        if (rm.canRedo()) {
            rm.redo();
            return true;
        } else {
            System.out.println("没有可以redo的指令");
            return false;
        }
    }
}
