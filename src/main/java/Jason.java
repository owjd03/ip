import java.util.ArrayList;
import java.util.Scanner;

public class Jason {
	public static void main(String[] args) {
		Scanner scanner = new Scanner(System.in);
		String name = "Jason";
		String input = "";
		Boolean exit = false;
		ArrayList<Task> storage = new ArrayList<>();

		System.out.println("________________________________________");
		System.out.println("Hello! I'm " + name);
		System.out.println("What can I do for you?");
		System.out.println("________________________________________");

		while (!exit) {
			input = scanner.nextLine();

			if (input.equals("bye")) {
				exit = true;

			} else if(input.equals("list")) {
				System.out.println("________________________________________");
				System.out.println("	Here are the tasks in your list:");
				for (int i = 0; i < storage.size(); i++) {
					System.out.println("	" + (i + 1) + ". " + storage.get(i).toString());
				}
				System.out.println("________________________________________");

			} else if(input.startsWith("mark")) {
				int number = Integer.parseInt(input.substring(5)) - 1;
				storage.get(number).markDone();

				System.out.println("________________________________________");
				System.out.println("	Nice! I've marked this task as done:");
				System.out.println("		" + storage.get(number).toString());
				System.out.println("________________________________________");

			} else if(input.startsWith("unmark")) {
				int number = Integer.parseInt(input.substring(7)) - 1;
				storage.get(number).markNotDone();

				System.out.println("________________________________________");
				System.out.println("	OK, I've marked this task as not done yet:");
				System.out.println("	   " + storage.get(number).toString());
				System.out.println("________________________________________");

			} else {
				System.out.println("________________________________________");
				System.out.println("	added: " + input);
				System.out.println("________________________________________");

				Task temp = new Task(input);
				storage.add(temp);
			}
		}

		System.out.println("________________________________________");
		System.out.println("Bye. Hope to see you again soon!");
		System.out.println("________________________________________");

		scanner.close();
	}
}

// Task class to handle tasks
class Task {
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
