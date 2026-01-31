import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileWriter;
import java.io.IOException;

import java.util.Scanner;

public class Bibot {
    public static TaskList loadTasks() {
        TaskList taskList = new TaskList();
        try {
            File f = new File("./data/bibot.txt");
            Scanner s = new Scanner(f);
            while (s.hasNext()) {
                String[] splitLine = s.nextLine().split(" \\| ");
                String taskType = splitLine[0];
                boolean isDone = splitLine[1].equals("completed");
                if (taskType.equals("T")) {
                    taskList.add(new ToDo(splitLine[2], isDone));
                } else if (taskType.equals("D")) {
                    taskList.add(new Deadline(splitLine[2], splitLine[3], isDone));
                } else {
                    taskList.add(new Event(splitLine[2], splitLine[3], splitLine[4], isDone));
                }
            }
            s.close();
            return taskList;
        } catch (FileNotFoundException | BibotException e) {
            return taskList;
        }
    }

    public static void saveTasks(TaskList taskList) {
        try {
            //@@author Gallardo166-reused
            //Reused from https://stackoverflow.com/questions/28947250/create-a-directory-if-it-does-not-exist-and-then-create-the-files-in-that-direct
            // with minor modifications
            File directory = new File("./data");
            if (!directory.exists()) {
                directory.mkdir();
            }
            //@@author
            FileWriter fw = new FileWriter("./data/bibot.txt");
            for (Task task : taskList) {
               fw.write(task.fileString() + "\n");
            }
            fw.close();
        } catch (IOException e) {  
            System.out.println("Something went wrong: " + e.getMessage());
        }
    }

    public static void main(String[] args) {
        TaskList taskList = loadTasks();
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
                    saveTasks(taskList);
                    ui.displayMessage("Nice! I've marked this task as done:");
                    ui.displayTask(taskList.get(index));

                } else if (command.startsWith("unmark ")) {
                    int index = Integer.parseInt(command.split(" ")[1]) - 1;
                    taskList.unmarkTask(index);
                    saveTasks(taskList);
                    ui.displayMessage("OK, I've marked this task as not done yet:");
                    ui.displayTask(taskList.get(index));

                } else if (command.startsWith("todo ")) {
                    if (command.split(" +").length < 2) {
                        throw new BibotException("Please write the task description!");
                    } else {
                        String description = command.replaceFirst("todo +", "");
                        ToDo todo = new ToDo(description);
                        taskList.add(todo);
                        saveTasks(taskList);
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
                        saveTasks(taskList);
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
                        saveTasks(taskList);
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
                        saveTasks(taskList);
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
