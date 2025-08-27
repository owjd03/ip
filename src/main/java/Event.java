public class Event extends Task {
    protected String from;
    protected String to;

    public Event(String contents, String from, String to) {
        super(contents);
        this.from = from;
        this.to = to;
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