import java.util.Scanner;

public class Peepo {
  private static final String LINE = "    ____________________________________________________________";
  private static final String INDENT = "     ";

  public static void main(String[] args) {
    System.out.println(LINE);
    System.out.println(INDENT + "Hi! I'm Peepo.");
    System.out.println(INDENT + "What can I do for you?");
    System.out.println(LINE + '\n');

    try (Scanner scanner = new Scanner(System.in)) {
      for (String input = scanner.nextLine(); !"bye".equals(input); input = scanner.nextLine()) {
        System.out.println(LINE);
        System.out.println(INDENT + input);
        System.out.println(LINE + '\n');
      }
    }
    System.out.println(LINE);
    System.out.println(INDENT + "Bye. Hope to see you again soon!");
    System.out.println(LINE);
  }
}
