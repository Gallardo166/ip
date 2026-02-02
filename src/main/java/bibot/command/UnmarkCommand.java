package bibot.command;
import bibot.Ui;
import bibot.task.TaskList;
import bibot.Storage;

import bibot.BibotException;

public class UnmarkCommand extends Command {
    private int index;

    public UnmarkCommand(int index) {
        this.index = index;
    }
    
    @Override
    public void execute(TaskList taskList, Ui ui, Storage storage) throws BibotException {
        taskList.unmarkTask(index);
        storage.saveTasks(taskList);
        ui.displayMessage("OK, I've marked this task as not done yet:");
        ui.displayTask(taskList.get(index));
    }
}