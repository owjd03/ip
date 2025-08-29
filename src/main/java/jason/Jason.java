package jason;

import jason.exception.JasonException;
import jason.task.*;
import jason.ui.Ui;
import jason.storage.Storage;
import jason.parser.Parser;


public class Jason {
	private TaskList taskList;
	private Storage storage;
	private Ui ui;

	/**
	 * Construct a new Jason instance.
	 * Intializes the UI, Storage and load files from exiting file.
	 * If loading fails, create new empty task list.
	 *
	 * @param filePath The file path where the tasks are stored in the hard drive.
	 */
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

	/**
	 * Creates a new Jason instance and invokes the run function to start the application.
	 *
	 * @param args command line argument.
	 */

	public static void main(String[] args) {
		new Jason("./data/Jason.txt").run();
	}

	/**
	 * Runs the main loop.
	 * Reads the user input and process command until the user chooses to exit.
	 * Process commands include list, mark, unmark, todo, deadline, event and delete.
	 *
	 */
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
					list();

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

	/**
	 * Saves tasks into hard drive storage.
	 *
	 * @throws Exception If unable to save task.
	 */

	private void saveToStorage() {
		try {
			storage.save(taskList.getAllTasks());
		} catch (Exception e) {
			this.ui.showError("Something went wrong: " + e.getMessage());
		}
	}

	/**
	 * Calls the showList function in ui to display the full list.
	 *
	 */
	public void list() {
		ui.showList(taskList);
	}

	/**
	 * Marks or unmarks a task as completed.
	 * Parses the input, updates tasks status, show confirmation and saves the changes to storage.
	 *
	 * @param input The user input string containing the task index.
	 * @param mark If true mark complete, if false mark uncomplete.
	 * @throws JasonException If the task index is invalid or parsing fails.
	 */
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

	/**
	 * Creates a new Todo task
	 * Parses the input, add it to the taskList, show confirmation and save to storage
	 *
	 * @param input The user input string to create a Todo task.
	 * @throws JasonException If Todo format is invalid or parsing fails.
	 */
	public void todoAction(String input) throws JasonException{

		Todo todo = Parser.parseTodo(input);
		taskList.add(todo);
		ui.showAddTask(todo, taskList.size());
		saveToStorage();


	}

	/**
	 * Creates a new Deadline task
	 * Parses the input, add it to the taskList, show confirmation and save to storage
	 *
	 * @param input The user input string to create a Deadline task.
	 * @throws JasonException If Todo format is invalid or parsing fails.
	 */
	public void deadlineAction(String input) throws JasonException{

		Deadline deadline = Parser.parseDeadline(input);
		taskList.add(deadline);
		ui.showAddTask(deadline, taskList.size());

		saveToStorage();
	}

	/**
	 * Creates a new Event task
	 * Parses the input, add it to the taskList, show confirmation and save to storage
	 *
	 * @param input The user input string to create a Event task.
	 * @throws JasonException If Event format is invalid or parsing fails.
	 */
	public void eventAction(String input) throws JasonException{

		Event event = Parser.parseEvent(input);
		taskList.add(event);
		ui.showAddTask(event, taskList.size());

		saveToStorage();
	}

	/**
	 * Parses the input, delete it from the task list, show confirmation and save to storage
	 *
	 * @param input The user input string containing task index to delete
	 * @throws JasonException If task index is invalid or parsing fails.
	 */
	public void deleteAction(String input) throws JasonException{
		int taskIndex = Parser.parseIndex(input, 6) - 1;


		Task delete = taskList.delete(taskIndex);
		ui.showDeleteTask(delete, taskList.size());

		saveToStorage();

	}
}
