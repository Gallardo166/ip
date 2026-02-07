package bibot.command;

import bibot.Storage;
import bibot.Ui;
import bibot.task.TaskList;

/**
 * Represents the executable command that displays all tasks in the task list.
 */
public class ListCommand extends Command {
    @Override
    public String execute(TaskList taskList, Ui ui, Storage storage) {
        return "Here are the tasks in your list: \n" + ui.getTaskListString(taskList);
    }    
}