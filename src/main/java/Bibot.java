public class Bibot {
    public static void main(String[] args) {
        Storage storage = new Storage("./data/bibot.txt");
        TaskList taskList = storage.loadTasks();
        Ui ui = new Ui();
        Parser parser = new Parser();

        ui.displayTopLine();
        ui.displayMessage("Hello! I'm Bibot!");
        ui.displayMessage("What can I do for you?");
        ui.displayBottomLine();

        while (!parser.isFinished()) {
            try {
                String input = ui.readCommand();
                ui.displayTopLine();
                parser.parse(input, ui, storage, taskList);
            } catch (BibotException e) {
                ui.displayMessage(e.getMessage());
            }

            ui.displayBottomLine();
        }

    }
}
