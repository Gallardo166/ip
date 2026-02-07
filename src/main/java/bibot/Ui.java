package bibot;

import bibot.task.Task;
import bibot.task.TaskList;

/**
 * Represents component that formats replies to user.
 */
public class Ui {
    private String taskIndentation = " ".repeat(1);

    /**
     * Returns the specified <code>Task</code> as a string with task-level indentation (1).
     */
    public String getTaskString(Task task) {
        return taskIndentation + task;
    }

    /**
     * Returns a numbered list of <code>Task</code>s as strings in the
     * specified <code>TaskList</code> with task-level indentation (1).
     */
    public String getTaskListString(TaskList taskList) {
        String taskListString = "";
        int taskNumber = 1;
        for (Task task: taskList) {
            taskListString += taskIndentation + taskNumber + ". " + task + "\n";
            taskNumber++;
        }
        return taskListString;
    }
    
    /**
     * Returns a message containing the number of <code>Task</code>s
     * in the specified <code>TaskList</code>.
     */
    public String getTaskCountString(TaskList taskList) {
        int numTasks = taskList.getLength();
        String taskString = (numTasks == 1) ? "task" : "tasks";
        return "Now you have " + numTasks + " " + taskString + " in the list.";
    }
}