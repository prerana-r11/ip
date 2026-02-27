package Cosmic;

public class UI {

    public void printGreeting(){
        System.out.println("Hello! I'm Cosmic.Cosmic");
        System.out.println("What can I do for you?");
    }

    public void printAddedTask(Task task,int total){
        System.out.println("Got it. I've added this task:");
        System.out.println(" " + task);
        System.out.println("Now you have " + total + " tasks in the list.");
    }

    public void printDeletedTask(Task task, int total){
        System.out.println("Noted, I've removed this task");
        System.out.println(" " + task);
        System.out.println(("Now you have " + total + " tasks in your list"));
    }

    public void printList(TaskList tasks){
        System.out.println("Here are the tasks in your list:");
        for(int i=0;i< tasks.size();i++){
            System.out.println((i+1) + ". " +tasks.get(i));
        }
    }

    public void printMarked(Task task) {
        System.out.println("Nice! I've marked this task as done:");
        System.out.println(" " + task);
    }

    public void printUnmarked(Task task) {
        System.out.println("OK, I've marked this task as not done yet:");
        System.out.println(" " + task);
    }


    public void printError(String message) {

        System.out.println("OOPS!!! " + message);
    }

    public void printExit(){

        System.out.println("Bye. Hope to see you again soon!");
    }
}
