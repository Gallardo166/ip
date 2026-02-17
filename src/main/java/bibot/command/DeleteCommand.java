package bibot.command;

import bibot.BibotException;
import bibot.Storage;
import bibot.Ui;
import bibot.task.Task;
import bibot.task.TaskList;

/**
 * Represents the executable command that deletes a task from the task list.
 */
public class DeleteCommand extends Command {
    private int index;

    /**
     * Constructs a new <code>DeleteCommand</code> representing the
     * executable command that deletes a task from the task list.
     * 
     * @param index Position of task to be deleted.
     */
    public DeleteCommand(int index) {
        this.index = index;
    }

    @Override
    public String execute(TaskList taskList, Ui ui, Storage storage) throws BibotException {
        Task deletedTask = taskList.deleteTask(index);
        storage.saveTasks(taskList);
        return "Noted. I've removed this task: \n" + ui.getTaskString(deletedTask) + "\n"
                + ui.getTaskCountString(taskList);
    }
}
