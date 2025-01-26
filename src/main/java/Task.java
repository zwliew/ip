public abstract class Task {
  protected boolean isDone;
  protected String description;

  public Task(String description) {
    this.description = description;
    this.isDone = false;
  }

  public Task(String description, boolean isDone) {
    this.description = description;
    this.isDone = isDone;
  }

  public void markAsDone() {
    this.isDone = true;
  }

  public void markAsUndone() {
    this.isDone = false;
  }

  public String getStatusIcon() {
    return isDone ? "X" : " ";
  }

  @Override
  public String toString() {
    return "[" + getStatusIcon() + "] " + description;
  }

  public abstract String toFileString();

  public static Task fromFileString(String line) throws PeepoException {
    final var parts = line.split("\\|", 3);
    final var type = parts[0].strip();
    final var isDone = parts[1].strip().equals("1");
    final var rest = parts.length == 3 ? parts[2].strip() : "";
    switch (type) {
      case "T":
        return Todo.fromInput(rest, isDone);
      case "D":
        return Deadline.fromInput(rest, isDone);
      case "E":
        return Event.fromInput(rest, isDone);
      default:
        throw new PeepoException("Unknown task type: " + type);
    }
  }
}
