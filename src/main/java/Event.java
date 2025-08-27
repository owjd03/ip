import java.time.LocalDate;
import java.time.format.DateTimeFormatter;

public class Event extends Task {
    protected String from;
    protected String to;

    public Event(String contents, String from, String to) {
        super(contents);
        this.from = parseDate(from);
        this.to = parseDate(to);
    }

    private String parseDate(String date) {
        try {
            LocalDate parsedDate = LocalDate.parse(date.trim());
            return parsedDate.format(DateTimeFormatter.ofPattern("MMM d yyyy"));

        } catch (Exception e) {
            return date;
        }
    }

    public String getTo() {
        return this.to;
    }

    public String getFrom() {
        return this.from;
    }

    @Override
    public String toString() {
        return "[E]" + super.toString() + " (from: " + this.from + " to: " + this.to + ")";
    }
}