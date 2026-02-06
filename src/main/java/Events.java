/**
 * An event task consists of a description, a start and end time.
 */
public class Events extends Task{
    protected String from;
    protected String to;

    /**
     * Creates an event task with the given description, start time, and end time.
     *
     * @param description The description of the event.
     * @param from The start time of the event.
     * @param to The end time of the event.
     */
    public Events(String description, String from, String to){
        super(description);
        this.from=from;
        this.to=to;
    }

    /**
     * Returns the string of the event task.
     *
     * @return Formatted event task string.
     */
    @Override
    public String toString(){
        return "[E]" + super.toString() + "(from: " +from
                + " to: " + to +")";
    }

}
