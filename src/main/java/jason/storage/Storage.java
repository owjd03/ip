package jason.storage;

import java.io.File;
import java.io.FileWriter;
import java.io.IOException;
import java.util.ArrayList;
import java.util.Scanner;
import jason.task.Task;
import jason.task.Todo;
import jason.task.Deadline;
import jason.task.Event;

public class Storage {
    private String filePath;

    /**
     * Constructs a new Storage instance with the specified file path.
     * Initializes the storage handler to work with the given file location.
     *
     * @param filePath The path to the file where tasks will be stored
     */
    public Storage(String filePath) {
        this.filePath = filePath;
    }

    /**
     * Loads tasks from the storage file and returns them as an ArrayList.
     * Creates an empty list if the file doesn't exist. Parses each line
     * of the file to reconstruct task objects with their saved state.
     *
     * @return ArrayList of Task objects loaded from the storage file
     * @throws Exception If there are issues reading from the file
     */
    public ArrayList<Task> load() throws Exception{
        ArrayList<Task> tempList =  new ArrayList<>();
        File file = new File(filePath);

        if (!file.exists()) {
            return tempList;
        }

        Scanner scanner = new Scanner(file);

        while (scanner.hasNextLine()) {
            String line = scanner.nextLine();
            Task task = checkTask(line);
            if (task != null) {
                tempList.add(task);
            }
        }

        scanner.close();

        return tempList;
    }

    /**
     * Parses a single line from the storage file and reconstructs the corresponding Task object.
     * Handles different task types (Todo, Deadline, Event) and restores their completion
     * status and snooze information. Uses pipe-delimited format for data extraction.
     *
     * @param line A single line from the storage file containing task data
     * @return The reconstructed Task object, or null if the line format is invalid
     */
    public Task checkTask(String line) {
        assert line != null : "line cannot be null";

        String[] stringArray = line.split(" \\| ");

        if (stringArray.length < 3) {
            return null;
        }

        String type = stringArray[0];
        Boolean isDone = stringArray[1].equals("1");
        String description = stringArray[2];

        assert type != null : "type cannot be null";
        assert description!= null : "description cannot be null";

        Task task;

        if (type.equals("T")) {
            task = new Todo(description);

            if (stringArray.length > 3 && !stringArray[3].equals("null")) {
                task.snooze(stringArray[3]);
            }
        } else if (type.equals("D")) {
            task = new Deadline(description, stringArray[3]);

            if (stringArray.length > 4 && !stringArray[4].equals("null")) {
                task.snooze(stringArray[4]);
            }
        } else if (type.equals("E")) {
            String[] timeRange = stringArray[3].split("-");
            String from = timeRange[0];
            String to = timeRange[1];
            task = new Event(description, from, to);

            if (stringArray.length > 4 && !stringArray[4].equals("null")) {
                task.snooze(stringArray[4]);
            }
        } else {
            return null;
        }

        if (task != null && isDone) {
            task.markDone();
        }

        return task;

    }

    /**
     * Saves the list of tasks to the storage file.
     * Creates the parent directory if it doesn't exist. Overwrites the entire
     * file with the current task list using the pipe-delimited format.
     *
     * @param tasks ArrayList of Task objects to save to storage
     */
    public void save(ArrayList<Task> tasks) {
        assert tasks != null : "tasks cannot be null";

        try {
            File file = new File(filePath);
            file.getParentFile().mkdirs();
            FileWriter writer = new FileWriter(filePath);

            for (Task task : tasks) {
                writer.write(formatTask(task) + System.lineSeparator());
            }

            writer.close();
        } catch (IOException e) {
            System.out.println("Something went wrong: " + e.getMessage());
        }
    }

    /**
     * Formats a Task object into a pipe-delimited string for storage.
     * Converts task data into a standardized format that can be saved to file
     * and later parsed back into task objects. Handles different task types
     * and includes completion status and snooze information.
     *
     * @param task The Task object to format for storage
     * @return A pipe-delimited string representation of the task
     */
    public String formatTask(Task task) {
        assert task != null : "task cannot be null";

        String isDone = task.getDone().equals("[ ]") ? "0" : "1";
        String description = task.getContents();
        String snoozeInfo = task.getSnoozeUntil() != null ? task.getSnoozeUntil() : "null";

        if (task instanceof Todo) {
            return "T | " + isDone + " | " + description + " | " + snoozeInfo;
        } else if (task instanceof Deadline) {
            Deadline deadline = (Deadline) task;
            return "D | " + isDone + " | " + description + " | " + deadline.getDeadline() + " | " + snoozeInfo;
        } else if (task instanceof Event) {
            Event event = (Event) task;
            return "E | " + isDone + " | " + description + " | " + event.getFrom() + "-" + event.getTo() + " | " + snoozeInfo;
        } else {
            return " ";
        }
    }

}