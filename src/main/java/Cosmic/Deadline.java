package Cosmic;

/**
 * A deadline task consists of a description and due date
 */
public class Deadline extends Task {
    protected String by;

    /**
     * Creates a Cosmic.Deadline task with the given description and due date.
     *
     * @param description The task description.
     * @param by The deadline of the task.
     */
    public Deadline(String description,String by){
        super(description);
        this.by=by;
    }

    /**
     * Returns the string  of the deadline task.
     *
     * @return Formatted deadline task string.
     */
    @Override
    public String toString(){
        return "[D]" + super.toString() + "(by:" + by + ")";

    }

    @Override
    public String toFileString() {
        return "D | " + (isDone ? "1" : "0") + " | " + description + " | " + by;
    }

}

