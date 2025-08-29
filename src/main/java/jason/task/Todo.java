package jason.task;

public class Todo extends Task {

    /**
     * Constructs a new Todo task with the specified contents and deadline..
     *
     * @param contents The description of the Todo task
     */
    public Todo(String contents) {
        super(contents);
    }

    @Override
    public String toString() {
        return "[T]" + super.toString();
    }
}