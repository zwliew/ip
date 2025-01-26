public final class Deadline extends Task {
  protected String by;

  public Deadline(String description, String by) {
    super(description);
    this.by = by;
  }

  public Deadline(String description, String by, boolean isDone) {
    super(description, isDone);
    this.by = by;
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
