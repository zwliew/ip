import java.io.IOException;
import java.util.Scanner;

public class Peepo {
    private final Ui ui;
    private final Storage storage;
    private TaskList tasks;

    public Peepo(String filePath) {
        ui = new Ui();
        storage = new Storage(filePath);

        try {
            tasks = new TaskList(storage.load());
        } catch (PeepoException | IOException e) {
            System.out.println("Failed to load tasks from file, falling back to empty list: " + e.getMessage());
            tasks = new TaskList();
        }
    }

    public static void main(String[] args) {
        new Peepo("data/tasks.txt").run();
    }

    public void run() {
        ui.showWelcome();
        try (Scanner scanner = new Scanner(System.in)) {
            boolean shouldExit = false;
            while (!shouldExit) {
                final var input = scanner.nextLine();
                try {
                    Command cmd = Parser.parse(input);
                    shouldExit = cmd.execute(ui, tasks, storage);
                } catch (PeepoException e) {
                    ui.showErr(e.getMessage());
                }
            }
        }
        ui.showGoodbye();
    }
}
