import java.io.File;
import java.io.FileWriter;
import java.io.IOException;
import java.util.ArrayList;
import java.util.Scanner;

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
        String[] stringArray = line.split(" \\| ");

        if (stringArray.length < 3) {
            return null;
        }

        String type = stringArray[0];
        Boolean isDone = stringArray[1].equals("1");
        String description = stringArray[2];

        Task task;

        if (type.equals("T")) {
            task = new Todo(description);
        } else if (type.equals("D")) {
            task = new Deadline(description, stringArray[3]);
        } else if (type.equals("E")) {
            String[] timeRange = stringArray[3].split("-");
            String from = timeRange[0];
            String to = timeRange[1];
            task = new Event(description, from, to);
        } else {
            return null;
        }

        if (task != null && isDone) {
            task.markDone();
        }

        return task;

    }

    public void save(ArrayList<Task> tasks) {
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
        String isDone = task.getDone().equals("[ ]") ? "0" : "1";
        String description = task.getContents();

        if (task instanceof Todo) {
            return "T | " + isDone + " | " + description;
        } else if (task instanceof Deadline) {
            Deadline deadline = (Deadline) task;
            return "D | " + isDone + " | " + description + " | " + deadline.getDeadline();
        } else if (task instanceof Event) {
            Event event = (Event) task;
            return "E | " + isDone + " | " + description + " | " + event.getFrom() + "-" + event.getTo();
        } else {
            return " ";
        }
    }

}