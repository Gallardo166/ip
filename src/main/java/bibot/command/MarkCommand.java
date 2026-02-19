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

    private static final String REPLY_STRING_FORMAT = "Nice! I've marked this task as done: \n%s";

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
    public String execute(TaskList taskList, Ui ui, Storage storage) throws BibotException {
        taskList.markTask(this.index);
        storage.saveTasks(taskList);
        return String.format(REPLY_STRING_FORMAT, ui.getTaskString(taskList.get(this.index)));
    }
}
