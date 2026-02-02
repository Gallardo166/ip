package bibot.task;
import java.util.ArrayList;
import java.util.Iterator;

import bibot.BibotException;

/**
 * Represents a list of <code>Task</code>s.
 * <code>TaskList</code> can be iterated through using a for loop.
 */
public class TaskList implements Iterable<Task> {
    private ArrayList<Task> tasks = new ArrayList<>();

    /**
     * Inserts given <code>Task</code> to the end of the list.
     */
    public void add(Task task) {
        tasks.add(task);
    }

    /**
     * Marks the <code>Task</code> in the specified index in the
     * list as completed.
     * The index starts at 0 for the first task. 
     * 
     * @param index Position of task in the list.
     * @throws BibotException If there is no task in given index.
     */
    public void markTask(int index) throws BibotException {
        if (index < 0 || index >= tasks.size()) {
            throw new BibotException("There's no such task!");
        } else {
            tasks.get(index).markAsDone();
        }
    }

    /**
     * Marks the <code>Task</code> in the specified index in the
     * list as not completed.
     * The index starts at 0 for the first task.
     * 
     * @param index Position of task in the list.
     * @throws BibotException If there is no task in given index.
     */
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

    /**
     * Delete the <code>Task</code> in the specified index in the
     * list.
     * Indices of subsequent tasks (those with indices greater than
     * the specified index) are decremented by 1.
     * 
     * @param index Position of task in the list.
     * @return Deleted task.
     * @throws BibotException If there is no task in given index.
     */
    public Task get(int index) throws BibotException {
        if (index < 0 || index >= tasks.size()) {
            throw new BibotException("There's no such task!");
        } else {
            return tasks.get(index);
        }
    }

    /**
     * Returns length of the list.
     */
    public int getLength() {
        return tasks.size(); 
    }

    @Override
    public Iterator<Task> iterator() {
        return tasks.iterator();
    }
}
