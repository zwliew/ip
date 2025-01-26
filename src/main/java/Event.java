import java.time.LocalDate;
import java.time.format.DateTimeParseException;

public final class Event extends Task {
  protected LocalDate from;
  protected LocalDate to;

  public Event(String description, String from, String to) throws PeepoException {
    super(description, false);
  }

  public Event(String description, String from, String to, boolean isDone) throws PeepoException {
    super(description, isDone);
    try {
      this.from = LocalDate.parse(from);
      this.to = LocalDate.parse(to);
    } catch (DateTimeParseException e) {
      throw new PeepoException("The date format is invalid. Please use yyyy-mm-dd.");
    }
  }

  public static Event fromInput(String input) throws PeepoException {
    return fromInput(input, false);
  }

  public static Event fromInput(String input, boolean isDone) throws PeepoException {
    var text = input.split(" /from ");
    if (text.length < 2) {
      throw new PeepoException("The description, start, and end time of an event cannot be empty.");
    }
    final var desc = text[0];
    text = text[1].split(" /to ");
    if (text.length < 2) {
      throw new PeepoException("The description, start, and end time of an event cannot be empty.");
    }
    return new Event(desc, text[0], text[1], isDone);
  }

  @Override
  public String toString() {
    return "[E]" + super.toString() + " (from: " + from + " to: " + to + ")";
  }

  @Override
  public String toFileString() {
    return String.format("E | %d | %s /from %s /to %s", isDone ? 1 : 0, description, from, to);
  }
}
