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

    /**
     * Constructs a new <code>Bibot</code> representing the chatbot application.
     * 
     * @param filePath Path to storage file.
     */
    public Bibot(String filePath) {
        this.storage = new Storage(filePath);
        this.taskList = storage.loadTasks();
        this.parser = new Parser();
        this.ui = new Ui();
    }

    /**
     * Returns a string reply to the user input.
     */
    public String getResponse(String input) {
        try {
            Command command = parser.parse(input);
            return command.execute(taskList, ui, storage);
        } catch (BibotException exception) {
            return exception.getMessage();
        }
    }

}