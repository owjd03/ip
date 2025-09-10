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

    public Storage(String filePath) {
        this.filePath = filePath;
    }

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