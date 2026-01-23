public class TaskList {
  private Task[] tasks = new Task[100];
  private int latestId = -1;

  public void add(Task task) {
    latestId += 1;
    tasks[latestId] = task;
  }

  public void markTask(int index) throws BibotException {
    if (index < 0 || index > latestId) {
      throw new BibotException("There's no such task!");
    } else {
      tasks[index].markAsDone();
    }
  }

  public void unmarkTask(int index) throws BibotException {
    if (index < 0 || index > latestId) {
      throw new BibotException("There's no such task!");
    } else {
      tasks[index].markAsUndone();
    }
  }

  public Task get(int index) throws BibotException {
    if (index < 0 || index > latestId) {
      throw new BibotException("There's no such task!");
    } else {
      return tasks[index]; 
    }
  }

  public void display() {
    for (int i = 0; i <= latestId; i++) {
      System.out.printf("      %d. %s\n", i + 1, tasks[i]);
    }
  }

  public void printLength() {
    String taskNum = latestId == 0
                     ? "1 task"
                     : String.format("%d tasks", latestId + 1);
    System.out.printf("     Now you have %s in the list.\n", taskNum);
  }
}
