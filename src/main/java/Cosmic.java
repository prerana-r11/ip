import java.util.Scanner;
public class Cosmic {
    private static final int MARK_INDEX_START=5;
    private static final int UNMARK_INDEX_START=7;
    private static final int TODO_INDEX_START=5;
    private static final int DEADLINE_INDEX_START=9;
    private static final int EVENT_INDEX_START=6;
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

            if (handleExit(input)){
                break;
            }

            if (handleListTasks(input, taskCount, tasks)){
                continue;
            }

            if (handleMarkTask(input, tasks,taskCount)){
                continue;
            }

            if (handleUnmarkTask(input, tasks,taskCount)){
                continue;
            }
            int updatedCount = handleAddTaskTypes(input, tasks, taskCount);
            if (updatedCount != taskCount) {
                taskCount = updatedCount;
                continue;
            }

            printUnknownCommand();
        }
        scanner.close();
    }

    private static void printGreeting() {
        System.out.println("Hello! I'm Cosmic");
        System.out.println("What can I do for you?");
    }
    private static void printUnknownCommand() {
        System.out.println("Sorry, I don't understand that");
    }

    private static int handleAddTaskTypes(String input, Task[] tasks, int taskCount) {
        int newTaskCount = taskCount;

        if (input.startsWith("todo ")) {
            System.out.println("Got it. I've added this task:");
            String todoTaskName = input.substring(TODO_INDEX_START);
            tasks[taskCount] = new Todo(todoTaskName);
            newTaskCount = printAddedTask(tasks, taskCount);

        } else if (input.startsWith("deadline ")) {
            System.out.println("Got it. I've added this task:");
            String[] deadlineParts = input.substring(DEADLINE_INDEX_START).split(" /by");
            tasks[taskCount] = new Deadline(deadlineParts[0], deadlineParts[1]);
            newTaskCount = printAddedTask(tasks, taskCount);

        } else if (input.startsWith("event ")) {
            System.out.println("Got it. I've added this task:");
            String[] eventParts = input.substring(EVENT_INDEX_START).split(" /from | /to");
            tasks[taskCount] = new Events(eventParts[0], eventParts[1], eventParts[2]);
            newTaskCount = printAddedTask(tasks, taskCount);
        }

        return newTaskCount;
    }


    private static boolean handleUnmarkTask(String input, Task[] tasks,int taskCount) {
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

    private static boolean handleMarkTask(String input, Task[] tasks,int taskCount) {
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

