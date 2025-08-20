import java.util.ArrayList;
import java.util.Scanner;

public class Jason {
	public static void main(String[] args) {
		Scanner scanner = new Scanner(System.in);
		String name = "Jason";
		String input = "";
		Boolean exit = false;
		ArrayList<String> storage = new ArrayList<>();

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
				for (int i = 0;i<storage.size();i++) {
					System.out.println("	" + (i + 1) + ". " + storage.get(i));
				}
				System.out.println("________________________________________");
			} else {
				System.out.println("________________________________________");
				System.out.println("	added: " + input);
				System.out.println("________________________________________");
				storage.add(input);
			}
		}

		System.out.println("________________________________________");
		System.out.println("Bye. Hope to see you again soon!");
		System.out.println("________________________________________");

		scanner.close();
	}
}
