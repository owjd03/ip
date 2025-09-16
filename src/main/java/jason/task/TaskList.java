package jason.task;

import java.util.ArrayList;
import jason.exception.JasonException;

public class TaskList {
    private ArrayList<Task> tasks;

    /**
     * Creates an empty TaskList with no tasks.
     * Initializes the internal ArrayList to store tasks.
     */
    public TaskList() {
        this.tasks = new ArrayList<>();
    }

    /**
     * Creates a TaskList with existing tasks.
     * Initializes the TaskList with a pre-existing collection of tasks.
     *
     * @param tasks ArrayList of existing tasks to initialize the TaskList with
     */
    public TaskList(ArrayList<Task> tasks) {
        this.tasks = tasks;
    }

    /**
     * Adds a new task to the end of the task list.
     * Appends the specified task to the internal ArrayList.
     *
     * @param task The task to be added to the list
     */
    public void add(Task task) {
        tasks.add(task);
    }

    /**
     * Removes and returns a task at the specified index.
     * Validates the index and throws an exception if invalid.
     *
     * @param index The zero-based index of the task to delete
     * @return The deleted Task object
     * @throws JasonException If the index is out of bounds or negative
     */
    public Task delete(int index) throws JasonException{
        if (index >= tasks.size() || index < 0) {
            throw new JasonException("Give me a valid task number");
        }

        return tasks.remove(index);
    }

    /**
     * Retrieves a task at the specified index without removing it.
     * Returns the task object for viewing or manipulation.
     *
     * @param index The zero-based index of the task to retrieve
     * @return The Task object at the specified index
     */
    public Task getTask(int index) {
        //check index given is not in the list
        return tasks.get(index);
    }

    /**
     * Returns the complete list of all tasks.
     * Provides access to the internal ArrayList for operations like saving to storage.
     *
     * @return ArrayList containing all tasks in the TaskList
     */
    public ArrayList<Task> getAllTasks() {
        return tasks;
    }

    /**
     * Returns the total number of tasks in the list.
     * Provides the count of tasks currently stored in the TaskList.
     *
     * @return The number of tasks in the list
     */
    public int size() {
        return this.tasks.size();
    }

    /**
     * Marks a task as done or not done based on the specified boolean value.
     * Updates the completion status of the task at the given index.
     *
     * @param index The zero-based index of the task to mark
     * @param isDone True to mark as done, false to mark as not done
     * @throws JasonException If the index is invalid or out of bounds
     */
    public void markTask(int index, boolean isDone) throws JasonException {
        assert index >= 0 : "Index cannot be less than 0";

        Task task = this.tasks.get(index); // This will throw exception if invalid index
        if (isDone) {
            task.markDone();
        } else {
            task.markNotDone();
        }
    }

    /**
     * Searches for tasks containing the specified keyword in their description.
     * Performs case-insensitive search through all task contents and returns matching tasks.
     *
     * @param keyword The search term to look for in task descriptions
     * @return ArrayList of tasks that contain the keyword in their contents
     */
    public ArrayList<Task> findTask(String keyword) {
        assert keyword != null : "Keyword cannot be null";

        ArrayList<Task> matchingWords = new ArrayList<>();

        for (Task task : this.tasks) {
            if (task.getContents().toLowerCase().contains(keyword.toLowerCase())) {
                matchingWords.add(task);
            }
        }

        return matchingWords;
    }
}