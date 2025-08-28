import java.util.ArrayList;
import java.util.Scanner;

public class Ui {
    private Scanner scanner;
    private String name;

    public Ui(String name) {
        this.scanner = new Scanner(System.in);
        this.name = name;
    }

    public String read() {
        return scanner.nextLine();
    }

    public void close() {
        scanner.close();
    }

    public void showLine() {
        System.out.println("________________________________________");
    }

    public void showStart() {
        showLine();
        System.out.println("Hello! I'm " + this.name);
        System.out.println("What can I do for you?");
        showLine();
    }

    public void showEnd() {
        showLine();
        System.out.println("Bye. Hope to see you again soon!");
        showLine();
    }

    public void showError(String message) {
        showLine();
        System.out.println("    " + message);
        showLine();
    }

    public void showList(TaskList tasks) {
        showLine();
        System.out.println("	Here are the tasks in your list:");
        for (int i = 0; i < tasks.size(); i++) {
            System.out.println("	" + (i + 1) + ". " + tasks.getTask(i).toString());
        }
        showLine();
    }

    public void showMarked(Task task, boolean mark) {
        showLine();
        String message = mark ? "	Nice! I've marked this task as done:"
                : "	OK, I've marked this task as not done yet:";
        System.out.println(message);
        System.out.println("		" + task.toString());
        showLine();
    }

    public void showAddTask(Task task, int totalTasks) {
        showLine();
        System.out.println("Got it. I've added this task:");
        System.out.println("   " + task.toString());
        System.out.println("Now you have " + totalTasks + " tasks in the list.");
        showLine();
    }

    public void showDeleteTask(Task task, int totalTasks) {
        showLine();
        System.out.println("Noted. I've removed this task:");
        System.out.println("    " + task.toString());
        System.out.println("Now you have " + totalTasks + " tasks in the list.");
        showLine();
    }


}