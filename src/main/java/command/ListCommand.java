package command;

import misc.Storage;
import misc.Ui;
import task.TaskList;

public class ListCommand implements Command {
    @Override
    public boolean execute(Ui ui, TaskList tasks, Storage storage) {
        ui.showTasks(tasks);
        return false;
    }
}
