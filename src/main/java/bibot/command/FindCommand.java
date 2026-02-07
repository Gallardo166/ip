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

    /**
     * Constructs a new <code>FindCommand</code> representing the
     * executable command that displays tasks containing the given keyword.
     */
    public FindCommand(String keyword) {
        this.keyword = keyword;
    }

    @Override
    public String execute(TaskList taskList, Ui ui, Storage storage) {
        TaskList matchingTasks = taskList.filter((Task task) -> task.hasSubstring(keyword));
        return "Here are the matching tasks in your list: \n" + ui.getTaskListString(matchingTasks);
    }
}
