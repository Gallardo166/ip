package bibot.task;

import bibot.BibotException;

import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.time.format.DateTimeParseException;

/**
 * Represents a task with a deadline.
 */
public class Deadline extends Task {
    private LocalDateTime date;

    private static final DateTimeFormatter INPUT_DATE_FORMAT
            = DateTimeFormatter.ofPattern("dd/MM/yyyy HHmm");
    private static final DateTimeFormatter DISPLAY_DATE_FORMAT
            = DateTimeFormatter.ofPattern("MMM dd yyyy HH:mm");

    private static final String INVALID_FORMAT_ERROR_MESSAGE
            = "Invalid datetime format: use dd/mm/yyyy hhmm (e.g. 12/02/2025 1400)";

    private static final long UPCOMING_INTERVAL_IN_DAYS = 1;

    /**
     * Constructs a new <code>Deadline</code> representing a task with a deadline.
     * The <code>Deadline</code> is initially not completed.
     * 
     * @throws BibotException If the given date is not in valid format.
     */
    public Deadline(String description, String date) throws BibotException {
        super(description);
        try {
            this.date = LocalDateTime.parse(date, INPUT_DATE_FORMAT);
        } catch (DateTimeParseException exception) {
            throw new BibotException(INVALID_FORMAT_ERROR_MESSAGE);
        }
    }

    /**
     * Constructs a new <code>Deadline</code> representing a task with a deadline.
     * 
     * @throws BibotException If the given date is not in valid format.
     */
    public Deadline(String description, String date, boolean isDone) throws BibotException {
        super(description, isDone);
        try {
            this.date = LocalDateTime.parse(date, INPUT_DATE_FORMAT);
        } catch (DateTimeParseException exception) {
            throw new BibotException(INVALID_FORMAT_ERROR_MESSAGE);
        }
    }

    @Override
    public boolean isUpcoming() {
        LocalDateTime upcomingMaximum = LocalDateTime.now().plusDays(UPCOMING_INTERVAL_IN_DAYS);
        return this.date.isBefore(upcomingMaximum);
    }

    @Override
    public String toString() {
        return String.format("[D]%s (by: %s)", super.toString(), this.date.format(DISPLAY_DATE_FORMAT));
    }

    @Override
    public String getFileString() {
        return "D | " + super.getFileString() + " | " + this.date.format(INPUT_DATE_FORMAT);
    }
}
