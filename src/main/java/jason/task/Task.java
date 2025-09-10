package jason.task;

import java.time.LocalDate;
import java.time.format.DateTimeFormatter;

// Task class to handle tasks
public class Task {
    private String contents;
    private boolean done;
    private boolean snoozed;
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
        this.done = false;
        this.snoozed = false;
        this.snoozeDate = null;
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

    public void snooze(String date) {
        this.snoozed = true;
        this.snoozeDate = date;
    }

    public void unSnooze() {
        this.snoozed = false;
        this.snoozeDate = null;
    }

    public boolean isSnoozed() {
        return snoozed;
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