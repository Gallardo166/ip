package bibot;

import bibot.command.AddCommand;
import bibot.command.Command;
import bibot.command.DeleteCommand;
import bibot.command.ExitCommand;
import bibot.command.ListCommand;
import bibot.command.MarkCommand;
import bibot.command.UnmarkCommand;
import bibot.task.Deadline;
import bibot.task.Event;
import bibot.task.ToDo;

public class Parser {
    private boolean isFinished = false;

    public boolean isFinished() {
        return this.isFinished;
    }

    public Command parse(String input) throws BibotException {
        String[] splitInput = input.split(" ");
        String command = splitInput[0];
        int numArgs = splitInput.length;

        switch (command) {
        case "bye":
            this.isFinished = true;
            return new ExitCommand();
        case "list":
            return new ListCommand();
        case "mark":
            if (!input.matches("^mark [0-9]+$")) {
                throw new BibotException("Please use this format:\n     mark [index]");
            } else {
                // Solution below adapted from
                // https://stackoverflow.com/questions/5585779/how-do-i-convert-a-string-to-an-int-in-java
                int index = Integer.parseInt(input.split(" ")[1]) - 1;
                return new MarkCommand(index);
            }
        case "unmark":
            if (!input.matches("^unmark [0-9]+$")) {
                throw new BibotException("Please use this format:\n     unmark [index]");
            } else {
                int index = Integer.parseInt(input.split(" ")[1]) - 1;
                return new UnmarkCommand(index);
            }
        case "todo":
            if (numArgs < 2) {
                throw new BibotException("Please write the task description!");
            } else { 
                String description = splitInput[1];
                ToDo todo = new ToDo(description);
                return new AddCommand(todo);
            }
        case "deadline":
            if (numArgs < 4 || !input.matches(".+ /by .+")) {
                throw new BibotException("Please use this format:\n     deadline [description] /by [datetime]");
            } else {
                String description = input.split(" /by ")[0].replaceFirst("deadline ", "");
                String date = input.split(" /by ")[1];
                Deadline deadline = new Deadline(description, date);
                return new AddCommand(deadline);
            }
        case "event":
            if (numArgs < 6 || !input.matches(".+ /from .+ /to .+")) {
                throw new BibotException(
                        "Please use this format:\n     event [description] /from [datetime] /to [datetime]");
            } else {
                String description = input.split(" /from ")[0].replaceFirst("event ", "");
                String startDate = input.split(" /from ")[1].split(" /to ")[0];
                String endDate = input.split(" /from ")[1].split(" /to ")[1];
                Event event = new Event(description, startDate, endDate);
                return new AddCommand(event);
            }
        case "delete":
            if (!input.matches("^delete [0-9]+$")) {
                throw new BibotException("Please use this format:\n     delete [index]"); 
            } else {
                int index = Integer.parseInt(input.split(" +")[1]) - 1;
                return new DeleteCommand(index);
            }
        default:
            throw new BibotException("I'm not familiar with that command...");
        }
    }
}
