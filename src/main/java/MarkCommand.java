public class MarkCommand implements Command {
    private final int idx;

    public MarkCommand(int idx) {
        this.idx = idx;
    }

    @Override
    public boolean execute(Ui ui, TaskList tasks, Storage storage) throws PeepoException {
        final var task = tasks.markAsDone(idx);
        storage.save(tasks);
        ui.showMarkTaskConfirmation(task);
        return false;
    }
}
