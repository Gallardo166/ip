import java.util.Scanner;

public class Ui {
    // Solution below inspired by https://www.w3schools.com/java/java_user_input.asp
    private static Scanner commandScanner = new Scanner(System.in);

    private String dividerIndentation = " ".repeat(4);
    private String messageIndentation = " ".repeat(5);
    private String taskIndentation = " ".repeat(6);
    private String divider = "_".repeat(39);

    public String readInput() {
        return commandScanner.nextLine();
    }

    public void displayMessage(String text) {
       System.out.println(messageIndentation + text); 
    }
    
    public void displayTask(Task task) {
        System.out.println(taskIndentation + task);
    }

    public void displayTaskList(TaskList taskList) {
        int taskNumber = 1;
        for (Task task: taskList) {
            System.out.println(taskIndentation + taskNumber
                        + ". " + task);
            taskNumber++;
        }
    }
    
    public void displayTaskCount(TaskList taskList) {
        int numTasks = taskList.getLength();
        String taskString = numTasks == 1
                            ? "task"
                            : "tasks";
        System.out.println(messageIndentation + "Now you have "
                + numTasks + " " + taskString + " in the list.");
    }

    public void displayTopLine() {
        System.out.println(dividerIndentation + divider);
    }

    public void displayBottomLine() {
        System.out.println(dividerIndentation + divider + "\n");
    }

    public void displayGreeting() {
        this.displayTopLine();
        this.displayMessage("Hello! I'm Bibot!");
        this.displayMessage("What can I do for you?");
        this.displayBottomLine();
    }

    public void end() {
        commandScanner.close();
    }
}
