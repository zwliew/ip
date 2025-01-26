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
