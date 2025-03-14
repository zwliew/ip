package command;

import misc.PeepoException;
import misc.Storage;
import misc.Ui;
import task.Task;
import task.TaskList;

public class TaskCommand implements Command {
    private final Task task;

    public TaskCommand(Task task) {
        this.task = task;
    }

    @Override
    public boolean execute(Ui ui, TaskList tasks, Storage storage) throws PeepoException {
        tasks.add(task);
        storage.save(tasks);
        ui.showTaskConfirmation(task, tasks.size());
        return false;
    }

    public Task getTask() {
        return task;
    }
}
