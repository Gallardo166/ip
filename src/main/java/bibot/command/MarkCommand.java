package bibot.command;

import bibot.BibotException;
import bibot.Storage;
import bibot.Ui;
import bibot.task.TaskList;

/**
 * Represents the executable command that marks a task as completed.
 */
public class MarkCommand extends Command {
    private int index;

    /**
     * Constructs a new <code>MarkCommand</code> representing the
     * executable command that marks a task as completed.
     * 
     * @param index Position of task to mark as completed.
     */
    public MarkCommand(int index) {
        this.index = index;
    }

    @Override
    public void execute(TaskList taskList, Ui ui, Storage storage) throws BibotException {
        taskList.markTask(index);
        storage.saveTasks(taskList);
        ui.displayMessage("Nice! I've marked this task as done:");
        ui.displayTask(taskList.get(index));
    }
}