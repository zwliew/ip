package command;

import misc.PeepoException;
import misc.Storage;
import misc.Ui;
import task.TaskList;

public class FindCommand implements Command {
    private final String term;

    public FindCommand(String term) {
        this.term = term;
    }

    @Override
    public boolean execute(Ui ui, TaskList tasks, Storage storage) throws PeepoException {
        final var foundTasks = tasks.find(term);
        ui.showFoundTasks(foundTasks);
        return false;
    }
}
