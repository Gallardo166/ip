package bibot.task;

/**
 * Represents a task managed by the chatbot application.
 */
public abstract class Task {
    private String description;
    private boolean isDone;

    /**
     * Constructs a new <code>Task</code> with a description and a completion status.
     * A <code>Task</code> is initially not completed.
     */
    public Task(String description) {
        this.description = description;
        this.isDone = false;
    }

    /**
     * Constructs a new <code>Task</code> with a description and a completion status.
     */
    public Task(String description, boolean isDone) {
        this.description = description;
        this.isDone = isDone;
    }

    /**
     * Marks the <code>Task</code> as completed.
     */
    public void markAsDone() {
        this.isDone = true;
    }

    /**
     * Marks the <code>Task</code> as not completed.
     */
    public void markAsUndone() {
        this.isDone = false;
    }

    public boolean hasSubstring(String substring) {
        return this.description.contains(substring);
    }

    public abstract boolean isUpcoming();

    @Override
    public String toString() {
        if (this.isDone) {
            return String.format("[X] %s", this.description);
        } else {
            return String.format("[ ] %s", this.description);
        }
    }

    /**
     * Returns the string representation to be written into storage file.
     */
    public String getFileString() {
        String completionString = (this.isDone) ? "completed" : "not completed";
        return String.format("%s | %s", completionString, this.description);
    }
}
