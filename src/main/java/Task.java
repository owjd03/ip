// Task class to handle tasks
public class Task {
    private String contents;
    private boolean done;

    public Task(String contents) {
        this.contents = contents;
        this.done = false;
    }

    public String getContents() {
        return this.contents;
    }

    public void markDone() {
        this.done = true;
    }

    public void markNotDone() {
        this.done = false;
    }

    public String getDone() {
        return this.done ? "[X]" : "[ ]";
    }

    @Override
    public String toString() {
        return getDone() + " " + contents;
    }

}