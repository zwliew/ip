package misc;

import task.Task;
import task.TaskList;

import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.nio.file.StandardOpenOption;
import java.util.ArrayList;
import java.util.stream.Stream;

public class Storage {
    private final String filePath;

    public Storage(String filePath) {
        this.filePath = filePath;
    }

    /**
     * Saves the current list of tasks to the storage file.
     *
     * @param tasks The list of tasks to be saved.
     * @throws PeepoException If an error occurs while creating directories or writing to the file.
     */
    public void save(final TaskList tasks) throws PeepoException {
        final StringBuilder sb = new StringBuilder();
        for (final var task : tasks) {
            sb.append(task.toFileString()).append('\n');
        }
        final Path path = Paths.get(filePath);
        try {
            if (!Files.exists(path)) {
                Files.createDirectories(path.getParent());
            }
            Files.writeString(path, sb.toString(), StandardOpenOption.CREATE, StandardOpenOption.TRUNCATE_EXISTING);
        } catch (IOException e) {
            throw new PeepoException("Failed to save tasks to file, continuing.");
        }
    }

    /**
     * Loads tasks from the storage file specified by the filePath field.
     * If the file does not exist or is not readable, an empty list of tasks is returned.
     * Each line in the file is parsed into a Task object using the Task.fromFileString method.
     *
     * @return An ArrayList of Task objects loaded from the storage file. If the file is missing
     *         or unreadable, an empty list is returned.
     * @throws IOException If an error occurs while reading the file.
     * @throws PeepoException If an error occurs while parsing a task from the file.
     */
    public ArrayList<Task> load() throws IOException, PeepoException {
        final var tasks = new ArrayList<Task>();
        final Path path = Paths.get(filePath);
        if (!Files.exists(path) || !Files.isReadable(path)) {
            return tasks;
        }
        try (Stream<String> lines = Files.lines(path)) {
            for (var it = lines.iterator(); it.hasNext(); ) {
                tasks.add(Task.fromFileString(it.next()));
            }
        }
        return tasks;
    }
}
