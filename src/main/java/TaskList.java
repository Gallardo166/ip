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
      System.out.printf("      %d. %s\n", i + 1, tasks[i]);
    }
  }

  public void printLength() {
    String taskNum = latestId == 1
                     ? "1 task"
                     : String.format("%d tasks", latestId);
    System.out.printf("     Now you have %s in the list.\n", taskNum);
  }
}
