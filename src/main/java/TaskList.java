import java.util.ArrayList;
import java.util.Iterator;

public class TaskList implements Iterable<Task> {
    private ArrayList<Task> tasks = new ArrayList<>();
    private int latestId = -1;

    public void add(Task task) {
        latestId += 1;
        tasks.add(task);
    }

    public void markTask(int index) throws BibotException {
        if (index < 0 || index > latestId) {
            throw new BibotException("There's no such task!");
        } else {
            tasks.get(index).markAsDone();
        }
    }

    public void unmarkTask(int index) throws BibotException {
        if (index < 0 || index > latestId) {
            throw new BibotException("There's no such task!");
        } else {
            tasks.get(index).markAsUndone();
        }
    }

    public Task deleteTask(int index) throws BibotException {
        if (index < 0 || index > latestId) {
            throw new BibotException("There's no such task!");
        } else {
            latestId -= 1;
            return tasks.remove(index);
        }
    }

    public Task get(int index) throws BibotException {
        if (index < 0 || index > latestId) {
            throw new BibotException("There's no such task!");
        } else {
            return tasks.get(index);
        }
    }

    public int getLength() {
        return latestId + 1;
    }

    public Iterator<Task> iterator() {
        return tasks.iterator();
    }
}
