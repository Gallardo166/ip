package bibot.command;

import bibot.Storage;
import bibot.Ui;
import bibot.task.TaskList;

/**
 * Represents the executable command that displays all tasks in the task list.
 */
public class ListCommand extends Command {
    private static final String REPLY_STRING_FORMAT = "Here are the tasks in your list: \n%s";

    @Override
    public String execute(TaskList taskList, Ui ui, Storage storage) {
        return String.format(REPLY_STRING_FORMAT, ui.getTaskListString(taskList));
    }    
}
