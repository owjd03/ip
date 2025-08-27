import java.util.ArrayList;
import java.util.Scanner;

public class Jason {
	private static ArrayList<Task> storage = new ArrayList<>();
	private static Storage fileStorage = new Storage("./data/Jason.txt");

	public static void main(String[] args) {
		Scanner scanner = new Scanner(System.in);
		String name = "Jason";
		String input = "";
		Boolean exit = false;

		System.out.println("________________________________________");
		System.out.println("Hello! I'm " + name);
		System.out.println("What can I do for you?");
		System.out.println("________________________________________");

		try {
			storage = fileStorage.load();
		} catch (Exception e) {
			System.out.println("Something went wrong: " + e.getMessage());
		}


		while (!exit) {
			input = scanner.nextLine();

			try {
				if (input.equals("bye")) {
					exit = true;

				} else if (input.equals("list")) {
					list(input);

				} else if (input.startsWith("mark")) {
					markTask(input, true);

				} else if (input.startsWith("unmark")) {
					markTask(input, false);

				} else if (input.startsWith("todo")) {
					todoAction(input);

				} else if (input.startsWith("deadline")) {
					deadlineAction(input);

				} else if (input.startsWith("event")) {
					eventAction(input);

				} else if (input.startsWith("delete")) {
					deleteAction(input);

				} else {
					// invalid structure
					throw new JasonException("I am extremely sorry but give me a todo, deadline or event ૮(˶ㅠ︿ㅠ)ა");
				}
			} catch (JasonException e) {
				System.out.println("________________________________________");
				System.out.println("	" + e.getMessage());
				System.out.println("________________________________________");
			} catch (Exception e) {
				System.out.println("________________________________________");
				System.out.println("Something went wrong: " + e.getMessage());
				System.out.println("________________________________________");
			}
		}

		System.out.println("________________________________________");
		System.out.println("Bye. Hope to see you again soon!");
		System.out.println("________________________________________");

		scanner.close();
	}

	private static void saveToStorage() {
		try {
			fileStorage.save(storage);
		} catch (Exception e) {
			System.out.println("Something went wrong: " + e.getMessage());
		}
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

		try {
			String stringNumber = input.substring(cut);

			//never give number
			if (stringNumber.trim().isEmpty()) {
				throw new JasonException("Please specify task number");
			}
			int number = Integer.parseInt(stringNumber) - 1;

			//number given is not in the list
			if (number >= storage.size() || number < 0) {
				throw new JasonException("Give me a valid task number");
			}

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

			saveToStorage();
		} catch (JasonException e) {
			System.out.println("________________________________________");
			System.out.println("	Bro! " + e.getMessage());
			System.out.println("________________________________________");
		}
	}

	public static void todoAction(String input) throws JasonException{
		//check if todo is empty
		if (input.length() < 4) {
			throw new JasonException("Todo cannot be empty!");
		}

		String contents = input.substring(5);
		Todo temp = new Todo(contents);
		storage.add(temp);

		System.out.println("________________________________________");
		System.out.println("Got it. I've added this task:");
		System.out.println("   " + temp.toString());
		System.out.println("Now you have " + storage.size() + " tasks in the list.");
		System.out.println("________________________________________");

		saveToStorage();


	}

	public static void deadlineAction(String input) throws JasonException{
		//check if deadline is empty
		if (input.length() < 8) {
			throw new JasonException("deadline cannot be empty!");
		}

		// check if user included by
		if (!input.contains("/by")) {
			throw new JasonException("Give me a deadline! Include /by");
		}

		String[] array =  input.substring(9).split(" /by ");
		String contents = array[0];
		String deadline = array[1];

		// empty contents and deadline
		if (contents.trim().isEmpty() || deadline.trim().isEmpty()) {
			throw new JasonException("Give me something in your task and deadline");
		}

		Deadline temp = new Deadline(contents, deadline);
		storage.add(temp);

		System.out.println("________________________________________");
		System.out.println("Got it. I've added this task:");
		System.out.println("   " + temp.toString());
		System.out.println("Now you have " + storage.size() + " tasks in the list.");
		System.out.println("________________________________________");

		saveToStorage();
	}

	public static void eventAction(String input) throws JasonException{
		// check if event is empty
		if (input.length() < 5) {
			throw new JasonException("Event cannot be empty!");
		}

		if (!input.contains("/to") || !input.contains("/from")) {
			throw new JasonException("Give me a duration! Include /from and /to");
		}

		String[] array =  input.substring(6).split(" /from ");
		String contents = array[0];
		String time = array[1];

		String[] array1 = time.split(" /to ");
		String from = array1[0];
		String to = array1[1];

		if (contents.trim().isEmpty() || from.trim().isEmpty() || to.trim().isEmpty()) {
			throw new JasonException("give me something in your task, from and to!");
		}

		Event temp = new Event(contents, from, to);
		storage.add(temp);

		System.out.println("________________________________________");
		System.out.println("Got it. I've added this task:");
		System.out.println("   " +  temp.toString());
		System.out.println("Now you have " + storage.size() + " tasks in the list.");
		System.out.println("________________________________________");

		saveToStorage();
	}

	public static void deleteAction(String input) throws JasonException{
		String stringDelete = input.substring(6);

		//user needs to state number
		if (stringDelete.trim().isEmpty()) {
			throw new JasonException("Please specify task number");
		}

		int delete = Integer.parseInt(stringDelete.trim()) - 1;

		if (delete >= storage.size() || delete < 0) {
			throw new JasonException("Give me a valid task number");
		}

		System.out.println("________________________________________");
		System.out.println("Noted. I've removed this task:");
		System.out.println("    " + storage.get(delete).toString());
		System.out.println("Now you have " + (storage.size() - 1) + " tasks in the list.");
		System.out.println("________________________________________");

		storage.remove(delete);

		saveToStorage();

	}
}
