package Cosmic;

public class CosmicException extends Exception{
    private String message;

    public CosmicException(String message){
        super(message);
    }
}
