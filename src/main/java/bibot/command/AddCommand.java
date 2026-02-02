package bibot.command;
import bibot.task.Task;
import bibot.task.TaskList;
import bibot.Ui;
import bibot.Storage;

public class AddCommand extends Command {
    private Task task;

    public AddCommand(Task task) {
        this.task = task;
    }

    @Override
    public void execute(TaskList taskList, Ui ui, Storage storage) {
        taskList.add(task);
        storage.saveTasks(taskList);
        ui.displayMessage("Got it. I've added this task:");
        ui.displayTask(task);
        ui.displayTaskCount(taskList);
    }
}