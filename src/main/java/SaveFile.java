import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.nio.file.StandardOpenOption;
import java.util.ArrayList;
import java.util.stream.Stream;

public class SaveFile {
  private static final String SAVE_FILE_PATH = "./data/peepo.txt";

  public static void saveToFile(final ArrayList<Task> tasks) throws IOException {
    final StringBuilder sb = new StringBuilder();
    for (final var task : tasks) {
      sb.append(task.toFileString()).append('\n');
    }
    final Path path = Paths.get(SAVE_FILE_PATH);
    if (!Files.exists(path)) {
      Files.createDirectories(path.getParent());
    }
    Files.writeString(path, sb.toString(), StandardOpenOption.CREATE, StandardOpenOption.TRUNCATE_EXISTING);
  }

  public static ArrayList<Task> loadFromFile() throws IOException, PeepoException {
    final var tasks = new ArrayList<Task>();
    final Path path = Paths.get(SAVE_FILE_PATH);
    if (!Files.exists(path) || !Files.isReadable(path)) {
      return tasks;
    }
    try (Stream<String> lines = Files.lines(path)) {
      for (var it = lines.iterator(); it.hasNext();) {
        tasks.add(Task.fromFileString(it.next()));
      }
    }
    return tasks;
  }
}
