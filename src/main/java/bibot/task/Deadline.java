package bibot.task;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.time.format.DateTimeParseException;

import bibot.BibotException;

public class Deadline extends Task {
    private LocalDateTime date;
    private static final DateTimeFormatter inputFormat =
            DateTimeFormatter.ofPattern("dd/MM/yyyy HHmm");
    private static final DateTimeFormatter displayFormat =
            DateTimeFormatter.ofPattern("MMM dd yyyy HH:mm");

    public Deadline(String description, String date) throws BibotException {
        super(description);
        try {
            this.date = LocalDateTime.parse(date, inputFormat);
        } catch (DateTimeParseException exception) {
            throw new BibotException(
                "Invalid datetime format: use dd/mm/yyyy hhmm (e.g. 12/02/2025 1400)");
        }
    }

    public Deadline(String description, String date, boolean isDone) throws BibotException {
        super(description, isDone);
        try {
            this.date = LocalDateTime.parse(date, inputFormat);
        } catch (DateTimeParseException exception) {
            throw new BibotException(
                "Invalid datetime format: use dd/mm/yyyy hhmm (e.g. 12/02/2025 1400)");
        }
    }

    @Override
    public String toString() {
        return String.format("[D]%s (by: %s)", super.toString(), this.date.format(displayFormat));
    }

    @Override
    public String fileString() {
        return "D | " + super.fileString() + " | " + this.date.format(inputFormat);
    }
}
