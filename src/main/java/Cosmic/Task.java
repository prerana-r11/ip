package Cosmic;

public class Task {
    protected final String description;
    protected boolean isDone;
    /**
     * Creates a task with the given description.
     *
     * @param description Description of the task.
     */
    public Task(String description) {
        this.description= description;
        this.isDone=false;
    }

    /**
     * Marks this task as done.
     */
    public void markAsDone() {

        isDone=true;
    }

    /**
     * Marks this task as not done.
     */
    public void markAsNotDone() {

        isDone=false;
    }

    /**
     * Returns the status icon of the task.
     *
     * @return "X" if done, otherwise a space.
     */
    public String getStatusIcon() {
        return isDone ? "X" : " ";
    }

    /**
     * Returns the string representation of this task.
     *
     * @return Cosmic.Task formatted as a string.
     */
    @Override
    public String toString() {

        return "[" +getStatusIcon() +"]" +description;
    }

    public String toFileString() {
        return "T | " + (isDone ? "1" : "0") + " | " + description;
    }

}
