package jason.parser;

import jason.exception.JasonException;
import jason.task.Todo;
import jason.task.Deadline;
import jason.task.Event;
import java.time.LocalDate;
import java.time.format.DateTimeFormatter;

public class Parser {
    private Parser() {}

    public static Todo parseTodo(String input) throws JasonException {
        assert input != null : "input cannot be null";

        //check if todo is empty
        if (input.length() < 4) {
            throw new JasonException("Todo cannot be empty!");
        }

        String contents = input.substring(5);
        return new Todo(contents);
    }

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