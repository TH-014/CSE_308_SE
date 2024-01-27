package Listener;

public class Subscriber implements SubscriberInterface{
    boolean hasNotification = false;
    @Override
    public void update(String message) {
        System.out.println(message);
    }
}