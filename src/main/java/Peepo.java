import java.io.IOException;
import java.util.ArrayList;
import java.util.Scanner;

public class Peepo {
  private static final String LINE = "    ____________________________________________________________";
  private static final String INDENT = "     ";

  private ArrayList<Task> texts;

  public Peepo(ArrayList<Task> texts) {
    this.texts = texts;
  }

  private <T extends Task> void addTask(ArrayList<Task> texts, T task) {
    texts.add(task);
    printLns(String.format("Got it. I've added this task:\n  %s\nNow you have %d tasks in the list.", task.toString(),
        texts.size()));
  }

  private void printLns(String lines) {
    System.out.println(LINE);
    for (var line : lines.split("\n")) {
      System.out.println(INDENT + line);
    }
    System.out.println(LINE + '\n');
  }

  public void hello() {
    printLns("Hi! I'm Peepo.\nWhat can I do for you?");
  }

  public void farewell() {
    printLns("Bye. Hope to see you again soon!");
  }

  public void handleInput(String input) throws PeepoException, IOException {
    final var parts = input.split(" ", 2);
    final var cmd = parts[0];
    final var rest = parts.length == 2 ? parts[1] : "";

    StringBuilder sb = new StringBuilder();
    if (cmd.equals("list")) {
      sb.append("Here are the tasks in your list:\n");
      for (int i = 0; i < texts.size(); i++) {
        final var task = texts.get(i);
        sb.append(i + 1).append(". ").append(task.toString()).append('\n');
      }
      printLns(sb.toString());
      return;
    }

    if (cmd.equals("mark")) {
      final var idx = Integer.parseInt(rest) - 1;
      if (idx < 0 || idx >= texts.size()) {
        throw new PeepoException("The task number is out of range.");
      }
      final var task = texts.get(idx);
      task.markAsDone();
      sb.append("Nice! I've marked this task as done:\n");
      sb.append("  ").append(task.toString());
    } else if (cmd.equals("unmark")) {
      final var idx = Integer.parseInt(rest) - 1;
      if (idx < 0 || idx >= texts.size()) {
        throw new PeepoException("The task number is out of range.");
      }
      final var task = texts.get(idx);
      task.markAsUndone();
      sb.append("OK, I've marked this task as not done yet:\n");
      sb.append("  ").append(task.toString());
    } else if (cmd.equals("todo")) {
      final var todo = Todo.fromInput(rest);
      addTask(texts, todo);
    } else if (cmd.equals("deadline")) {
      final var deadline = Deadline.fromInput(rest);
      addTask(texts, deadline);
    } else if (cmd.equals("event")) {
      final var event = Event.fromInput(rest);
      addTask(texts, event);
    } else if (cmd.equals("delete")) {
      final var idx = Integer.parseInt(rest) - 1;
      if (idx < 0 || idx >= texts.size()) {
        throw new PeepoException("The task number is out of range.");
      }
      final var task = texts.remove(idx);
      sb.append("Noted. I've removed this task:\n");
      sb.append("  ").append(task.toString()).append('\n');
      sb.append("Now you have ").append(texts.size()).append(" tasks in the list.");
    } else {
      throw new PeepoException("I'm sorry, but I don't know what that means.");
    }

    if (sb.length() > 0) {
      printLns(sb.toString());
    }

    SaveFile.saveToFile(texts);
  }

  private static void printError(Peepo peepo, String message) {
    peepo.printLns(String.format("OOPS!!! %s", message));
  }

  public static void main(String[] args) {

    ArrayList<Task> tasks;
    try {
      tasks = SaveFile.loadFromFile();
    } catch (PeepoException | IOException e) {
      System.out.println("Failed to load tasks from file, falling back to empty list: " + e.getMessage());
      tasks = new ArrayList<>();
    }

    Peepo peepo = new Peepo(tasks);
    peepo.hello();
    try (Scanner scanner = new Scanner(System.in)) {
      for (String input = scanner.nextLine(); !input.equals("bye"); input = scanner.nextLine()) {
        try {
          peepo.handleInput(input);
        } catch (PeepoException | IOException e) {
          printError(peepo, e.getMessage());
        }
      }
    }
    peepo.farewell();
  }
}
