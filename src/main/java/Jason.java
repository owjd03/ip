import java.util.Scanner;

public class Jason {
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
			} else {
				System.out.println("________________________________________");
				System.out.println("     " + input);
				System.out.println("________________________________________");
			}
		}

		System.out.println("________________________________________");
		System.out.println("Bye. Hope to see you again soon!");
		System.out.println("________________________________________");

		scanner.close();
	}
}
