package bibot.command;

import bibot.Ui;
import bibot.task.TaskList;
import bibot.Storage;

public class ListCommand extends Command {
    @Override
    public void execute(TaskList taskList, Ui ui, Storage storage) {
        ui.displayMessage("Here are the tasks in your list:");
        ui.displayTaskList(taskList);
    }    
}
