public class Parser {
    private Parser() {}

    public static Todo parseTodo(String input) throws JasonException {
        //check if todo is empty
        if (input.length() < 4) {
            throw new JasonException("Todo cannot be empty!");
        }

        String contents = input.substring(5);
        return new Todo(contents);
    }

    public static Deadline parseDeadline(String input) throws JasonException {
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
        String string = input.substring(index);

        //user needs to state number
        if (string.trim().isEmpty()) {
            throw new JasonException("Please specify task number");
        }

        try {
            return Integer.parseInt(string.trim());
        } catch (NumberFormatException e) {
            throw new JasonException("Give me a valid task number");
        }
    }

}