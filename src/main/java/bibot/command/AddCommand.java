package bibot.command;

import bibot.BibotException;
import bibot.Storage;
import bibot.Ui;
import bibot.task.Task;
import bibot.task.TaskList;

/**
 * Represents the executable command that adds a task into the task list.
 */
public class AddCommand extends Command {
    private Task task;

    private static final String REPLY_STRING_FORMAT = "Got it. I've added this task: \n%s\n%s";

    /**
     * Constructs a new <code>AddCommand</code> representing the
     * executable command for adding a task into the task list.
     * 
     * @param task Task to be added into task list.
     */
    public AddCommand(Task task) {
        this.task = task;
    }

    @Override
    public String execute(TaskList taskList, Ui ui, Storage storage) throws BibotException {
        taskList.add(this.task);
        storage.saveTasks(taskList);
        return String.format(REPLY_STRING_FORMAT, ui.getTaskString(this.task),
                ui.getTaskCountString(taskList));
    }
}
