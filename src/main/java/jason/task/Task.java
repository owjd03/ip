package jason.task;
// Task class to handle tasks
public class Task {
    private String contents;
    private boolean done;

    /**
     * Constructs a new Task with the specified contents.
     * The task is initially marked as not done.
     *
     * @param contents The contents of the task
     */
    public Task(String contents) {
        this.contents = contents;
        this.done = false;
    }

    /**
     *Returns the contents of the task
     *
     * @return The contents of the task
     */
    public String getContents() {
        return this.contents;
    }

    /**
     * Marks the task as finished
     *
     */
    public void markDone() {
        this.done = true;
    }

    /**
     * Marks the task as unfinished
     *
     */
    public void markNotDone() {
        this.done = false;
    }

    /**
     * Returns a visual representation depending on whether the task is completed
     *
     * @return "[X]" if not done, "[ ]" if done
     */
    public String getDone() {
        return this.done ? "[X]" : "[ ]";
    }

    @Override
    public String toString() {
        return getDone() + " " + contents;
    }

}