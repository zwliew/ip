package task;

public final class Todo extends Task {
    public Todo(String description) {
        super(description);
    }

    public Todo(String description, boolean isDone) {
        super(description, isDone);
    }

    public static Todo fromInput(String input) {
        return new Todo(input);
    }

    /**
     * Converts the given input into a Todo.
     *
     * @param input The description of the Todo.
     * @param isDone A flag indicating whether the Todo is already completed.
     * @return A Todo created using the provided description and completion status.
     */
    public static Todo fromInput(String input, boolean isDone) {
        return new Todo(input, isDone);
    }

    @Override
    public String toString() {
        return "[T]" + super.toString();
    }

    /**
     * Converts the current task into a string format that can be saved to a file.
     * The format includes the task type (T), its completion status (1 for done, 0 for not done),
     * and its description.
     *
     * @return A string representation of the task suitable for file storage.
     */
    @Override
    public String toFileString() {
        return String.format("T | %d | %s", isDone ? 1 : 0, description);
    }
}
