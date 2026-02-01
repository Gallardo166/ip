package bibot.task;
import java.util.ArrayList;
import java.util.Iterator;

import bibot.BibotException;

public class TaskList implements Iterable<Task> {
    private ArrayList<Task> tasks = new ArrayList<>();

    public void add(Task task) {
        tasks.add(task);
    }

    public void markTask(int index) throws BibotException {
        if (index < 0 || index >= tasks.size()) {
            throw new BibotException("There's no such task!");
        } else {
            tasks.get(index).markAsDone();
        }
    }

    public void unmarkTask(int index) throws BibotException {
        if (index < 0 || index >= tasks.size()) {
            throw new BibotException("There's no such task!");
        } else {
            tasks.get(index).markAsUndone();
        }
    }

    public Task deleteTask(int index) throws BibotException {
        if (index < 0 || index >= tasks.size()) {
            throw new BibotException("There's no such task!");
        } else {
            return tasks.remove(index);
        }
    }

    public Task get(int index) throws BibotException {
        if (index < 0 || index >= tasks.size()) {
            throw new BibotException("There's no such task!");
        } else {
            return tasks.get(index);
        }
    }

    public int getLength() {
        return tasks.size(); 
    }

    public Iterator<Task> iterator() {
        return tasks.iterator();
    }
}
