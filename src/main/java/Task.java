public class Task {
  private String description;
  private boolean isDone = false;
  
  public Task(String description) {
    this.description = description;
  }

  public void markAsDone() {
    this.isDone = true;
  }

  public void markAsUndone() {
    this.isDone = false;
  }

  @Override
  public String toString() {
    if (this.isDone) {
      return String.format("[X] %s", this.description);
    } else {
      return String.format("[ ] %s", this.description);
    }
  }
}