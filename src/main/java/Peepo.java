import java.util.ArrayList;
import java.util.Scanner;

public class Peepo {
  private static final String LINE = "    ____________________________________________________________";
  private static final String INDENT = "     ";

  public static void main(String[] args) {
    System.out.println(LINE);
    System.out.println(INDENT + "Hi! I'm Peepo.");
    System.out.println(INDENT + "What can I do for you?");
    System.out.println(LINE + '\n');

    final ArrayList<Task> texts = new ArrayList<>();

    try (Scanner scanner = new Scanner(System.in)) {
      for (String input = scanner.nextLine(); !"bye".equals(input); input = scanner.nextLine()) {
        System.out.println(LINE);
        if ("list".equals(input)) {
          System.out.println(INDENT + "Here are the tasks in your list:");
          for (int i = 0; i < texts.size(); i++) {
            final var task = texts.get(i);
            System.out.println(INDENT + (i + 1) + ". " + task.toString());
          }
        } else if (input.startsWith("mark ")) {
          final var idx = Integer.parseInt(input.substring(5)) - 1;
          assert idx >= 0 && idx < texts.size();
          final var task = texts.get(idx);
          task.markAsDone();

          System.out.println(INDENT + "Nice! I've marked this task as done:");
          System.out.println(INDENT + "  " + task.toString());
        } else if (input.startsWith("unmark ")) {
          final var idx = Integer.parseInt(input.substring(7)) - 1;
          assert idx >= 0 && idx < texts.size();
          final var task = texts.get(idx);
          task.markAsUndone();

          System.out.println(INDENT + "OK, I've marked this task as not done yet:");
          System.out.println(INDENT + "  " + task.toString());
        } else if (input.startsWith("todo ")) {
          final var description = input.substring(5);
          final var task = new Todo(description);
          texts.add(task);

          System.out.println(INDENT + "Got it. I've added this task:");
          System.out.println(INDENT + "  " + task.toString());
          System.out.println(INDENT + "Now you have " + texts.size() + " tasks in the list.");
        } else if (input.startsWith("deadline ")) {
          final var text = input.substring(9).split(" /by ");
          assert text.length == 2;
          final var task = new Deadline(text[0], text[1]);
          texts.add(task);

          System.out.println(INDENT + "Got it. I've added this task:");
          System.out.println(INDENT + "  " + task.toString());
          System.out.println(INDENT + "Now you have " + texts.size() + " tasks in the list.");
        } else if (input.startsWith("event ")) {
          var text = input.substring(6).split(" /from ");
          assert text.length == 2;
          final var desc = text[0];
          text = text[1].split(" /to ");
          assert text.length == 2;
          final var task = new Event(desc, text[0], text[1]);
          texts.add(task);

          System.out.println(INDENT + "Got it. I've added this task:");
          System.out.println(INDENT + "  " + task.toString());
          System.out.println(INDENT + "Now you have " + texts.size() + " tasks in the list.");
        }
        System.out.println(LINE + '\n');
      }
    }
    System.out.println(LINE);
    System.out.println(INDENT + "Bye. Hope to see you again soon!");
    System.out.println(LINE);
  }
}
