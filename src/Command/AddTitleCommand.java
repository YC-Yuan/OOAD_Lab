package Command;

public class AddTitleCommand extends Command implements Reversible {
    private String name;
    private String directory;

    public AddTitleCommand(String name, String directory) {
        super();
        this.name = name;
        this.directory = directory;
    }

    @Override
    public void execute() {

    }

    @Override
    public void undo() {

    }
}
