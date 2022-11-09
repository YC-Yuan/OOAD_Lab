package Commands.Reverse;

public interface Reversible {
    void undo();

    void redo();

    default void executeWithRecord() {
        ReversibleManager manager = ReversibleManager.getInstance();
        manager.record(this);

    }
}
