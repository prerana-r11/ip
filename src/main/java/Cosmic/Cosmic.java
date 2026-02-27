package Cosmic;

import java.util.Scanner;

public class Cosmic {

    private static final String FILE_PATH = "./data/cosmic.txt";

    public static void main(String[] args) {
        Storage storage = new Storage(FILE_PATH);
        TaskList tasks = storage.load();
        UI ui = new UI();

        ui.printGreeting();
        Scanner scanner = new Scanner(System.in);

        while (scanner.hasNextLine()) {
            String input = scanner.nextLine();

            try {
                if (input.equals("list")) {
                    ui.printList(tasks);
                    continue;
                }

                if (input.startsWith("delete ")) {
                    int index = Integer.parseInt(input.substring(7)) - 1;
                    Task deleted = tasks.delete(index);
                    storage.save(tasks);
                    ui.printDeletedTask(deleted, tasks.size());
                }

                if (input.startsWith("mark ")) {
                    int index = Integer.parseInt(input.substring(5)) - 1;
                    tasks.mark(index);
                    storage.save(tasks);
                    ui.printMarked(tasks.get(index));
                }

                if (input.startsWith("unmark ")) {
                    int index = Integer.parseInt(input.substring(7)) - 1;
                    tasks.unmark(index);
                    storage.save(tasks);
                    ui.printUnmarked(tasks.get(index));
                }

                if (input.startsWith("todo ")) {
                    String description = getFindContent(input);
                    if (description.isEmpty()) {
                        throw new CosmicException("The description of a todo cannot be empty.");
                    }

                    Task task = new Todo(description);
                    tasks.add(task);
                    storage.save(tasks);
                    ui.printAddedTask(task, tasks.size());
                    continue;
                }


                if (input.startsWith("deadline ")) {
                    String content = getDeadlineContent(input);

                    String[] parts = content.split("/by", 2);
                    String description = parts[0].trim();
                    String by = parts[1].trim();

                    if (description.isEmpty() || by.isEmpty()) {
                        throw new CosmicException("Description and date cannot be empty.");
                    }

                    Task task = new Deadline(description, by);
                    tasks.add(task);
                    storage.save(tasks);
                    ui.printAddedTask(task, tasks.size());
                    continue;
                }


                if (input.startsWith("event ")) {
                    String content = getEventContent(input);

                    String[] parts = content.split("/from|/to", 3);
                    String description = parts[0].trim();
                    String from = parts[1].trim();
                    String to = parts[2].trim();

                    if (description.isEmpty() || from.isEmpty() || to.isEmpty()) {
                        throw new CosmicException("Description, from, and to cannot be empty.");
                    }

                    Task task = new Events(description, from, to);
                    tasks.add(task);
                    storage.save(tasks);
                    ui.printAddedTask(task, tasks.size());
                    continue;
                }

                if (input.equals("find")) {
                    throw new CosmicException("The description of a find command cannot be empty.");
                }
                if(input.startsWith("find ")){
                    String content = getContentAfterPrefix(input, "find ");
                    if (content.isEmpty()) {
                        throw new CosmicException("The description of a find command cannot be empty.");
                    }
                    TaskList found = tasks.find(content);
                    ui.printList(found);
                    continue;
                }
                
                if (input.equals("bye")) {
                    ui.printExit();
                    break;
                }

                throw new CosmicException("Sorry I don't understand that :(");

            } catch (CosmicException e) {
                ui.printError(e.getMessage());
            } catch (NumberFormatException e) {
                ui.printError("Please enter a valid task number.");
            }
        }

        scanner.close();
    }

    private static String getFindContent(String input) {
        return input.substring(5).trim();
    }

    private static String getEventContent(String input) throws CosmicException {
        String content = input.substring(6).trim();

        if (!content.contains("/from") || !content.contains("/to")) {
            throw new CosmicException("Event must contain /from and /to.");
        }
        return content;
    }

    private static String getDeadlineContent(String input) throws CosmicException {
        String content = input.substring(9).trim();

        if (!content.contains("/by")) {
            throw new CosmicException("Deadline must contain /by.");
        }
        return content;
    }

    private static String getContentAfterPrefix(String input, String prefix) {
        return input.substring(prefix.length()).trim();
    }
}
