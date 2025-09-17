package jason.task;

import java.time.LocalDate;
import java.time.format.DateTimeFormatter;

// Task class to handle tasks
public class Task {
    private String contents;
    private boolean isDone;
    private boolean isSnoozed;
    private String snoozeDate;

    /**
     * Constructs a new Task with the specified contents.
     * The task is initially marked as not done.
     *
     * @param contents The contents of the task
     */
    public Task(String contents) {
        assert contents != null : "Contents cannot be null";

        this.contents = contents;
        this.isDone = false;
        this.isSnoozed = false;
        this.snoozeDate = null;
    }

    /**
     * Returns a string representation of the task, including its content
     *
     * @return A string containing the formatted details of this task.
     */
    public String getContents() {
        return this.contents;
    }

    /**
     * Marks the task as finished
     *
     */
    public void markDone() {
        this.isDone = true;
    }

    /**
     * Marks the task as unfinished
     *
     */
    public void markNotDone() {
        this.isDone = false;
    }

    /**
     * Returns a visual representation depending on whether the task is completed
     *
     * @return "[X]" if not done, "[ ]" if done
     */
    public String getDone() {
        return this.isDone ? "[X]" : "[ ]";
    }

    public void snooze(String date) {
        this.isSnoozed = true;
        this.snoozeDate = date;
    }

    public void unSnooze() {
        this.isSnoozed = false;
        this.snoozeDate = null;
    }

    public boolean isSnoozed() {
        return isSnoozed;
    }

    public String getSnoozeUntil() {
        return snoozeDate;
    }

    public String getSnoozeStatus() {
        if (isSnoozed()) {
            return " (snoozed until " + snoozeDate + ")";
        }
        return "";
    }

    @Override
    public String toString() {
        return getDone() + " " + contents + getSnoozeStatus();
    }

}