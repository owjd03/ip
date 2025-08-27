public class Deadline extends Task {
    protected String deadline;

    public Deadline(String contents, String deadline) {
        super(contents);
        this.deadline = deadline;
    }

    public String getDeadline() {
        return this.deadline;
    }


    @Override
    public String toString() {
        return "[D]" + super.toString() + " (by: " + this.deadline + ")";
    }
}