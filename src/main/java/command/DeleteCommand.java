package command;

import misc.PeepoException;
import misc.Storage;
import misc.Ui;
import task.TaskList;

public class DeleteCommand implements Command {
    private final int idx;

    public DeleteCommand(int idx) {
        this.idx = idx;
    }

    @Override
    public boolean execute(Ui ui, TaskList tasks, Storage storage) throws PeepoException {
        final var task = tasks.remove(idx);
        storage.save(tasks);
        ui.showDeleteConfirmation(task, tasks.size());
        return false;
    }

    public int getIdx() {
        return idx;
    }
}
