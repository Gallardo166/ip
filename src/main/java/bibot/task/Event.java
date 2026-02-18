package bibot.task;

import bibot.BibotException;

import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.time.format.DateTimeParseException;

/**
 * Represents a task with a start time and end time.
 */
public class Event extends Task {
    private LocalDateTime startTime;
    private LocalDateTime endTime;
    
    private static final DateTimeFormatter INPUT_DATE_FORMAT
            = DateTimeFormatter.ofPattern("dd/MM/yyyy HHmm");
    private static final DateTimeFormatter DISPLAY_DATE_FORMAT
            = DateTimeFormatter.ofPattern("MMM dd yyyy HH:mm");

    private static final String INVALID_FORMAT_ERROR_MESSAGE
            = "Invalid datetime format: use dd/mm/yyyy hhmm (e.g. 12/02/2025 1400)";

    private static final long UPCOMING_INTERVAL_IN_DAYS = 1;

    /**
     * Constructs a new <code>Event</code> representing a task with a start time and end time.
     * The <code>Event</code> is initially not completed.
     */
    public Event(String description, String startTime, String endTime) throws BibotException {
        super(description);
        try {
            this.startTime = LocalDateTime.parse(startTime, INPUT_DATE_FORMAT);
            this.endTime = LocalDateTime.parse(endTime, INPUT_DATE_FORMAT);
        } catch (DateTimeParseException exception) {
            throw new BibotException(INVALID_FORMAT_ERROR_MESSAGE);
        }
    }

    /**
     * Constructs a new <code>Event</code> representing a task with a start time and end time.
     */
    public Event(String description, String startTime, String endTime, boolean isDone) throws BibotException {
        super(description, isDone);
        try {
            this.startTime = LocalDateTime.parse(startTime, INPUT_DATE_FORMAT);
            this.endTime = LocalDateTime.parse(endTime, INPUT_DATE_FORMAT);
        } catch (DateTimeParseException exception) {
            throw new BibotException(INVALID_FORMAT_ERROR_MESSAGE);
        }
    }

    @Override
    public boolean isUpcoming() {
        LocalDateTime currentTime = LocalDateTime.now();
        LocalDateTime upcomingMaximum = currentTime.plusDays(UPCOMING_INTERVAL_IN_DAYS);
        return this.startTime.isAfter(currentTime) && this.startTime.isBefore(upcomingMaximum);
    }
    @Override
    public String toString() {
        String formattedStartTime = this.startTime.format(DISPLAY_DATE_FORMAT);
        String formattedEndTime = this.endTime.format(DISPLAY_DATE_FORMAT);
        return String.format("[E]%s (from: %s to: %s)", super.toString(), formattedStartTime, formattedEndTime);
    }

    @Override
    public String getFileString() {
        String formattedStartTime = this.startTime.format(INPUT_DATE_FORMAT);
        String formattedEndTime = this.endTime.format(INPUT_DATE_FORMAT);
        return "E | " + super.getFileString() + " | " + formattedStartTime + " | " + formattedEndTime;
    }
}
