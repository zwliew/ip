package task;

import misc.PeepoException;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

public class TaskListTest {
    private TaskList tasks;

    @BeforeEach
    public void setUp() {
        tasks = new TaskList();
    }

    @Test
    public void get_validIdx_success() {
        final var task = new Todo("my desc");
        tasks.add(task);

        try {
            final var actualTask = tasks.get(0);
            assertEquals(task, actualTask);
        } catch (PeepoException e) {
            fail(e.getMessage());
        }
    }

    @Test
    public void get_invalidIdx_throws() {
        assertThrows(PeepoException.class, () -> tasks.get(0));
        assertThrows(PeepoException.class, () -> tasks.get(1));
    }
}
