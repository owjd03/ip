import java.util.ArrayList;
import java.util.Scanner;

public class Jason {
	private static ArrayList<Task> storage = new ArrayList<>();

	public static void main(String[] args) {
		Scanner scanner = new Scanner(System.in);
		String name = "Jason";
		String input = "";
		Boolean exit = false;

		System.out.println("________________________________________");
		System.out.println("Hello! I'm " + name);
		System.out.println("What can I do for you?");
		System.out.println("________________________________________");

		while (!exit) {
			input = scanner.nextLine();

			if (input.equals("bye")) {
				exit = true;

			} else if(input.equals("list")) {
				list(input);

			} else if(input.startsWith("mark")) {
				markTask(input, true);

			} else if(input.startsWith("unmark")) {
				markTask(input, false);

			} else if(input.startsWith("todo")) {
				todoAction(input);

			} else if(input.startsWith("deadline")) {
				deadlineAction(input);

			} else if(input.startsWith("event")) {
				eventAction(input);

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

	public static void list(String input) {
		System.out.println("________________________________________");
		System.out.println("	Here are the tasks in your list:");
		for (int i = 0; i < storage.size(); i++) {
			System.out.println("	" + (i + 1) + ". " + storage.get(i).toString());
		}
		System.out.println("________________________________________");
	}

	public static void markTask(String input, Boolean mark) {
		int cut = 0;
		if (mark) {
			cut = 5;
		} else {
			cut = 7;
		}

		int number = Integer.parseInt(input.substring(cut)) - 1;
		if (mark) {
			storage.get(number).markDone();

		} else {
			storage.get(number).markNotDone();
		}
		System.out.println("________________________________________");
		String message = mark ? "	Nice! I've marked this task as done:"
				: "	OK, I've marked this task as not done yet:";
		System.out.println(message);
		System.out.println("		" + storage.get(number).toString());
		System.out.println("________________________________________");
	}

	public static void todoAction(String input) {
		String contents = input.substring(5);
		Todo temp = new Todo(contents);
		storage.add(temp);

		System.out.println("________________________________________");
		System.out.println("Got it. I've added this task:");
		System.out.println("   " + temp.toString());
		System.out.println("Now you have " + storage.size() + " tasks in the list.");
		System.out.println("________________________________________");
	}

	public static void deadlineAction(String input) {
		String[] array =  input.substring(9).split(" /by ");
		String contents = array[0];
		String deadline = array[1];

		Deadline temp = new Deadline(contents, deadline);
		storage.add(temp);

		System.out.println("________________________________________");
		System.out.println("Got it. I've added this task:");
		System.out.println("   " + temp.toString());
		System.out.println("Now you have " + storage.size() + " tasks in the list.");
		System.out.println("________________________________________");
	}

	public static void eventAction(String input) {
		String[] array =  input.substring(6).split(" /from ");
		String contents = array[0];
		String time = array[1];

		String[] array1 = time.split(" /to ");
		String from = array1[0];
		String to = array1[1];

		Event temp = new Event(contents, from, to);
		storage.add(temp);

		System.out.println("________________________________________");
		System.out.println("Got it. I've added this task:");
		System.out.println("   " +  temp.toString());
		System.out.println("Now you have " + storage.size() + " tasks in the list.");
		System.out.println("________________________________________");
	}
}
