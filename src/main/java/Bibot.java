public class Bibot {
    public static void main(String[] args) {
        Storage storage = new Storage("./data/bibot.txt");
        TaskList taskList = storage.loadTasks();
        Ui ui = new Ui();

        ui.displayTopLine();
        ui.displayMessage("Hello! I'm Bibot!");
        ui.displayMessage("What can I do for you?");
        ui.displayBottomLine();

        while (true) {
            try {
                String command = ui.readCommand();

                ui.displayTopLine();

                if (command.equals("bye")) {
                    ui.end();
                    ui.displayMessage("Bye. Hope to see you again soon!");
                    ui.displayBottomLine();
                    break;

                } else if (command.equals("list")) {
                    ui.displayMessage("Here are the tasks in your list:");
                    taskList.display();

                } else if (command.startsWith("mark ")) {
                    // Solution below adapted from
                    // https://stackoverflow.com/questions/5585779/how-do-i-convert-a-string-to-an-int-in-java
                    int index = Integer.parseInt(command.split(" ")[1]) - 1;
                    taskList.markTask(index);
                    storage.saveTasks(taskList);
                    ui.displayMessage("Nice! I've marked this task as done:");
                    ui.displayTask(taskList.get(index));

                } else if (command.startsWith("unmark ")) {
                    int index = Integer.parseInt(command.split(" ")[1]) - 1;
                    taskList.unmarkTask(index);
                    storage.saveTasks(taskList);
                    ui.displayMessage("OK, I've marked this task as not done yet:");
                    ui.displayTask(taskList.get(index));

                } else if (command.startsWith("todo ")) {
                    if (command.split(" +").length < 2) {
                        throw new BibotException("Please write the task description!");
                    } else {
                        String description = command.replaceFirst("todo +", "");
                        ToDo todo = new ToDo(description);
                        taskList.add(todo);
                        storage.saveTasks(taskList);
                        ui.displayMessage("Got it. I've added this task:");
                        ui.displayTask(todo);
                        taskList.printLength();
                    }

                } else if (command.startsWith("deadline ")) {
                    String[] splitCommand = command.split(" /by ");
                    if (splitCommand.length != 2) {
                        throw new BibotException("Please use this format:\n     deadline [description] /by [datetime]");
                    } else {
                        String description = splitCommand[0].replaceFirst("deadline ", "");
                        String date = splitCommand[1];
                        Deadline deadline = new Deadline(description, date);
                        taskList.add(deadline);
                        storage.saveTasks(taskList);
                        ui.displayMessage("Got it. I've added this task:");
                        ui.displayTask(deadline);
                        taskList.printLength();
                    }

                } else if (command.startsWith("event ")) {
                    String[] splitCommand = command.split(" /from ");
                    if (splitCommand.length != 2 || splitCommand[1].split(" /to ").length != 2) {
                        throw new BibotException(
                                "Please use this format:\n     event [description] /from [datetime] /to [datetime]");
                    } else {
                        String description = splitCommand[0].replaceFirst("event ", "");
                        String startDate = splitCommand[1].split(" /to ")[0];
                        String endDate = splitCommand[1].split(" /to ")[1];
                        Event event = new Event(description, startDate, endDate);
                        taskList.add(event);
                        storage.saveTasks(taskList);
                        ui.displayMessage("Got it. I've added this task:");
                        ui.displayTask(event);
                        taskList.printLength();
                    }

                } else if (command.startsWith("delete ")) {
                    String[] splitCommand = command.split(" +");
                    if (splitCommand.length != 2) {
                        throw new BibotException("Please specify the index to delete!");
                    } else {
                        int index = Integer.parseInt(command.split(" +")[1]) - 1;
                        Task deletedTask = taskList.deleteTask(index);
                        storage.saveTasks(taskList);
                        ui.displayMessage("Noted. I've removed this task:");
                        ui.displayTask(deletedTask);
                        taskList.printLength();
                    }

                } else {
                    throw new BibotException("I'm not familiar with that command..");

                }
            } catch (BibotException e) {
                ui.displayMessage(e.getMessage());

            }

            ui.displayBottomLine();
        }

    }
}
