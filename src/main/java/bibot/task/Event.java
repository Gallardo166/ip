package bibot.task;

/**
 * Represents a task with a start time and end time.
 */
public class Event extends Task {
    private String startTime;
    private String endTime;

    /**
     * Constructs a new <code>Event</code> representing a task with a start time and end time.
     * The <code>Event</code> is initially not completed.
     */
    public Event(String description, String startTime, String endTime) {
        super(description);
        this.startTime = startTime;
        this.endTime = endTime;
    }

    /**
     * Constructs a new <code>Event</code> representing a task with a start time and end time.
     */
    public Event(String description, String startTime, String endTime, boolean isDone) {
        super(description, isDone);
        this.startTime = startTime;
        this.endTime = endTime;
    }

    @Override
    public String toString() {
        return String.format("[E]%s (from: %s to: %s)", super.toString(), startTime, endTime);
    }

    @Override
    public String getFileString() {
        return "E | " + super.getFileString() + " | " + this.startTime + " | " + this.endTime;
    }
}
