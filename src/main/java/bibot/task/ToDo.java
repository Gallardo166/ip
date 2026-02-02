package bibot.task;

/**
 * Represents a task with a description.
 */
public class ToDo extends Task {

    /**
     * Constructs a new <code>ToDo</code> representing a task
     * with a description.
     * The <code>ToDo</code> is initially not completed.
     */
    public ToDo(String description) {
        super(description);
    }

    /**
     * Constructs a new <code>ToDo</code> representing a task
     * with a description.
     */
    public ToDo(String description, boolean isDone) {
        super(description, isDone);
    }

    @Override
    public String toString() {
        return "[T]" + super.toString();
    }

    @Override
    public String fileString() {
        return "T | " + super.fileString();
    }
}
