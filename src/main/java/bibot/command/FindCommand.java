package bibot.command;

import bibot.Storage;
import bibot.Ui;
import bibot.task.Task;
import bibot.task.TaskList;

/**
 * Represents the executable command that displays tasks containing some keyword.
 */
public class FindCommand extends Command {
    private String keyword;

    private static final String REPLY_STRING_FORMAT = "Here are the matching tasks in your list: \n%s";

    /**
     * Constructs a new <code>FindCommand</code> representing the
     * executable command that displays tasks containing the given keyword.
     */
    public FindCommand(String keyword) {
        this.keyword = keyword;
    }

    @Override
    public String execute(TaskList taskList, Ui ui, Storage storage) {
        TaskList matchingTasks = taskList.filter((Task task) -> task.hasSubstring(this.keyword));
        return String.format(REPLY_STRING_FORMAT, ui.getTaskListString(matchingTasks));
    }
}
