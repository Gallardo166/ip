package bibot;

import bibot.task.Task;
import bibot.task.TaskList;

import java.util.Scanner;

/**
 * Represents component that handles output display to user.
 */
public class Ui {
    // Solution below inspired by https://www.w3schools.com/java/java_user_input.asp
    private static Scanner commandScanner = new Scanner(System.in);

    private String dividerIndentation = " ".repeat(4);
    private String messageIndentation = " ".repeat(5);
    private String taskIndentation = " ".repeat(6);
    private String divider = "_".repeat(39);

    /**
     * Reads the user input.
     * Reads up to a newline.
     * 
     * @return User input as a <code>String</code>.
     */
    public String readInput() {
        return commandScanner.nextLine();
    }

    /**
     * Prints the specified text with message-level indentation (5).
     */
    public void displayMessage(String text) {
        System.out.println(messageIndentation + text);
    }
    
    /**
     * Prints the specified <code>Task</code> as a string with task-level indentation (6).
     */
    public void displayTask(Task task) {
        System.out.println(taskIndentation + task);
    }

    /**
     * Prints numbered list of <code>Task</code>s as strings in the
     * specified <code>TaskList</code> with task-level indentation (6).
     */
    public void displayTaskList(TaskList taskList) {
        int taskNumber = 1;
        for (Task task: taskList) {
            System.out.println(taskIndentation + taskNumber + ". " + task);
            taskNumber++;
        }
    }
    
    /**
     * Prints a message containing the number of <code>Task</code>s
     * in the specified <code>TaskList</code>.
     */
    public void displayTaskCount(TaskList taskList) {
        int numTasks = taskList.getLength();
        String taskString = (numTasks == 1) ? "task" : "tasks";
        System.out.println(messageIndentation + "Now you have " + numTasks + " "
                + taskString + " in the list.");
    }

    /**
     * Prints a horizontal divider without an extra newline.
     */
    public void displayTopLine() {
        System.out.println(dividerIndentation + divider);
    }

    /**
     * Prints a horizontal divider with an extra newline.
     */
    public void displayBottomLine() {
        System.out.println(dividerIndentation + divider + "\n");
    }

    /**
     * Prints a greeting to the user.
     */
    public void displayGreeting() {
        this.displayTopLine();
        this.displayMessage("Hello! I'm Bibot!");
        this.displayMessage("What can I do for you?");
        this.displayBottomLine();
    }

    /**
     * Stops the <code>Ui</code> from reading input.
     */
    public void end() {
        commandScanner.close();
    }
}