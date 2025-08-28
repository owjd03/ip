package jason.task;

public class Todo extends Task {

    public Todo(String contents) {
        super(contents);
    }

    @Override
    public String toString() {
        return "[T]" + super.toString();
    }
}