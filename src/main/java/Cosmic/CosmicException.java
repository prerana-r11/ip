package Cosmic;

/**
 * Represents a custom exception
 * Thrown when user input or task operations are invalid.
 */
public class CosmicException extends Exception{
    private String message;

    /**
     * Constructs a CosmicException with the specified error message.
     *
     * @param message The error message describing the exception.
     */
    public CosmicException(String message){
        super(message);
    }
}
