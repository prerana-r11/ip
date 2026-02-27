package Cosmic;
import java.util.ArrayList;
/**
 * Represents a list of tasks.
 * Provides operations to add, delete, modify, retrieve,
 * and search tasks stored in the list.
 */
public class TaskList {
    private final ArrayList<Task> tasks;

    /**
     * Constructs an empty TaskList.
     */
    public TaskList(){

        this.tasks= new ArrayList<>();
    }

    /**
     * Constructs a TaskList using an existing list of tasks.
     *
     * @param tasks The initial list of tasks to store.
     */
    public TaskList(ArrayList<Task> tasks) {
        this.tasks = tasks;
    }

    /**
     * Adds a task to the task list.
     *
     * @param task The task to be added.
     */
    public void add(Task task){

        tasks.add(task);
    }

    /**
     * Deletes the task at the specified index.
     *
     * @param index The index of the task to delete.
     * @return The deleted task.
     * @throws CosmicException If the index is invalid.
     */
    public Task delete(int index) throws CosmicException{
        if(index<0 || index>tasks.size()){
            throw new CosmicException("Invalid task number!");
        }
        return tasks.remove(index);
    }

    /**
     * Marks the task at the specified index as completed.
     *
     * @param index The index of the task to mark.
     * @throws CosmicException If the index is invalid.
     */
    public void mark(int index) throws CosmicException{
        if(index<0 || index>tasks.size()){
            throw new CosmicException("Invalid task number!");
        }
        tasks.get(index).markAsDone();
    }

    /**
     * Marks the task at the specified index as not completed.
     *
     * @param index The index of the task to unmark.
     * @throws CosmicException If the index is invalid.
     */
    public void unmark(int index) throws CosmicException{
        if(index<0 || index>=tasks.size()){
            throw new CosmicException("Invalid task number!");
        }
        tasks.get(index).markAsNotDone();
    }

    /**
     * Returns the total number of tasks in the list.
     *
     * @return The number of tasks.
     */
    public int size(){
        return tasks.size();
    }

    /**
     * Retrieves the task at the specified index.
     *
     * @param index The index of the task.
     * @return The task at the given index.
     */
    public Task get(int index){

        return tasks.get(index);
    }

    /**
     * Returns the full list of tasks.
     *
     * @return The list containing all tasks.
     */
    public ArrayList<Task> getAll(){
        return tasks;
    }

    /**
     * Returns a new TaskList containing tasks whose descriptions
     * contain the specified keyword.
     *
     * @return A TaskList containing matching tasks.
     */
    public TaskList find(String find){
        ArrayList<Task> filteredList = new ArrayList<>();
        for(Task t:tasks){
            if(t.getDescription().contains(find)){
                filteredList.add(t);
            }
        }
        return new TaskList(filteredList);

    }
}
