public interface Command {
    /**
     * Executes the command and returns true if the program should exit.
     */
    boolean execute(Ui ui, TaskList tasks, Storage storage) throws PeepoException;
}
