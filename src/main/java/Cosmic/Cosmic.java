package Cosmic;
import java.io.File;
import java.io.FileWriter;
import java.io.IOException;
import java.io.FileNotFoundException;
import java.util.ArrayList;
import java.util.Scanner;


public class Cosmic {
    private static final int MARK_INDEX_START = 5;
    private static final int UNMARK_INDEX_START = 7;
    private static final int MAX_TASK = 100;
    private static final String FILE_PATH = "./data/cosmic.txt";


    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);
        ArrayList<Task> tasks = new ArrayList<>();
        loadTasks(tasks);

        printGreeting();

        while (true) {
            if (!scanner.hasNextLine()) {
                break;
            }
            String input = scanner.nextLine();

            try {
                if (handleExit(input)) {
                    break;
                }

                if (handleListTasks(input, tasks)) {
                    continue;
                }

                if (handleMarkTask(input, tasks)) {
                    continue;
                }

                if (handleUnmarkTask(input, tasks)) {
                    continue;
                }
                if (handleAddTaskTypes(input, tasks)) {
                    continue;
                }
                throw new CosmicException("Sorry I don't understand that :(");
            } catch (CosmicException e) {
                System.out.println(("OOPS!!! " + e.getMessage()));
            }
        }
        scanner.close();
    }

    private static void printGreeting() {
        System.out.println("Hello! I'm Cosmic.Cosmic");
        System.out.println("What can I do for you?");
    }

    private static boolean handleAddTaskTypes(String input, ArrayList<Task> tasks) throws CosmicException {
        if (input.startsWith("todo")) {
            String todoTaskName = input.replaceFirst("todo", "").trim();

            if (todoTaskName.isEmpty()) {
                throw new CosmicException("The description of a todo cannot be empty.");
            }

            System.out.println("Got it. I've added this task:");
            tasks.add(new Todo(todoTaskName));
            printAddedTask(tasks);
            saveTasks(tasks);
            return true;

        } else if (input.startsWith("deadline")) {
            String content = input.replaceFirst("deadline", "").trim();
            if (content.isEmpty()) {
                throw new CosmicException("The description of a deadline cannot be empty");
            }
            if (!content.contains("/by")) {
                throw new CosmicException("The deadline must include /by date");
            }
            String[] deadlineParts = content.split("/by", 2);
            String description = deadlineParts[0].trim();
            String by = (deadlineParts.length > 1) ? deadlineParts[1].trim() : "";
            if (description.isEmpty()) {
                throw new CosmicException("The description of a deadline cannot be empty");
            }
            if (by.isEmpty()) {
                throw new CosmicException("The date of a deadline cannot be empty");
            }
            System.out.println("Got it. I've added this task:");
            tasks.add(new Deadline(description, by));
            printAddedTask(tasks);
            saveTasks(tasks);
            return true;
        } else if (input.startsWith("event")) {
            String content = getEventContent(input);
            if (content.isEmpty()) {
                throw new CosmicException("The description of a event cannot be empty");
            }
            if (!content.contains(("/from"))) {
                throw new CosmicException("The event must contain /from");
            }
            if (!content.contains(("/to"))) {
                throw new CosmicException("The event must contain /to");
            }
            String[] eventParts = content.split("/from|/to", 3);
            String description = eventParts[0].trim();
            String from = (eventParts.length > 1) ? eventParts[1].trim() : "";
            String to = (eventParts.length > 2) ? eventParts[2].trim() : "";
            if (description.isEmpty()) {
                throw new CosmicException("The description of the event cannot be empty");
            }
            if (from.isEmpty()) {
                throw new CosmicException("The from date of the event cannot be empty");
            }
            if (to.isEmpty()) {
                throw new CosmicException("The to date of the event cannot be empty");
            }
            System.out.println("Got it. I've added this task:");
            tasks.add(new Events(description, from, to));
            printAddedTask(tasks);
            saveTasks(tasks);
            return true;
        }

        return false;
    }

    private static String getEventContent(String input) {
        String content = input.replaceFirst("event", "").trim();
        return content;
    }


    private static boolean handleUnmarkTask(String input, ArrayList<Task> tasks) {
        if (input.startsWith("unmark ")) {
            try {
                int index = Integer.parseInt(input.substring(UNMARK_INDEX_START)) - 1;
                if (index < 0 || index >= tasks.size()) {
                    System.out.println("Invalid task number.");
                    return true;
                }
                tasks.get(index).markAsNotDone();
                saveTasks(tasks);
                System.out.println("OK, I've marked this task as not done yet:");
                System.out.println(" " + tasks.get(index));
            } catch (NumberFormatException e) {
                System.out.println("Please enter a valid task number.");
            }
            return true;
        }
        return false;
    }

    private static boolean handleMarkTask(String input, ArrayList<Task> tasks) {
        if (input.startsWith("mark ")) {
            try {
                int index = Integer.parseInt(input.substring(MARK_INDEX_START)) - 1;
                if (index < 0 || index >= tasks.size()) {
                    System.out.println("Invalid task number.");
                    return true;
                }
                tasks.get(index).markAsDone();
                saveTasks(tasks);
                System.out.println("Nice! I've marked this task as done:");
                System.out.println(" " + tasks.get(index));
            } catch (NumberFormatException e) {
                System.out.println("Please enter a valid task number.");
            }
            return true;
        }
        return false;
    }

    private static boolean handleListTasks(String input, ArrayList<Task> tasks) {
        if (input.equals("list")) {
            System.out.println("Here are the tasks in your list:");
            for (int i = 0; i < tasks.size(); i++) {
                System.out.println((i + 1) + ". " + tasks.get(i));
            }
            return true;
        }
        return false;
    }

    private static boolean handleExit(String input) {
        if (input.equals("bye")) {
            System.out.println("Bye. Hope to see you again soon!");
            return true;
        }
        return false;
    }

    private static void printAddedTask(ArrayList<Task> tasks) {
        System.out.println(" " + tasks.get(tasks.size() - 1));
        System.out.println("Now you have " + tasks.size() + " tasks in the list.");
    }


    private static void saveTasks(ArrayList<Task> tasks) {
        try {
            File file = new File(FILE_PATH);
            file.getParentFile().mkdirs();

            FileWriter writer = new FileWriter(file);

            for (Task t : tasks) {
                writer.write(t.toFileString() + "\n");
            }

            writer.close();
        } catch (IOException e) {
            System.out.println("Error saving tasks.");
        }
    }

    private static void loadTasks(ArrayList<Task> tasks) {
        File file = new File(FILE_PATH);

        if (!file.exists()) {
            return;
        }

        try {
            Scanner fileScanner = new Scanner(file);

            while (fileScanner.hasNextLine()) {
                String line = fileScanner.nextLine();
                Task task = parseTask(line);
                if (task != null) {
                    tasks.add(task);
                }
            }

            fileScanner.close();
        } catch (FileNotFoundException e) {
            System.out.println("Error loading tasks.");
        }
    }

    private static Task parseTask(String line) {
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

            default:
                return null;
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


