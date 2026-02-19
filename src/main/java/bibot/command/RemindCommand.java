package bibot.command;

import bibot.Storage;
import bibot.Ui;
import bibot.task.Task;
import bibot.task.TaskList;

/**
 * Represents the executable command that reminds the user of upcoming tasks.
 */
public class RemindCommand extends Command {
    private static final String REPLY_STRING_FORMAT = "Reminder, here are your upcoming tasks: \n%s";

    @Override
    public String execute(TaskList taskList, Ui ui, Storage storage) {
       TaskList upcomingTasks = taskList.filter((Task task) -> task.isUpcoming());
       return String.format(REPLY_STRING_FORMAT, ui.getTaskListString(upcomingTasks));
    }
}
