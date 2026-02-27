package Cosmic;

import java.io.*;
import java.util.Scanner;

/**
 * Handles loading and saving of tasks to a file.
 * Responsible for storage of task data.
 */
public class Storage {
    private String filePath;

    /**
     * Constructs a Storage object with the given file path.
     *
     * @param filePath The file path where tasks are stored.
     */
    public Storage(String filePath) {
        this.filePath = filePath;
    }

    /**
     * Saves the given TaskList to the file.
     * Each task is written in its file storage format.
     *
     * @param taskList The list of tasks to be saved.
     */
    public void save(TaskList taskList) {
        try {
            File file = new File(filePath);
            file.getParentFile().mkdirs();
            FileWriter writer = new FileWriter(file);

            for (Task t : taskList.getAll()) {
                writer.write(t.toFileString() + "\n");
            }

            writer.close();
        } catch (IOException e) {
            System.out.println("Error saving tasks.");
        }
    }

    /**
     * Loads tasks from the file and returns them as a TaskList.
     * If the file does not exist, returns an empty TaskList.
     *
     * @return A TaskList containing tasks loaded from file.
     */
    public TaskList load() {
        TaskList taskList = new TaskList();
        File file = new File(filePath);

        if (!file.exists()) {
            return taskList;
        }

        try {
            Scanner fileScanner = new Scanner(file);

            while (fileScanner.hasNextLine()) {
                String line = fileScanner.nextLine();
                Task task = parseTask(line);
                if (task != null) {
                    taskList.add(task);
                }
            }

            fileScanner.close();
        } catch (Exception e) {
            System.out.println("Error loading tasks.");
        }

        return taskList;
    }

    /**
     * Parses a line from the file and converts it into a Task object.
     *
     * @param line A single line from the storage file.
     * @return A Task object if parsing is successful, null otherwise.
     */
    private Task parseTask(String line) {
        try {
            String[] parts = line.split(" \\| ");
            String type = parts[0];
            boolean isDone = parts[1].equals("1");

            Task task = null;

            switch (type) {
            case "T":
                task = new Todo(parts[2]);
                break;
            case "D":
                task = new Deadline(parts[2], parts[3]);
                break;
            case "E":
                task = new Events(parts[2], parts[3], parts[4]);
                break;
            }

            if (task != null && isDone) {
                task.markAsDone();
            }

            return task;

        } catch (Exception e) {
            return null;
        }
    }
}