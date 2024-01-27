package Listener;

import util.SocketWrapper;

import java.io.IOException;
import java.util.HashMap;
import java.util.Scanner;
import java.util.Vector;

public class Client implements Runnable{
    private final String serverIp;
    private final int serverPort;
    private SocketWrapper socketWrapper;
    private Subscriber subscriber;
    Vector<String> subscribedStocks;
    Vector<String> subscribed;
    Vector<String> stockList;
    boolean isUpdated = false;

    public Client(String serverIp, int serverPort, Subscriber subscriber) throws IOException {
        this.serverIp = serverIp;
        this.serverPort = serverPort;
        this.subscriber = subscriber;
        subscribedStocks = new Vector<>();
        stockList = new Vector<>();
        subscribed = new Vector<>();
        connectToServer(serverIp, serverPort);
        socketWrapper.write(subscriber.getName());
        Thread cmdThread = new Thread (this);
        cmdThread.start();
    }

    void printStockList()
    {
        isUpdated = false;
        System.out.println("Stock List:");
        for(String stock: stockList)
        {
            System.out.println(stock);
        }
        System.out.println("------------------------------------------------------------------------------");
    }

    void printSubscribedStocks()
    {
        System.out.println("Subscribed Stocks: "+ subscribed.size());
        for(String stock: stockList)
        {
            String [] tokens = stock.split(" ");
            if(subscribed.contains(tokens[0]))
                System.out.println(stock);
        }
    }

    public void run() {
        try {
            Thread.sleep(1000);
        } catch (InterruptedException e) {
            System.out.println(e);;
        }
        printStockList();
        Scanner scanner = new Scanner(System.in);
        while(true)
        {
            System.out.println("Enter command: ");
            String message = scanner.nextLine();
            if(message.equalsIgnoreCase("exit"))
            {
                System.exit(0);
            }
            else if(message.equalsIgnoreCase("v"))
            {
                printSubscribedStocks();
            }
            else
            {
                try {
                    String [] tokens = message.split(" ");
                    if(tokens[0].equalsIgnoreCase("s"))
                    {
                        if(!subscribed.contains(tokens[1]))
                        {
                            subscribed.add(tokens[1]);
                            socketWrapper.write(message);
                        }
                    }
                    else if(tokens[0].equalsIgnoreCase("u"))
                    {
                        if(subscribed.contains(tokens[1]))
                        {
                            subscribed.remove(tokens[1]);
                            socketWrapper.write(message);
                        }
                    }
                    socketWrapper.write(message);
                } catch (IOException e) {
                    System.out.println(e);
                }
            }
        }
    }
    public SocketWrapper getSocketWrapper() {
        return socketWrapper;
    }

    public void connectToServer(String serverIp, int serverPort) {
        try{
            socketWrapper = new SocketWrapper(serverIp, serverPort);
            new ReadThreadClient(socketWrapper, this, subscriber);
        } catch (Exception e) {
            System.out.println(e);
        }
    }

    public static void main(String[] args) throws IOException {
        String serverAddress = "127.0.0.1";
        int serverPort = 33333;
        System.out.print("Enter name of the client: ");
        Scanner scanner = new Scanner(System.in);
        String clientName = scanner.nextLine();
        Subscriber subscriber = new Subscriber(clientName);
        new Client(serverAddress, serverPort, subscriber);
    }
}
