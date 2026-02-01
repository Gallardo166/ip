package bibot;

import bibot.command.Command;
import bibot.task.TaskList;

public class Bibot {
    private Storage storage;
    private TaskList taskList;
    private Parser parser;
    private Ui ui;

    public Bibot(String filePath) {
        this.storage = new Storage(filePath);
        this.taskList = storage.loadTasks();
        this.parser = new Parser();
        this.ui = new Ui();
    }

    public void run() {
        ui.displayGreeting();
        while (!parser.isFinished()) {
            try {
                String input = ui.readInput();
                ui.displayTopLine();
                Command command = parser.parse(input);
                command.execute(taskList, ui, storage);
            } catch (BibotException e) {
                ui.displayMessage(e.getMessage());
            }
            ui.displayBottomLine();
        }
    }

    public static void main(String[] args) {
        new Bibot("./data/tasks.txt").run();
    }
}
