import java.time.LocalDate;
import java.time.format.DateTimeParseException;

public final class Deadline extends Task {
  protected LocalDate by;

  public Deadline(String description, String by) throws PeepoException {
    super(description, false);
  }

  public Deadline(String description, String by, boolean isDone) throws PeepoException {
    super(description, isDone);
    try {
      this.by = LocalDate.parse(by);
    } catch (DateTimeParseException e) {
      throw new PeepoException("The date format is invalid. Please use yyyy-mm-dd.");
    }
  }

  public static Deadline fromInput(String input) throws PeepoException {
    return fromInput(input, false);
  }

  public static Deadline fromInput(String input, boolean isDone) throws PeepoException {
    final var text = input.split(" /by ");
    if (text.length != 2) {
      throw new PeepoException("The description and deadline of a deadline cannot be empty.");
    }
    return new Deadline(text[0], text[1], isDone);
  }

  @Override
  public String toString() {
    return "[D]" + super.toString() + " (by: " + by + ")";
  }

  @Override
  public String toFileString() {
    return String.format("D | %d | %s /by %s", isDone ? 1 : 0, description, by);
  }
}
