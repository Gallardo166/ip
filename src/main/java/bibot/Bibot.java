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
     * Contains the main application logic.
     * Reads and executes user commands.
     */
    public void run() {
        ui.displayGreeting();
        while (!parser.isFinished()) {
            try {
                String input = ui.readInput();
                ui.displayTopLine();
                Command command = parser.parse(input);
                command.execute(taskList, ui, storage);
            } catch (BibotException exception) {
                ui.displayMessage(exception.getMessage());
            } finally {
                ui.displayBottomLine();
            }
        }
    }

    /**
     * Represents the entry point of the application.
     * Uses <code>./data/tasks.txt</code> for task storage.
     */
    public static void main(String[] args) {
        new Bibot("./data/tasks.txt").run();
    }
}