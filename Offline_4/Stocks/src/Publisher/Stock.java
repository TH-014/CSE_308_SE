package Publisher;

import java.util.Vector;

public class Stock {
    private String name;
    private int count;
    private double price;
    private final Vector<String> subscriberList;

    public Vector<String> getSubscriberList() {
        return subscriberList;
    }

    public Stock(String name, int count, double price) {
        this.name = name;
        this.count = count;
        this.price = price;
        subscriberList = new Vector<>();
    }

    public Stock(String line) {
        String[] tokens = line.split(" ");
        this.name = tokens[0];
        this.count = Integer.parseInt(tokens[1]);
        this.price = Double.parseDouble(tokens[2]);
        subscriberList = new Vector<>();
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public int getCount() {
        return count;
    }

    public void setCount(int count) {
        this.count = count;
    }

    public double getPrice() {
        return price;
    }

    public void setPrice(double price) {
        this.price = price;
    }

    public void subscribe(String subscriberName)
    {
        if(!subscriberList.contains(subscriberName))
            subscriberList.add(subscriberName);
    }

    public void unsubscribe(String subscriberName)
    {
        subscriberList.remove(subscriberName);
    }

    public boolean isSubscribed(String subscriberName)
    {
        return subscriberList.contains(subscriberName);
    }
}
