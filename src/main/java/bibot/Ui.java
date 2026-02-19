package bibot;

import bibot.task.Task;
import bibot.task.TaskList;

/**
 * Represents component that formats replies to user.
 */
public class Ui {

    private static final String EMPTY_TASK_LIST_STRING_FORMAT = "None!";
    private static final String TASK_STRING_FORMAT = " %s";
    private static final String TASK_LIST_STRING_FORMAT = " %d. %s\n";
    private static final String TASK_COUNT_STRING_FORMAT = "Now you have %d %s in the list.";

    /**
     * Returns the specified <code>Task</code> as a string with task-level indentation (1).
     */
    public String getTaskString(Task task) {
        return String.format(TASK_STRING_FORMAT, task);
    }

    /**
     * Returns a numbered list of <code>Task</code>s in the specified <code>TaskList</code> 
     * as a string with task-level indentation (1).
     */
    public String getTaskListString(TaskList taskList) {
        if (taskList.getLength() == 0) {
            return EMPTY_TASK_LIST_STRING_FORMAT;
        }
        String taskListString = "";
        int taskNumber = 1;
        for (Task task: taskList) {
            taskListString += String.format(TASK_LIST_STRING_FORMAT, taskNumber, task);
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
        return String.format(TASK_COUNT_STRING_FORMAT, numTasks, taskString);
    }
}
