package misc;

import task.Task;
import task.TaskList;

import java.util.List;

public class Ui {
    private static final String LINE = "    ____________________________________________________________";
    private static final String INDENT = "     ";

    private void show(String lines) {
        System.out.println(LINE);
        for (var line : lines.split("\n")) {
            System.out.println(INDENT + line);
        }
        System.out.println(LINE + '\n');
    }

    public void showWelcome() {
        show("Hi! I'm Peepo.\nWhat can I do for you?");
    }

    public void showGoodbye() {
        show("Bye. Hope to see you again soon!");
    }

    public void showTaskConfirmation(Task task, int numTasks) {
        show(String.format("Got it. I've added this task:\n  %s\nNow you have %d tasks in the list.", task.toString(),
                numTasks));
    }

    public void showTasks(TaskList tasks) {
        final StringBuilder sb = new StringBuilder();
        sb.append("Here are the tasks in your list:\n");
        int i = 0;
        for (final var task : tasks) {
            sb.append(++i).append(". ").append(task.toString()).append('\n');
        }
        show(sb.toString());
    }

    /**
     * Displays a confirmation message indicating that a specific task
     * has been marked as done.
     *
     * @param task The task object that was marked as done.
     */
    public void showMarkTaskConfirmation(Task task) {
        String sb = "Nice! I've marked this task as done:\n" +
                "  " + task.toString();
        show(sb);
    }

    /**
     * Displays a confirmation message indicating that a specific task has been marked
     * as not done yet.
     *
     * @param task The task object that was marked as not done.
     */
    public void showUnmarkTaskConfirmation(Task task) {
        String sb = "OK, I've marked this task as not done yet:\n" +
                "  " + task.toString();
        show(sb);
    }

    /**
     * Displays a confirmation message indicating that a specific task has been deleted
     * and shows the updated number of tasks remaining in the list.
     *
     * @param task     The task object that was removed from the list.
     * @param numTasks The current number of tasks remaining in the list.
     */
    public void showDeleteConfirmation(Task task, int numTasks) {
        String sb = "Noted. I've removed this task:\n" +
                "  " + task.toString() + '\n' +
                "Now you have " + numTasks + " tasks in the list.";
        show(sb);
    }

    /**
     * Displays an error message formatted with a specific prefix to indicate an issue.
     *
     * @param message The error message to be displayed.
     */
    public void showErr(String message) {
        show(String.format("OOPS!!! %s", message));
    }

    public void showFoundTasks(List<Task> foundTasks) {
        final StringBuilder sb = new StringBuilder();
        sb.append("Here are the matching tasks in your list:\n");
        int i = 0;
        for (final var task : foundTasks) {
            sb.append(++i).append(". ").append(task.toString()).append('\n');
        }
        show(sb.toString());
    }
}
