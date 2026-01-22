public class TaskList {
  private String[] tasks = new String[100];
  private int latestId = 0;

  public void add(String task) {
    tasks[latestId] = task;
    latestId += 1;
  }

  public void display() {
    for (int i = 0; i < latestId; i++) {
      System.out.printf("    %d. %s\n", i + 1, tasks[i]);
    }
  }
}
