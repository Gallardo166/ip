package bibot.command;
import bibot.Ui;
import bibot.task.TaskList;
import bibot.Storage;

import bibot.BibotException;

/**
 * Represents an executable command corresponding to specific user inputs.
 */
public abstract class Command {
    /**
     * Modifies the task list and displays output to user
     * based on the type of command.
     * @param taskList Task list to be modified.
     * @param ui Ui used for output display.
     * @param storage Storage used for saving tasks into storage file.
     * @throws BibotException If command is invalid.
     */
    public abstract void execute(TaskList taskList, Ui ui, Storage storage) throws BibotException;
}
