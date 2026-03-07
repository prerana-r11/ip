package ui;

import task.Task;
import task.TaskList;


/**
 * Handles all user interface interactions.
 * Responsible for printing messages and responses to the user.
 */
public class UI {
    private static final String INDENT = "    ";

    public void printGreeting(){
        System.out.println(INDENT + "Hello! I'm Cosmic");
        System.out.println(INDENT + "What can I do for you?");
    }

    /**
     * Prints confirmation after a task is added.
     *
     * @param task The task that was added.
     * @param total The updated total number of tasks.
     */
    public void printAddedTask(Task task, int total){
        System.out.println(INDENT + "Got it. I've added this task:");
        System.out.println(INDENT + " " + task);
        System.out.println(INDENT + "Now you have " + total + " tasks in the list.");
    }

    /**
     * Prints confirmation after a task is deleted.
     *
     * @param task The task that was deleted.
     * @param total The updated total number of tasks.
     */
    public void printDeletedTask(Task task, int total){
        System.out.println(INDENT + "Noted, I've removed this task");
        System.out.println(INDENT + " " + task);
        System.out.println((INDENT + "Now you have " + total + " tasks in your list"));
    }


    /**
     * Prints all tasks in the given TaskList.
     *
     * @param tasks The TaskList to be displayed.
     */
    public void printList(TaskList tasks){
        System.out.println(INDENT + "Here are the tasks in your list:");
        for(int i=0;i< tasks.size();i++){
            System.out.println(INDENT + (i+1) + ". " +tasks.get(i));
        }
    }

    /**
     * Prints confirmation when a task is marked as  completed.
     *
     * @param task The task that was marked.
     */
    public void printMarked(Task task) {
        System.out.println(INDENT + "Nice! I've marked this task as done:");
        System.out.println(INDENT + " " + task);
    }

    /**
     * Prints confirmation when a task is marked as not completed.
     *
     * @param task The task that was unmarked.
     */
    public void printUnmarked(Task task) {
        System.out.println(INDENT + "OK, I've marked this task as not done yet:");
        System.out.println(INDENT + " " + task);
    }


    /**
     * Prints an error message to the user.
     *
     * @param message The error message to display.
     */
    public void printError(String message) {

        System.out.println(INDENT + "OOPS!!! " + message);
    }

    /**
     * Prints the exit message when the application terminates.
     */
    public void printExit(){

        System.out.println(INDENT + "Bye. Hope to see you again soon!");
    }
}
