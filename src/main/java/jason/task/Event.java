package jason.task;

import java.time.LocalDate;
import java.time.format.DateTimeFormatter;

public class Event extends Task {
    protected String from;
    protected String to;

    /**
     * Constructs a new Event task with the specified contents, starting date and ending date
     * Calls parseDate to attempt to parse the from and to string as a LocalDate.
     *
     * @param contents The description of the event task
     * @param from The from date string
     * @param to The to date string
     */
    public Event(String contents, String from, String to) {
        super(contents);

        assert from != null : "From cannot be null";
        assert to != null : "To cannot be null";

        this.from = parseDate(from);
        this.to = parseDate(to);
    }

    /**
     * Attempts to parse the date string as a LocalDate and formats it for display.
     * If parsing fails, the date is set to the original date string.
     *
     * @param date The from date or to date as a string
     * @return The formatted from or to date in "MMM d yyyy" format or
     * the from date or to date as a string
     */
    private String parseDate(String date) {
        try {
            LocalDate parsedDate = LocalDate.parse(date.trim());
            return parsedDate.format(DateTimeFormatter.ofPattern("MMM d yyyy"));

        } catch (Exception e) {
            return date;
        }
    }

    /**
     * Returns the formatted to string
     *
     * @return The formatted to date or the String version of the to date
     */
    public String getTo() {
        return this.to;
    }

    /**
     * Returns the formatted from string
     *
     * @return The formatted from date or the String version of the from date
     */
    public String getFrom() {
        return this.from;
    }

    @Override
    public String toString() {
        return "[E]" + super.toString() + " (from: " + this.from + " to: " + this.to + ")";
    }
}