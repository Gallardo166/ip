package bibot.command;
import bibot.Ui;
import bibot.task.TaskList;
import bibot.Storage;

import bibot.BibotException;

public abstract class Command {
    public abstract void execute(TaskList taskList, Ui ui, Storage storage) throws BibotException;
}