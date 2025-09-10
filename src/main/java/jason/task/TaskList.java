package jason.task;

import java.util.ArrayList;
import jason.exception.JasonException;

public class TaskList {
    private ArrayList<Task> tasks;

    public TaskList() {
        this.tasks = new ArrayList<>();
    }

    public TaskList(ArrayList<Task> tasks) {
        this.tasks = tasks;
    }

    public void add(Task task) {
        tasks.add(task);
    }

    public Task delete(int index) throws JasonException{
        if (index >= tasks.size() || index < 0) {
            throw new JasonException("Give me a valid task number");
        }

        return tasks.remove(index);
    }

    public Task getTask(int index) {
        //check index given is not in the list
        return tasks.get(index);
    }

    public ArrayList<Task> getAllTasks() {
        return tasks;
    }

    public int size() {
        return this.tasks.size();
    }

    public void markTask(int index, boolean isDone) throws JasonException {
        assert index >= 0 : "Index cannot be less than 0";

        Task task = this.tasks.get(index); // This will throw exception if invalid index
        if (isDone) {
            task.markDone();
        } else {
            task.markNotDone();
        }
    }

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