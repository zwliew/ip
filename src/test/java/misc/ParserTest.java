package misc;

import command.*;
import org.junit.jupiter.api.Test;
import task.Deadline;
import task.Event;
import task.Todo;

import static org.junit.jupiter.api.Assertions.*;

public class ParserTest {
    @Test
    public void parse_validCommands_success() {
        try {
            assertInstanceOf(ByeCommand.class, Parser.parse("bye"));

            assertInstanceOf(ListCommand.class, Parser.parse("list"));

            final var markCmd = (MarkCommand) Parser.parse("mark 1");
            assertEquals(0, markCmd.getIdx());

            final var unmarkCmd = (UnmarkCommand) Parser.parse("unmark 10");
            assertEquals(9, unmarkCmd.getIdx());

            final var deleteCmd = (DeleteCommand) Parser.parse("delete  13 ");
            assertEquals(12, deleteCmd.getIdx());

            final var byeCmd = (ByeCommand) Parser.parse("bye");
            assertInstanceOf(ByeCommand.class, byeCmd);

            final var todoCmd = (TaskCommand) Parser.parse("todo i want to do a todo today to-doo-doo");
            assertInstanceOf(Todo.class, todoCmd.getTask());

            final var deadlineCmd = (TaskCommand) Parser.parse("deadline i want to do a deadline today to-doo-doo /by 2025-09-13");
            assertInstanceOf(Deadline.class, deadlineCmd.getTask());

            final var eventCmd = (TaskCommand) Parser.parse("event i want to host an event /from 2025-09-13 /to 2026-10-27");
            assertInstanceOf(Event.class, eventCmd.getTask());
        } catch (PeepoException e) {
            fail(e.getMessage());
        }
    }

    @Test
    public void parse_invalidCommands_throws() {
        assertThrows(PeepoException.class, () -> Parser.parse(""));
        assertThrows(PeepoException.class, () -> Parser.parse("never gonna give u up "));
        assertThrows(PeepoException.class, () -> Parser.parse("too never gonna let u down "));
        assertThrows(PeepoException.class, () -> Parser.parse("deadline oh nyoo i need to hurry /b "));
        assertThrows(PeepoException.class, () -> Parser.parse("deadline oh nyoo i need to hurry /b invalidDateTime"));
        assertThrows(PeepoException.class, () -> Parser.parse("event my event title rox /to 2025-03-13"));
    }
}
