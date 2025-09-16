package jason.parser;

import jason.exception.JasonException;
import jason.task.Todo;
import jason.task.Deadline;
import jason.task.Event;
import java.time.LocalDate;
import java.time.format.DateTimeFormatter;

public class Parser {

    /**
     * Private constructor to prevent instantiation.
     * This is a utility class with only static methods.
     */
    private Parser() {}

    /**
     * Parses user input to create a Todo task.
     * Extracts the task description from the input string after validation.
     *
     * @param input The user input string in format "todo <description>"
     * @return A new Todo object with the specified description
     * @throws JasonException If the input is too short or empty
     */
    public static Todo parseTodo(String input) throws JasonException {
        assert input != null : "input cannot be null";

        //check if todo is empty
        if (input.length() < 4) {
            throw new JasonException("Todo cannot be empty!");
        }

        String contents = input.substring(5);
        return new Todo(contents);
    }

    /**
     * Parses user input to create a Deadline task.
     * Extracts the task description and deadline from the input string.
     * Validates the presence of the "/by" delimiter and non-empty fields.
     *
     * @param input The user input string in format "deadline <description> /by <date>"
     * @return A new Deadline object with the specified description and deadline
     * @throws JasonException If the input format is invalid, missing "/by", or has empty fields
     */
    public static Deadline parseDeadline(String input) throws JasonException {
        assert input != null : "input cannot be null";

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


        return new Deadline(contents, deadline);
    }

    /**
     * Parses user input to create an Event task.
     * Extracts the task description, start time, and end time from the input string.
     * Validates the presence of both "/from" and "/to" delimiters and non-empty fields.
     *
     * @param input The user input string in format "event <description> /from <start> /to <end>"
     * @return A new Event object with the specified description, start time, and end time
     * @throws JasonException If the input format is invalid, missing delimiters, or has empty fields
     */
    public static Event parseEvent(String input) throws JasonException {
        assert input != null : "input cannot be null";

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

        return new Event(contents, from, to);
    }

    /**
     * Parses and extracts a task index from user input.
     * Validates that a task number is provided and that it's a valid integer.
     *
     * @param input The user input string containing a command and task number
     * @param index The starting position for parsing (unused parameter)
     * @return The parsed task index as an integer
     * @throws JasonException If no task number is specified or if the number is invalid
     */
    public static int parseIndex(String input, int index) throws JasonException {
        assert input != null : "input cannot be null";

        String[] stringArray = input.split(" ");

        //user needs to state number
        if (stringArray.length < 2) {
            throw new JasonException("Please specify task number");
        }

        try {
            return Integer.parseInt(stringArray[1]);
        } catch (NumberFormatException e) {
            throw new JasonException("Give me a valid task number");
        }
    }

    /**
     * Parses and extracts a search keyword from user input.
     * Validates that a keyword is provided and not empty after trimming.
     *
     * @param input The user input string in format "find <keyword>"
     * @return The search keyword as a trimmed string
     * @throws JasonException If the input is too short or keyword is empty
     */
    public static String parseString(String input) throws JasonException {
        assert input != null : "input cannot be null";

        if (input.length() < 5) {
            throw new JasonException("Specify a keyword to search for!");
        }

        String keyword = input.substring(5).trim();

        if (keyword.isEmpty()) {
            throw new JasonException("Specify a keyword to search for!");
        }

        return keyword;
    }

    /**
     * Parses and extracts a snooze date from user input.
     * Validates that a date is provided and attempts to parse it using parseDate method.
     *
     * @param input The user input string in format "snooze <taskNumber> <date>"
     * @return The formatted snooze date string
     * @throws JasonException If no date is specified or the date format is invalid
     */
    public static String parseSnoozeDate(String input) throws JasonException {
        assert input != null : "input cannot be null";

        String[] stringArray = input.split(" ");

        if (stringArray.length < 3) {
            throw new JasonException("Please specify snooze date");
        }

        try {
            return parseDate(stringArray[2]);
        } catch (NumberFormatException e) {
            throw new JasonException("Give me a valid dater");
        }
    }

    /**
     * Parses various date formats and converts them to a standardized format.
     * Handles special keywords like "today" and "tomorrow" as well as ISO date format.
     * Returns dates in "MMM d yyyy" format for consistent display.
     *
     * @param date The date string to parse (can be "today", "tomorrow", or ISO format)
     * @return The formatted date string in "MMM d yyyy" format
     * @throws JasonException If the date format is invalid or cannot be parsed
     */
    public static String parseDate(String date) throws JasonException {
        DateTimeFormatter formatter = DateTimeFormatter.ofPattern("MMM d yyyy");

        if (date.equals("tomorrow")) {
            return LocalDate.now().plusDays(1).format(formatter);
        } else if (date.equals("today")) {
            return LocalDate.now().format(formatter);
        } else {
            try {
                LocalDate dateFormat = LocalDate.parse(date);
                return dateFormat.format(formatter);
            } catch (Exception e) {
                throw new JasonException("Invalid date format");
            }
        }
    }
}