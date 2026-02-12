package Cosmic;

import java.util.Scanner;
public class Cosmic {
    private static final int MARK_INDEX_START=5;
    private static final int UNMARK_INDEX_START=7;
    private static final int MAX_TASK=100;
    
    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);
        Task[] tasks = new Task[MAX_TASK];
        int taskCount = 0;

        printGreeting();

        while(true) {
            if (!scanner.hasNextLine()) {
                break;
            }
            String input = scanner.nextLine();

            try {
                if (handleExit(input)) {
                    break;
                }

                if (handleListTasks(input, taskCount, tasks)) {
                    continue;
                }

                if (handleMarkTask(input, tasks, taskCount)) {
                    continue;
                }

                if (handleUnmarkTask(input, tasks, taskCount)) {
                    continue;
                }
                int updatedCount = handleAddTaskTypes(input, tasks, taskCount);
                if (updatedCount != taskCount) {
                    taskCount = updatedCount;
                    continue;
                }
                throw new CosmicException("Sorry I don't understand that :(");
            }catch(CosmicException e){
                System.out.println(("OOPS!!! " + e.getMessage()));
            }
        }
        scanner.close();
    }

    private static void printGreeting() {
        System.out.println("Hello! I'm Cosmic.Cosmic");
        System.out.println("What can I do for you?");
    }
    private static int handleAddTaskTypes(String input, Task[] tasks, int taskCount) throws CosmicException {
        int newTaskCount = taskCount;
        if (input.startsWith("todo")) {
            String todoTaskName = input.replaceFirst("todo", "").trim();

            if (todoTaskName.isEmpty()) {
                throw new CosmicException("The description of a todo cannot be empty.");
            }

            System.out.println("Got it. I've added this task:");
            tasks[taskCount] = new Todo(todoTaskName);
            newTaskCount = printAddedTask(tasks, taskCount);

        } else if (input.startsWith("deadline")) {
            String content = input.replaceFirst("deadline","").trim();
            if (content.isEmpty()) {
                throw new CosmicException("The description of a deadline cannot be empty");
            }
            if(!content.contains("/by")){
                throw new CosmicException("The deadline must include /by date");
            }
            String[] deadlineParts = content.split("/by",2);
            String description=deadlineParts[0].trim();
            String by = (deadlineParts.length > 1) ? deadlineParts[1].trim() : "";
            if(description.isEmpty()){
                throw new CosmicException("The description of a deadline cannot be empty");
            }
            if(by.isEmpty()){
                throw new CosmicException("The date of a deadline cannot be empty");
            }
            System.out.println("Got it. I've added this task:");
            tasks[taskCount] = new Deadline(description, by);
            newTaskCount = printAddedTask(tasks, taskCount);

        } else if (input.startsWith("event")) {
            String content = getEventContent(input);
            if (content.isEmpty()) {
                throw new CosmicException("The description of a event cannot be empty");
            }
            if(!content.contains(("/from"))){
                throw new CosmicException("The event must contain /from");
            }
            if(!content.contains(("/to"))){
                throw new CosmicException("The event must contain /to");
            }
            String[] eventParts = content.split("/from|/to",3);
            String description=eventParts[0].trim();
            String from=(eventParts.length > 1) ? eventParts[1].trim() : "";
            String to=(eventParts.length > 2) ? eventParts[2].trim() : "";
            if(description.isEmpty()){
                throw new CosmicException("The description of the event cannot be empty");
            }
            if(from.isEmpty()){
                throw new CosmicException("The from date of the event cannot be empty");
            }
            if(to.isEmpty()){
                throw new CosmicException("The to date of the event cannot be empty");
            }
            System.out.println("Got it. I've added this task:");
            tasks[taskCount] = new Events(description, from, to);
            newTaskCount = printAddedTask(tasks, taskCount);
        }

        return newTaskCount;
    }

    private static String getEventContent(String input) {
        String content= input.replaceFirst("event","").trim();
        return content;
    }


    private static boolean handleUnmarkTask(String input, Task[] tasks, int taskCount) {
        if(input.startsWith("unmark ")){
            try {
                int index = Integer.parseInt(input.substring(UNMARK_INDEX_START)) - 1;
                if (index < 0 || index >= taskCount) {
                    System.out.println("Invalid task number.");
                    return true;
                }
                tasks[index].markAsNotDone();
                System.out.println("OK, I've marked this task as not done yet:");
                System.out.println(" " + tasks[index]);
            } catch (NumberFormatException e) {
                System.out.println("Please enter a valid task number.");
            }
            return true;
        }
        return false;
    }

    private static boolean handleMarkTask(String input, Task[] tasks, int taskCount) {
        if(input.startsWith("mark ")){
            try {
                int index = Integer.parseInt(input.substring(MARK_INDEX_START)) - 1;
                if (index < 0 || index >= taskCount) {
                    System.out.println("Invalid task number.");
                    return true;
                }
                tasks[index].markAsDone();
                System.out.println("Nice! I've marked this task as done:");
                System.out.println(" " + tasks[index]);
            } catch (NumberFormatException e) {
                System.out.println("Please enter a valid task number.");
            }
            return true;
        }
        return false;
    }

    private static boolean handleListTasks(String input, int taskCount, Task[] tasks) {
        if(input.equals("list")){
           System.out.println("Here are the tasks in your list:");
           for(int i = 0; i < taskCount; i++){
               System.out.println((i+1) + ". " + tasks[i]);
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

    private static int printAddedTask(Task[] tasks, int taskCount) {
        System.out.println(" " + tasks[taskCount]);
        taskCount++;
        System.out.println("Now you have " + taskCount + " tasks in the list.");
        return taskCount;
    }
}

