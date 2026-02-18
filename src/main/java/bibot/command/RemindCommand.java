package bibot.command;

import bibot.Storage;
import bibot.Ui;
import bibot.task.Task;
import bibot.task.TaskList;

/**
 * Represents the executable command that reminds the user of upcoming tasks.
 */
public class RemindCommand extends Command {
    @Override
    public String execute(TaskList taskList, Ui ui, Storage storage) {
       TaskList upcomingTasks = taskList.filter((Task task) -> task.isUpcoming());
       return "Reminder, here are your upcoming tasks: \n" + ui.getTaskListString(upcomingTasks);
    }
}
