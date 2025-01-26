package misc;

import command.*;
import task.Deadline;
import task.Event;
import task.Todo;

public class Parser {
    public static Command parse(String input) throws PeepoException {
        final var parts = input.split(" ", 2);
        final var cmd = parts[0];
        final var rest = parts.length == 2 ? parts[1].strip() : "";

        return switch (cmd) {
            case "list" -> new ListCommand();
            case "mark" -> new MarkCommand(Integer.parseInt(rest) - 1);
            case "unmark" -> new UnmarkCommand(Integer.parseInt(rest) - 1);
            case "todo" -> new TaskCommand(Todo.fromInput(rest));
            case "deadline" -> new TaskCommand(Deadline.fromInput(rest));
            case "event" -> new TaskCommand(Event.fromInput(rest));
            case "delete" -> new DeleteCommand(Integer.parseInt(rest) - 1);
            case "bye" -> new ByeCommand();
            default -> throw new PeepoException("I'm sorry, but I don't know what that means.");
        };
    }
}
