public final class Todo extends Task {
  public Todo(String description) {
    super(description);
  }

  public Todo(String description, boolean isDone) {
    super(description, isDone);
  }

  public static Todo fromInput(String input) {
    return new Todo(input);
  }

  public static Todo fromInput(String input, boolean isDone) {
    return new Todo(input, isDone);
  }

  @Override
  public String toString() {
    return "[T]" + super.toString();
  }

  @Override
  public String toFileString() {
    return String.format("T | %d | %s", isDone ? 1 : 0, description);
  }
}
