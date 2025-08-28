package jason.task;

import java.time.LocalDate;
import java.time.format.DateTimeFormatter;

public class Deadline extends Task {
    protected String deadline;
    protected String originalDeadline;

    public Deadline(String contents, String deadline) {
        super(contents);
        this.originalDeadline = deadline;
        try {
            LocalDate parsedDate = LocalDate.parse(deadline.trim());
            this.deadline = parsedDate.format(DateTimeFormatter.ofPattern("MMM d yyyy"));

        } catch (Exception e) {
            this.deadline = null;
        }
    }

    public String getDeadline() {
        return this.deadline;
    }

    @Override
    public String toString() {
        return "[D]" + super.toString() + " (by: " + this.deadline + ")";
    }
}