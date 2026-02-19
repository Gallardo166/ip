package bibot.command;

import bibot.BibotException;
import bibot.Storage;
import bibot.Ui;
import bibot.task.TaskList;

/**
 * Represents the executable command that marks a task as not completed.
 */
public class UnmarkCommand extends Command {
    private int index;

    private static final String REPLY_STRING_FORMAT = "OK, I've marked this task as not done yet: \n%s";

    /**
     * Constructs a new <code>UnmarkCommand</code> representing the
     * executable command that marks a task as not completed.
     * 
     * @param index Position of task to mark as not completed.
     */
    public UnmarkCommand(int index) {
        this.index = index;
    }
    
    @Override
    public String execute(TaskList taskList, Ui ui, Storage storage) throws BibotException {
        taskList.unmarkTask(this.index);
        storage.saveTasks(taskList);
        return String.format(REPLY_STRING_FORMAT, ui.getTaskString(taskList.get(this.index)));
    }
}
