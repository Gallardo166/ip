public class TaskList {
  private Task[] tasks = new Task[100];
  private int latestId = 0;

  public void add(Task task) {
    tasks[latestId] = task;
    latestId += 1;
  }

  public void markTask(int index) {
    tasks[index].markAsDone();
  }

  public void unmarkTask(int index) {
    tasks[index].markAsUndone();
  }

  public Task get(int index) {
    return tasks[index]; 
  }

  public void display() {
    for (int i = 0; i < latestId; i++) {
      System.out.printf("    %d. %s\n", i + 1, tasks[i]);
    }
  }

  public void printLength() {
    System.out.printf("    Now you have %d tasks in the list.\n", latestId);
  }
}
