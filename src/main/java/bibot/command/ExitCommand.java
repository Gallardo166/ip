package bibot.command;

import bibot.Storage;
import bibot.Ui;
import bibot.task.TaskList;

/**
 * Represents the executable command that ends the current application session.
 */
public class ExitCommand extends Command {
    private static final String REPLY_STRING = "Bye. Hope to see you again soon!";

    @Override
    public String execute(TaskList taskList, Ui ui, Storage storage) {
        return REPLY_STRING;
    }
}
