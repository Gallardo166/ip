package bibot.command;

import bibot.BibotException;
import bibot.Storage;
import bibot.Ui;
import bibot.task.TaskList;

/**
 * Represents an executable command corresponding to specific user inputs.
 */
public abstract class Command {
    /**
     * Modifies the task list and returns a reply string based on the type of command.
     * 
     * @param taskList Task list to be modified.
     * @param ui Ui used for forming replies.
     * @param storage Storage used for saving tasks into storage file.
     * @throws BibotException If command is invalid.
     */
    public abstract String execute(TaskList taskList, Ui ui, Storage storage) throws BibotException;
}