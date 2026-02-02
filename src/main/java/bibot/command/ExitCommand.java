package bibot.command;

import bibot.Ui;
import bibot.task.TaskList;
import bibot.Storage;

public class ExitCommand extends Command {
    @Override
    public void execute(TaskList taskList, Ui ui, Storage storage) {
        ui.end();
        ui.displayMessage("Bye. Hope to see you again soon!");
    }
}