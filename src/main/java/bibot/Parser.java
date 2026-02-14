package bibot;

import bibot.command.AddCommand;
import bibot.command.Command;
import bibot.command.DeleteCommand;
import bibot.command.ExitCommand;
import bibot.command.FindCommand;
import bibot.command.ListCommand;
import bibot.command.MarkCommand;
import bibot.command.UnmarkCommand;
import bibot.task.Deadline;
import bibot.task.Event;
import bibot.task.ToDo;

/**
 * Represents component that handles different user inputs.
 */
public class Parser {
    private boolean isFinished = false;

    /**
     * Returns true if <code>Parser</code> has encountered the exit command.
     */
    public boolean isFinished() {
        return this.isFinished;
    }

    /**
     * Returns an executable <code>Command</code> based on the user input.
     * 
     * @throws BibotException If input format is invalid.
     */
    public Command parse(String input) throws BibotException {
        String[] splitInput = input.split(" ");
        String keyword = splitInput[0];
        int numArgs = splitInput.length;
        Command command;

        switch (keyword) {
        case "bye":
            this.isFinished = true;
            command = new ExitCommand();
            break;
        case "list":
            command = new ListCommand();
            break;
        case "mark":
            if (!input.matches("^mark [0-9]+$")) {
                throw new BibotException("Please use this format:\n     mark [index]");
            } else {
                // Solution below adapted from
                // https://stackoverflow.com/questions/5585779/how-do-i-convert-a-string-to-an-int-in-java
                int index = Integer.parseInt(input.split(" ")[1]) - 1;
                command = new MarkCommand(index);
            }
            break;
        case "unmark":
            if (!input.matches("^unmark [0-9]+$")) {
                throw new BibotException("Please use this format:\n     unmark [index]");
            } else {
                int index = Integer.parseInt(input.split(" ")[1]) - 1;
                command = new UnmarkCommand(index);
            }
            break;
        case "todo":
            if (numArgs < 2) {
                throw new BibotException("Please write the task description!");
            } else { 
                String description = input.replaceFirst("todo ", "");
                ToDo todo = new ToDo(description);
                command = new AddCommand(todo);
            }
            break;
        case "deadline":
            if (numArgs < 4 || !input.matches(".+ /by .+")) {
                throw new BibotException(
                        "Please use this format:\n     deadline [description] /by [datetime]");
            } else {
                String description = input.split(" /by ")[0]
                        .replaceFirst("deadline ", "");
                String date = input.split(" /by ")[1];
                Deadline deadline = new Deadline(description, date);
                command = new AddCommand(deadline);
            }
            break;
        case "event":
            if (numArgs < 6 || !input.matches(".+ /from .+ /to .+")) {
                throw new BibotException(
                        "Please use this format:\n     event [description] /from [datetime] /to [datetime]");
            } else {
                String description = input.split(" /from ")[0].replaceFirst("event ", "");
                String startDate = input.split(" /from ")[1].split(" /to ")[0];
                String endDate = input.split(" /from ")[1].split(" /to ")[1];
                Event event = new Event(description, startDate, endDate);
                command = new AddCommand(event);
            }
            break;
        case "delete":
            if (!input.matches("^delete [0-9]+$")) {
                throw new BibotException("Please use this format:\n     delete [index]"); 
            } else {
                int index = Integer.parseInt(input.split(" +")[1]) - 1;
                command = new DeleteCommand(index);
            }
            break;
        case "find":
            if (numArgs < 2) {
                throw new BibotException("Please use this format:\n     find [keyword]");
            } else {
                String searchKeyword = input.replaceFirst("find ", "");
                command = new FindCommand(searchKeyword);
            }
            break;
        default:
            throw new BibotException("I'm not familiar with that command...");
        }

        assert command != null : "null command";

        return command;
    }
}
