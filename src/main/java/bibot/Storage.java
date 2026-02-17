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

/**
 * Represents component that handles loading and saving tasks in a file.
 */
public class Storage {
    private String root;
    private String filePath; 
    
    /**
     * Constructs a new <code>Storage</code> representing the component
     * that handles loading and saving tasks in a file.
     * 
     * @param filePath Path to storage file.
     */
    public Storage(String filePath) {
        String[] directories = filePath.split("/");

        assert directories.length > 1 : "incorrect file path";

        this.root =  directories[1];
        this.filePath = filePath;
    }

    /**
     * Reads tasks from the storage file and returns a <code>TaskList</code>.
     * If storage file is not found, returns an empty <code>TaskList</code>.
     */
    public TaskList loadTasks() {
        TaskList taskList = new TaskList();
        try {
            File f = new File(filePath);
            Scanner s = new Scanner(f);
            while (s.hasNext()) {
                taskList.add(createTask(s.nextLine()));
            }
            s.close();
            return taskList;
        } catch (FileNotFoundException | BibotException exception) {
            return taskList;
        }
    }

    /**
     * Writes tasks in the specified <code>TaskList</code>
     * to the storage file.
     */
    public void saveTasks(TaskList taskList) throws BibotException {
        try {
            //@@author Gallardo166-reused
            //Reused from https://stackoverflow.com/questions/28947250/create-a-directory-if-it-does-not-exist-and-then-create-the-files-in-that-direct
            // with minor modifications
            File directory = new File("./" + root);
            if (!directory.exists()) {
                directory.mkdir();
            }
            //@@author
            FileWriter fw = new FileWriter(filePath);
            for (Task task : taskList) {
                fw.write(task.getFileString() + "\n");
            }
            fw.close();
        } catch (IOException exception) {  
            throw new BibotException("Something went wrong: " + exception.getMessage());
        }
    }

    private Task createTask(String line) throws BibotException {
        String[] splitLine = line.split(" \\| ");
        String taskType = splitLine[0];
        boolean isDone = splitLine[1].equals("completed");

        if (taskType.equals("T")) {
            return new ToDo(splitLine[2], isDone);
        } else if (taskType.equals("D")) {
            return new Deadline(splitLine[2], splitLine[3], isDone);
        } else if (taskType.equals("E")) {
            return new Event(splitLine[2], splitLine[3], splitLine[4], isDone);
        } else {
            throw new BibotException("Incorrect format in file encountered.");
        }
    } 
}
