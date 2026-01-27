import java.util.Scanner;
public class Cosmic {
    public static void main(String[] args) {
        Scanner scanner=new Scanner(System.in);
        System.out.println("Hello! I'm Cosmic");
        System.out.println("What can I do for you?");
        while(true) {
            String input = scanner.nextLine();
            if (input.equals("bye")) {
                System.out.println("Bye. Hope to see you again soon!");
                break;
            }
            System.out.println(input);
       }
    }
}
