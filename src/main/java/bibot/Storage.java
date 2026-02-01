package bibot;

import bibot.task.Deadline;
import bibot.task.Event;
import bibot.task.Task;
import bibot.task.TaskList;
import bibot.task.ToDo;

import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileWriter;
import java.io.IOException;

import java.util.Scanner;

public class Storage {
    private String filePath;
    
    public Storage(String filePath) {
        this.filePath = filePath;
    }

    public TaskList loadTasks() {
        TaskList taskList = new TaskList();
        try {
            File f = new File(filePath);
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

    public void saveTasks(TaskList taskList) {
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
}
