package Cosmic;

/**
 * A Cosmic.Todo task consists of a description of the task
 */
public class Todo extends Task {

    /**
     * Creates a Cosmic.Todo task with the given description.
     *
     * @param description The description of the todo task.
     */
    public Todo(String description){
        super(description);
    }

    /**
     * Returns the string  of the todo task.
     *
     * @return Formatted todo task string.
     */
    @Override
    public String toString(){
        return "[T]" + super.toString();
    }

}
