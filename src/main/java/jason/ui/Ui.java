package jason.ui;

import java.util.ArrayList;
import java.util.Scanner;
import jason.task.TaskList;
import jason.task.Task;

public class Ui {
    private Scanner scanner;
    private String name;

    /**
     * Constructs a new Ui instance
     * Initializes the scanner for reading user input from System.in.
     *
     * @param name Name to display in greetings
     */
    public Ui(String name) {
        this.scanner = new Scanner(System.in);
        this.name = name;
    }

    /**
     * Reads the user input
     *
     * @return returns the user input as a string
     */
    public String read() {
        return scanner.nextLine();
    }

    /**
     * closes the scanner
     */
    public void close() {
        scanner.close();
    }

    /**
     * Displays a horizontal line for formatting
     */
    public void showLine() {
        System.out.println("________________________________________");
    }

    /**
     * Displays the starting greeting message to welcome the user
     * Shows the application name and prompts the user for input
     */
    public void showStart() {
        showLine();
        System.out.println("Hello! I'm " + this.name);
        System.out.println("What can I do for you?");
        showLine();
    }

    /**
     * Displays the ending farewell message to welcome the user
     */
    public void showEnd() {
        showLine();
        System.out.println("Bye. Hope to see you again soon!");
        showLine();
    }

    /**
     * Displays an error message to the user
     *
     * @param message The error message to display to the user
     */
    public void showError(String message) {
        showLine();
        System.out.println("    " + message);
        showLine();
    }

    /**
     * Displays every task in the list
     * Shows the index number and the description of each task
     *
     * @param tasks The TaskList containing all tasks
     */
    public void showList(TaskList tasks) {
        showLine();
        System.out.println("	Here are the tasks in your list:");
        for (int i = 0; i < tasks.size(); i++) {
            System.out.println("	" + (i + 1) + ". " + tasks.getTask(i).toString());
        }
        showLine();
    }

    /**
     * Displays a confirmation message when a task is marked or unmarked
     *
     * @param task The task that was marked or unmarked
     * @param mark True if task is marked as done, false if task is unmarked
     */
    public void showMarked(Task task, boolean mark) {
        showLine();
        String message = mark ? "	Nice! I've marked this task as done:"
                : "	OK, I've marked this task as not done yet:";
        System.out.println(message);
        System.out.println("		" + task.toString());
        showLine();
    }

    /**
     * Displays a confirmation message when a task is added
     *
     * @param task The specific task added
     * @param totalTasks Total number of tasks in the list after adition
     */
    public void showAddTask(Task task, int totalTasks) {
        showLine();
        System.out.println("Got it. I've added this task:");
        System.out.println("   " + task.toString());
        System.out.println("Now you have " + totalTasks + " tasks in the list.");
        showLine();
    }

    /**
     * Displays a confirmation message when a task is deleted
     *
     * @param task The specific task deleted
     * @param totalTasks Total number of tasks in the list after deletion
     */
    public void showDeleteTask(Task task, int totalTasks) {
        showLine();
        System.out.println("Noted. I've removed this task:");
        System.out.println("    " + task.toString());
        System.out.println("Now you have " + totalTasks + " tasks in the list.");
        showLine();
    }

    /**
     * Displays a message showing the various tasks with the keyword
     *
     * @param tasks The list of tasks that contain an identical word to the keyword
     */
    public void showFindTask(ArrayList<Task> tasks) {
        showLine();
        if (tasks.isEmpty()) {
            System.out.println("    No matching tasks!");
        } else {
            System.out.println("    Here are the matching tasks in your list:");
            for (int i = 0; i < tasks.size(); i++) {
                System.out.println("    " + (i + 1) + "." + tasks.get(i).toString());
            }
        }
        showLine();
    }
}