public abstract class Task {
  protected boolean isDone;
  protected String description;

  public Task(String description) {
    this.description = description;
    this.isDone = false;
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
}
