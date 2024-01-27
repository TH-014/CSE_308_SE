package Listener;

import util.*;

import java.io.IOException;
import java.util.List;
import java.util.Vector;

public class ReadThreadClient implements Runnable {
    private Thread thr;
    private SocketWrapper socketWrapper;
    private Subscriber subscriber;
    private Client client;

    public ReadThreadClient(SocketWrapper socketWrapper, Client cl, Subscriber subscriber) {
        this.socketWrapper = socketWrapper;
        this.subscriber = subscriber;
        this.client = cl;
        this.thr = new Thread(this);
        thr.start();
    }

//    boolean match(Vector<String> stockList)
//    {
//        for (String s : stockList) {
//            String[] tokens = s.split(" ");
//            if (!s.equals(client.stockList.get(tokens[0]))) {
//                client.stockList.put(tokens[0], s);
//                return false;
//            }
//        }
//        return true;
//    }
    public void run() {
//        System.out.println("In Read Thread Client");
        while (true)
        {
            Object o = null;
            try {
                o = socketWrapper.read();
            } catch (IOException | ClassNotFoundException e) {
                System.out.println(e);
            }
            if(o != null)
            {
                if (o instanceof Data) {
                    Data data = (Data) o;
                    Vector<String> notificationList = data.getNotificationList();
                    Vector<String> subscribedStocks = data.getSubscribedStocks();
                    //                    if(!match(stockList)) {
//                        client.isUpdated = true;
//                    }
                    client.stockList = data.getStockList();
//                    for(String stock: client.stockList)
//                    {
//                        System.out.println(stock);
//                    }
                    client.subscribedStocks = subscribedStocks;
                    if(!notificationList.isEmpty()) {
                        subscriber.update(notificationList);
                    }
                }
            }
        }
        //System.out.println("Read Thread Client Closed");
    }
}
