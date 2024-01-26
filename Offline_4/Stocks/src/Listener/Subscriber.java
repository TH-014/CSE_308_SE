package Listener;

public class Subscriber implements SubscriberInterface{
    @Override
    public void update(String message) {
        System.out.println(message);
    }
}