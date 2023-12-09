package Exceptions;

public class InvalidAmountException extends CustomException{
    public InvalidAmountException(String message) {
        super("Invalid Amount: "+message);
    }
}
