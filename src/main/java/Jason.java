import java.util.ArrayList;
import java.util.Scanner;

public class Jason {
	private TaskList taskList;
	private Storage storage;
	private Ui ui;

	private Jason(String filePath) {
		this.ui = new Ui("Jason");
		this.storage = new Storage(filePath);
		try {
			this.taskList = new TaskList(storage.load());
		} catch (Exception e) {
			System.out.println("Something went wrong: " + e.getMessage());
			this.taskList = new TaskList();
		}
	}

	public static void main(String[] args) {
		new Jason("./data/Jason.txt").run();
	}

	public void run () {
		String input = "";
		boolean exit = false;

		ui.showStart();

		while (!exit) {
			input = ui.read();

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
				ui.showError(e.getMessage());
			} catch (Exception e) {
				ui.showError("Something went wrong: " + e.getMessage());
			}
		}

		this.ui.showEnd();
		this.ui.close();
	}

	private void saveToStorage() {
		try {
			storage.save(taskList.getAllTasks());
		} catch (Exception e) {
			this.ui.showError("Something went wrong: " + e.getMessage());
		}
	}

	public void list(String input) {
		ui.showList(taskList);
	}

	public void markTask(String input, Boolean mark) throws JasonException {
		int cut = 0;
		if (mark) {
			cut = 5;
		} else {
			cut = 7;
		}

		int taskIndex = Parser.parseIndex(input, cut) - 1;

		taskList.markTask(taskIndex, mark);
		Task temp = taskList.getTask(taskIndex);
		ui.showMarked(temp, mark);
		saveToStorage();

	}

	public void todoAction(String input) throws JasonException{

		Todo todo = Parser.parseTodo(input);
		taskList.add(todo);
		ui.showAddTask(todo, taskList.size());
		saveToStorage();


	}

	public void deadlineAction(String input) throws JasonException{

		Deadline deadline = Parser.parseDeadline(input);
		taskList.add(deadline);
		ui.showAddTask(deadline, taskList.size());

		saveToStorage();
	}

	public void eventAction(String input) throws JasonException{

		Event event = Parser.parseEvent(input);
		taskList.add(event);
		ui.showAddTask(event, taskList.size());

		saveToStorage();
	}

	public void deleteAction(String input) throws JasonException{
		int taskIndex = Parser.parseIndex(input, 6) - 1;


		Task delete = taskList.delete(taskIndex);
		ui.showDeleteTask(delete, taskList.size());

		saveToStorage();

	}
}
