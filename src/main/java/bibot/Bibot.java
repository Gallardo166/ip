package bibot;

import bibot.command.Command;
import bibot.task.TaskList;

/**
 * Represents the chatbot application. 
 */
public class Bibot {
    private Storage storage;
    private TaskList taskList;
    private Parser parser;
    private Ui ui;
    private String loadErrorMessage = null;

    /**
     * Constructs a new <code>Bibot</code> representing the chatbot application.
     * 
     * @param filePath Path to storage file.
     */
    public Bibot(String filePath) {
        assert !filePath.equals("") : "filePath should not be empty";

        this.storage = new Storage(filePath);

        try {
            this.taskList = this.storage.loadTasks();
        } catch (BibotException exception) {
            this.loadErrorMessage = exception.getMessage();
            this.taskList = new TaskList();
        }

        this.parser = new Parser();
        this.ui = new Ui();
    }

    /**
     * Returns a string reply to the user input.
     */
    public String getResponse(String input) {
        try {
            Command command = this.parser.parse(input);
            return command.execute(this.taskList, this.ui, this.storage);
        } catch (BibotException exception) {
            return exception.getMessage();
        }
    }

    public boolean isFinished() {
        return this.parser.isFinished();
    }

    public String getLoadErrorMessage() {
        return this.loadErrorMessage;
    }
}
