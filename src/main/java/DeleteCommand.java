public class DeleteCommand extends Command {
    private int index;

    public DeleteCommand(int index) {
        this.index = index;
    }

    @Override
    public void execute(TaskList taskList, Ui ui, Storage storage) throws BibotException {
        Task deletedTask = taskList.deleteTask(index);
        storage.saveTasks(taskList);
        ui.displayMessage("Noted. I've removed this task:");
        ui.displayTask(deletedTask);
        ui.displayTaskCount(taskList);
    }
}
