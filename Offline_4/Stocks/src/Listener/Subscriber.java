package Listener;

import java.util.Vector;

public class Subscriber implements SubscriberInterface{
    private String name;
    @Override
    public void update(Vector<String> message) {
        System.out.println("You have new notification(s)!");
        for(String s: message)
            System.out.println("\t"+s);
    }

    public Subscriber(String name) {
        this.name = name;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }
}