package Exceptions;

public class DuplicateUserException extends CustomException{
    public DuplicateUserException(String message) {
        super("Duplicate User: "+message);
    }
}
