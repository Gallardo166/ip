public class MarkCommand extends Command {
    private int index;

    public MarkCommand(int index) {
        this.index = index;
    }

    @Override
    public void execute(TaskList taskList, Ui ui, Storage storage) throws BibotException {
        taskList.markTask(index);
        storage.saveTasks(taskList);
        ui.displayMessage("Nice! I've marked this task as done:");
        ui.displayTask(taskList.get(index));
    }
}
