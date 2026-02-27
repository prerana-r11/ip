package Cosmic;
import java.util.ArrayList;

public class TaskList {
    private final ArrayList<Task> tasks;

    public TaskList(){

        this.tasks= new ArrayList<>();
    }

    public TaskList(ArrayList<Task> tasks) {
        this.tasks = tasks;
    }

    public void add(Task task){

        tasks.add(task);
    }

    public Task delete(int index) throws CosmicException{
        if(index<0 || index>tasks.size()){
            throw new CosmicException("Invalid task number!");
        }
        return tasks.remove(index);
    }

    public void mark(int index) throws CosmicException{
        if(index<0 || index>tasks.size()){
            throw new CosmicException("Invalid task number!");
        }
        tasks.get(index).markAsDone();
    }

    public void unmark(int index) throws CosmicException{
        if(index<0 || index>tasks.size()){
            throw new CosmicException("Invalid task number!");
        }
        tasks.get(index).markAsNotDone();
    }

    public int size(){
        return tasks.size();
    }

    public Task get(int index){

        return tasks.get(index);
    }

    public ArrayList<Task> getAll(){
        return tasks;
    }

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
