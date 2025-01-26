package command;

import misc.PeepoException;
import misc.Storage;
import misc.Ui;
import task.TaskList;

public class UnmarkCommand implements Command {
    private final int idx;

    public UnmarkCommand(int idx) {
        this.idx = idx;
    }

    @Override
    public boolean execute(Ui ui, TaskList tasks, Storage storage) throws PeepoException {
        final var task = tasks.markAsUndone(idx);
        storage.save(tasks);
        ui.showUnmarkTaskConfirmation(task);
        return false;
    }
}
