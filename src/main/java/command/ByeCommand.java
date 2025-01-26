package command;

import misc.Storage;
import misc.Ui;
import task.TaskList;

public class ByeCommand implements Command {
    @Override
    public boolean execute(Ui ui, TaskList tasks, Storage storage) {
        return true;
    }
}
