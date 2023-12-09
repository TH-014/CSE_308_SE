package Exceptions;

public class UnauthorizedAccessException extends CustomException{
    public UnauthorizedAccessException(String message) {
        super("Unauthorized Operation: "+message);
    }
}
