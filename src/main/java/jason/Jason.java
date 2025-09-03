package jason;

import jason.exception.JasonException;
import jason.task.*;
import jason.ui.Ui;
import jason.storage.Storage;
import jason.parser.Parser;

import java.util.ArrayList;


public class Jason {
	private TaskList taskList;
	private Storage storage;
	private Ui ui;
	private static boolean isFirstIteration = true;

	/**
	 * Construct a new Jason instance.
	 * Intializes the UI, Storage and load files from exiting file.
	 * If loading fails, create new empty task list.
	 *
	 */
	public Jason() {
		this.ui = new Ui("Jason");
		this.storage = new Storage("./data/Jason.txt");
		try {
			this.taskList = new TaskList(storage.load());
		} catch (Exception e) {
			System.out.println("Something went wrong: " + e.getMessage());
			this.taskList = new TaskList();
		}
	}

	public String getResponse(String input) {
		try {
			if (isFirstIteration) {
				isFirstIteration = false;
				return ui.showStart();

			} else if (input.equals("bye")) {
				return exitJason();

			} else if (input.equals("list")) {
				return list();

			} else if (input.startsWith("mark")) {
				return markTask(input, true);

			} else if (input.startsWith("unmark")) {
				return markTask(input, false);

			} else if (input.startsWith("todo")) {
				return todoAction(input);

			} else if (input.startsWith("deadline")) {
				return deadlineAction(input);

			} else if (input.startsWith("event")) {
				return eventAction(input);

			} else if (input.startsWith("delete")) {
				return deleteAction(input);

			} else if (input.startsWith("find")) {
				return findAction(input);

			} else {
				// invalid structure
				return "I am extremely sorry but give me a todo, deadline or event ૮(˶ㅠ︿ㅠ)ა";
			}
		} catch (JasonException e) {
			return e.getMessage();
		} catch (Exception e) {
			return "Something went wrong: " + e.getMessage();
		}
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
			System.out.println("Something went wrong: " + e.getMessage());
		}
	}

	/**
	 * Calls the showList function in ui to display the full list.
	 *
	 * @return Returns the string input for the list
	 */
	public String list() {
		return ui.showList(taskList);
	}

	/**
	 * Marks or unmarks a task as completed.
	 * Parses the input, updates tasks status, show confirmation and saves the changes to storage.
	 *
	 * @param input The user input string containing the task index.
	 * @param mark If true mark complete, if false mark uncomplete.
	 * @return Returns the string input for the markTask
	 * @throws JasonException If the task index is invalid or parsing fails.
	 */
	public String markTask(String input, Boolean mark) throws JasonException {
		int cut = 0;
		if (mark) {
			cut = 5;
		} else {
			cut = 7;
		}

		int taskIndex = Parser.parseIndex(input, cut) - 1;

		taskList.markTask(taskIndex, mark);
		Task temp = taskList.getTask(taskIndex);
		saveToStorage();
		return ui.showMarked(temp, mark);

	}

	/**
	 * Creates a new Todo task
	 * Parses the input, add it to the taskList, show confirmation and save to storage
	 *
	 * @param input The user input string to create a Todo task.
	 * @return Returns the string input for the todoAction
	 * @throws JasonException If Todo format is invalid or parsing fails.
	 */
	public String todoAction(String input) throws JasonException{

		Todo todo = Parser.parseTodo(input);
		taskList.add(todo);
		saveToStorage();
		return ui.showAddTask(todo, taskList.size());

	}

	/**
	 * Creates a new Deadline task
	 * Parses the input, add it to the taskList, show confirmation and save to storage
	 *
	 * @param input The user input string to create a Deadline task.
	 * @return Returns the string input for the deadlineAction
	 * @throws JasonException If Todo format is invalid or parsing fails.
	 */
	public String deadlineAction(String input) throws JasonException{

		Deadline deadline = Parser.parseDeadline(input);
		taskList.add(deadline);
		saveToStorage();
		return ui.showAddTask(deadline, taskList.size());

	}

	/**
	 * Creates a new Event task
	 * Parses the input, add it to the taskList, show confirmation and save to storage
	 *
	 * @param input The user input string to create a Event task.
	 * @return Returns the string input for the eventAction
	 * @throws JasonException If Event format is invalid or parsing fails.
	 */
	public String eventAction(String input) throws JasonException{

		Event event = Parser.parseEvent(input);
		taskList.add(event);
		saveToStorage();
		return ui.showAddTask(event, taskList.size());

	}

	/**
	 * Parses the input, delete it from the task list, show confirmation and save to storage
	 *
	 * @param input The user input string containing task index to delete
	 * @return Returns the string input for the deleteAction
	 * @throws JasonException If task index is invalid or parsing fails.
	 */
	public String deleteAction(String input) throws JasonException{
		int taskIndex = Parser.parseIndex(input, 6) - 1;


		Task delete = taskList.delete(taskIndex);
		saveToStorage();
		return ui.showDeleteTask(delete, taskList.size());


	}

	/**
	 * Parses the input, find it from the task list, show results
	 *
	 * @param input The user input string containing the keyword to find
	 * @return Returns the string input for the findAction
	 * @throws JasonException If task is not given
	 */
	public String findAction(String input) throws JasonException{
		String keyword = Parser.parseString(input);
		ArrayList<Task> tasks = taskList.findTask(keyword);
		return ui.showFindTask(tasks);
	}

	public String exitJason() {
		return ui.showEnd();
	}
}
