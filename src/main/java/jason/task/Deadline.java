package jason.task;

import java.time.LocalDate;
import java.time.format.DateTimeFormatter;

public class Deadline extends Task {
    protected String deadline;

    /**
     * Constructs a new Deadline task with the specified contents and deadline.
     * Attempts to parse the deadline string as a LocalDate and formats it for display.
     * If parsing fails, the deadline is set to the original deadline string.
     *
     * @param contents The description of the deadline task
     * @param deadline The deadline date string
     */
    public Deadline(String contents, String deadline) {
        super(contents);
        try {
            LocalDate parsedDate = LocalDate.parse(deadline.trim());
            this.deadline = parsedDate.format(DateTimeFormatter.ofPattern("MMM d yyyy"));

        } catch (Exception e) {
            this.deadline = deadline;
        }
    }

    /**
     * Returns the formatted deadline string
     *
     * @return The formatted deadline or the String version of the deadline
     */
    public String getDeadline() {
        return this.deadline;
    }

    @Override
    public String toString() {
        return "[D]" + super.toString() + " (by: " + this.deadline + ")";
    }
}