import java.util.Scanner;
public class Cosmic {
    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);
        Task[] tasks = new Task[100];
        int taskCount = 0;
        System.out.println("Hello! I'm Cosmic");
        System.out.println("What can I do for you?");
        while(true) {
            if (!scanner.hasNextLine()) {
                break;
            }
            String input = scanner.nextLine();
            if (input.equals("bye")) {
                System.out.println("Bye. Hope to see you again soon!");
                break;
            }
           if(input.equals("list")){
               System.out.println("Here are the tasks in your list:");
               for(int i = 0; i < taskCount; i++){
                   System.out.println((i+1) + ". " + tasks[i]);
               }
               continue;
           }
           if(input.startsWith("mark")){
               int index= Integer.parseInt(input.substring(5))-1;
               tasks[index].markAsDone();
               System.out.println("Nice! I've marked this task as done:");
               System.out.println(" " + tasks[index]);
               continue;
           }
           if(input.startsWith("unmark")){
               int index= Integer.parseInt(input.substring(7))-1;
               tasks[index].markAsNotDone();
               System.out.println("OK, I've marked this task as not done yet:");
               System.out.println(" " + tasks[index]);
               continue;
           }
           if(input.startsWith("todo ")){
               System.out.println("Got it. I've added this task:");
               String todoTaskName= input.substring(5);
               tasks[taskCount]= new Todo(todoTaskName);
               System.out.println(" " + tasks[taskCount]);
               taskCount++;
               System.out.println("Now you have " +taskCount+ " tasks in the list.");
               continue;

           }
           if(input.startsWith("deadline ")){
               System.out.println("Got it. I've added this task:");
               String[] deadlineParts= input.substring(9).split(" /by");
               tasks[taskCount]= new Deadline(deadlineParts[0],deadlineParts[1]);
               System.out.println(" " + tasks[taskCount]);
               taskCount++;
               System.out.println("Now you have " +taskCount+ " tasks in the list.");
               continue;

           }
           if(input.startsWith("event ")){
               System.out.println("Got it. I've added this task:");
               String[] eventParts= input.substring(6).split(" /from | /to");
               tasks[taskCount]= new Events(eventParts[0],eventParts[1],eventParts[2]);
               System.out.println(" " + tasks[taskCount]);
               taskCount++;
               System.out.println("Now you have " +taskCount+ " tasks in the list.");
               continue;

           }
           tasks[taskCount] = new Task(input);
           taskCount++;
           System.out.println("added: " +input);
       }
    }
}
