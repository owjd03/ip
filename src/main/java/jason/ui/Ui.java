package jason.ui;

import java.util.ArrayList;
import jason.task.TaskList;
import jason.task.Task;

public class Ui {
    private String name;

    /**
     * Constructs a new Ui instance
     * Initializes the scanner for reading user input from System.in.
     *
     * @param name Name to display in greetings
     *
     */
    public Ui(String name) {
        this.name = name;
    }

    /**
     * Displays the starting greeting message to welcome the user
     * Shows the application name and prompts the user for input
     *
     * @return Returns the string input for showStart
     */
    public String showStart() {
        return "Hello! I'm " + this.name + "\nWhat can I do for you?";
    }

    /**
     * Displays the ending farewell message to welcome the user
     *
     * @return Returns the string input for the showEnd
     */
    public String showEnd() {
        return "Bye. Hope to see you again soon!";
    }

    /**
     * Displays every task in the list
     * Shows the index number and the description of each task
     *
     * @param tasks The TaskList containing all tasks
     * @return Returns the string input for the showList
     */
    public String showList(TaskList tasks) {
        StringBuilder showListString = new StringBuilder("Here are the tasks in your list:");
        for (int i = 0; i < tasks.size(); i++) {
            showListString.append("\n")
                    .append(i + 1)
                    .append(". ")
                    .append(tasks.getTask(i).toString());
        }
        return showListString.toString();
    }

    /**
     * Displays a confirmation message when a task is marked or unmarked
     *
     * @param task The task that was marked or unmarked
     * @param mark True if task is marked as done, false if task is unmarked
     * @return Returns the string input for the showMarked.
     */
    public String showMarked(Task task, boolean mark) {
        String message = mark ? "	Nice! I've marked this task as done:"
                : "	OK, I've marked this task as not done yet:";
        return message + "\n" + task.toString();
    }

    /**
     * Displays a confirmation message when a task is added
     *
     * @param task The specific task added
     * @param totalTasks Total number of tasks in the list after addition
     * @return Returns the string input for the showAddTask
     */
    public String showAddTask(Task task, int totalTasks) {
        return String.format("Got it. I've added this task:\n  %s\nNow you have %d tasks in the list.",
                task.toString(), totalTasks);
    }

    /**
     * Displays a confirmation message when a task is deleted
     *
     * @param task The specific task deleted
     * @param totalTasks Total number of tasks in the list after deletion
     * @return Returns the string input for the showDeleteTask
     */
    public String showDeleteTask(Task task, int totalTasks) {
        return String.format("Got it. I've removed this task:\n  %s\nNow you have %d tasks in the list.",
                task.toString(), totalTasks);
    }

    /**
     * Displays a message showing the various tasks with the keyword
     *
     * @param tasks The list of tasks that contain an identical word to the keyword
     * @return Returns the string input for the showFindTask
     */
    public String showFindTask(ArrayList<Task> tasks) {
        StringBuilder showFindTaskString = new StringBuilder();
        if (tasks.isEmpty()) {
            showFindTaskString.append("No matching tasks!");
        } else {
            showFindTaskString.append("Here are the matching tasks in your list:");
            for (int i = 0; i < tasks.size(); i++) {
                showFindTaskString.append("\n")
                        .append(i + 1)
                        .append(". ")
                        .append(tasks.get(i).toString());
            }
        }
        return showFindTaskString.toString();
    }

}