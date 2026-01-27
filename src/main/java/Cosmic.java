import java.util.Scanner;
public class Cosmic {
    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);
        String[] tasks = new String[100];
        int taskCount = 0;
        System.out.println("Hello! I'm Cosmic");
        System.out.println("What can I do for you?");
        while(true) {
            String input = scanner.nextLine();
            if (input.equals("bye")) {
                System.out.println("Bye. Hope to see you again soon!");
                break;
            }
           if(input.equals("list")){
               for(int i = 0; i < taskCount; i++){
                   System.out.println((i+1) + ". " + tasks[i]);
               }
               continue;
           }
           tasks[taskCount] = input;
           taskCount++;
           System.out.println("added: " +input);
       }
    }
}
