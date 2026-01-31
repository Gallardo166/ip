public class ExitCommand extends Command {
    @Override
    public void execute(TaskList taskList, Ui ui, Storage storage) {
        ui.end();
        ui.displayMessage("Bye. Hope to see you again soon!");
    }
}
