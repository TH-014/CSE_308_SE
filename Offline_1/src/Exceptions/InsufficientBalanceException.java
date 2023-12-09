package Exceptions;

public class InsufficientBalanceException extends CustomException{
    public InsufficientBalanceException(String message) {
        super("Insufficient Balance: "+message);
    }
}
