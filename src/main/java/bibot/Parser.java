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

    private static final String DEADLINE_SPLIT_KEY = " /by ";
    private static final String EVENT_FIRST_SPLIT_KEY = " /from ";
    private static final String EVENT_SECOND_SPLIT_KEY = " /to ";

    private static final String UNKNOWN_COMMAND_MESSAGE = "I'm not familiar with that command...";

    private enum InputCommand {
        BYE      ("^$",                   "Please use this format:\n     bye"),
        LIST     ("^$",                   "Please use this format:\n     list"),
        MARK     ("^[0-9]+$",             "Please use this format:\n     mark [index]"),
        UNMARK   ("^[0-9]+",              "Please use this format:\n     unmark [index]"),
        TODO     ("^.+$",                 "Please write the task description!"),
        DEADLINE ("^.+ /by .+$",          "Please use this format:\n     deadline [description] /by [datetime]"),
        EVENT    ("^.+ /from .+ /to .+$", "Please use this format:\n     event [description] /from [datetime] /to [datetime]"),
        DELETE   ("^[0-9]+$",             "Please use this format:\n     delete [index]"),
        FIND     ("^.+$",                 "Please use this format:\n     find [keyword]");

        private String requiredBodyRegex;
        private String errorMessage;

        private InputCommand(String requiredBodyRegex, String errorMessage) {
            this.requiredBodyRegex = requiredBodyRegex;
            this.errorMessage = errorMessage;
        }

        public void checkValidBody(String inputBody) throws BibotException {
            if (!inputBody.matches(this.requiredBodyRegex)) {
                throw new BibotException(this.errorMessage);
            }
        }
    }

    private class InputData {
        private InputCommand inputCommand;
        private String inputBody;

        public InputData(InputCommand inputCommand, String inputBody) {
            this.inputCommand = inputCommand;
            this.inputBody = inputBody;
        }

        public void checkValidBody() throws BibotException {
            this.inputCommand.checkValidBody(this.inputBody);
        }
    }

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
        InputData inputData = getInputData(input);
        inputData.checkValidBody();

        Command command = null;

        switch (inputData.inputCommand) {
        case BYE:
            this.isFinished = true;
            command = createExitCommand();
            break;
        case LIST:
            command = createListCommand();
            break;
        case MARK:
            command = createMarkCommand(inputData.inputBody);
            break;
        case UNMARK:
            command = createUnmarkCommand(inputData.inputBody);
            break;
        case TODO:
            command = createAddTodoCommand(inputData.inputBody);
            break;
        case DEADLINE:
            command = createAddDeadlineCommand(inputData.inputBody);
            break;
        case EVENT:
            command = createAddEventCommand(inputData.inputBody);
            break;
        case DELETE:
            command = createDeleteCommand(inputData.inputBody);
            break;
        case FIND:
            command = createFindCommand(inputData.inputBody);
            break;
        default:
            assert false : "This point should be unreachable";
        }

        assert command != null : "command should not be null";
        
        return command;
    }

    private InputData getInputData(String input) throws BibotException {
        String[] splitInput = input.split(" ");
        String inputCommandString = splitInput[0];
        InputCommand inputCommand = convertToEnum(inputCommandString);

        String inputBody;
        if (splitInput.length == 1) {
            inputBody = "";
        } else {
            String untilFirstSpaceRegex = "[^ ]+ ";
            inputBody = input.replaceFirst(untilFirstSpaceRegex, "");
        }

        return new InputData(inputCommand, inputBody);
    }

    private InputCommand convertToEnum(String inputCommandString) throws BibotException {
        try {
             return Enum.valueOf(InputCommand.class, inputCommandString.toUpperCase());
        } catch (IllegalArgumentException exception) {
            throw new BibotException(UNKNOWN_COMMAND_MESSAGE);
        }
    }

    private ExitCommand createExitCommand() {
        return new ExitCommand();
    }

    private ListCommand createListCommand() {
        return new ListCommand();
    }

    private MarkCommand createMarkCommand(String inputBody) {
        // Solution below adapted from https://stackoverflow.com/a/5585800
        int index = Integer.parseInt(inputBody) - 1;
        return new MarkCommand(index);
    } 

    private UnmarkCommand createUnmarkCommand(String inputBody) {
        int index = Integer.parseInt(inputBody) - 1;
        return new UnmarkCommand(index);
    }

    private AddCommand createAddTodoCommand(String inputBody) {
        String description = inputBody;
        ToDo todo = new ToDo(description);
        return new AddCommand(todo);
    }

    private AddCommand createAddDeadlineCommand(String inputBody) throws BibotException {
        String[] splitInputBody = inputBody.split(DEADLINE_SPLIT_KEY);
        String description = splitInputBody[0];
        String date = splitInputBody[1];
        Deadline deadline = new Deadline(description, date);
        return new AddCommand(deadline);
    }

    private AddCommand createAddEventCommand(String inputBody) {
        String[] firstSplitInputBody = inputBody.split(EVENT_FIRST_SPLIT_KEY);
        String description = firstSplitInputBody[0];

        String[] secondSplitInputBody = firstSplitInputBody[1].split(EVENT_SECOND_SPLIT_KEY);
        String startDate = secondSplitInputBody[0];
        String endDate = secondSplitInputBody[1];
        Event event = new Event(description, startDate, endDate);
        return new AddCommand(event);
    }


    private DeleteCommand createDeleteCommand(String inputBody) {
        int index = Integer.parseInt(inputBody) - 1;
        return new DeleteCommand(index);
    }

    private FindCommand createFindCommand(String inputBody) {
        return new FindCommand(inputBody);
    }
}
