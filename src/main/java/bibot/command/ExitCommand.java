package bibot.command;

import bibot.Storage;
import bibot.Ui;
import bibot.task.TaskList;

/**
 * Represents the executable command that ends the current application session.
 */
public class ExitCommand extends Command {
    @Override
    public void execute(TaskList taskList, Ui ui, Storage storage) {
        ui.end();
        ui.displayMessage("Bye. Hope to see you again soon!");
    }
}