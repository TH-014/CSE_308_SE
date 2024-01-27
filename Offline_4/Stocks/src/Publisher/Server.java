package Publisher;

import util.SocketWrapper;
import java.io.*;
import java.net.ServerSocket;
import java.net.Socket;
import java.util.HashMap;
import java.util.Scanner;
import java.util.Vector;

public class Server implements Runnable{
    final HashMap<String, Stock> stockList;
    private ServerSocket serverSocket;
    private HashMap<String, SocketWrapper> clientMap;
    final HashMap<String, Vector<String>> pendingNotifications;

    private static final String INPUT_FILE_NAME = "init_stocks.txt";
    private static final String SAVED_NOTIFICATIONS = "pending_notifications.txt";
    private static final String SAVED_SUBSCRIBER_LIST = "subscribers.txt";

    Server() throws IOException {
        stockList = new HashMap<>();
        clientMap = new HashMap<>();
        pendingNotifications = new HashMap<>();
        readFiles();
        Thread cmdThread = new Thread(this);
        cmdThread.start();
        try {
            serverSocket = new ServerSocket(33333);
            while (true) {
                Socket clientSocket = serverSocket.accept();
                serve(clientSocket);
            }
        } catch (Exception e) {
            System.out.println("Server starts:" + e);
        }
    }

    private void serve(Socket clientSocket) throws Exception{
        SocketWrapper socketWrapper = new SocketWrapper(clientSocket);
        new ReadThreadServer(this, socketWrapper);
    }

    public void subscribe(String clientName, String stockName)
    {
        if(!stockList.containsKey(stockName))
            return;
        stockList.get(stockName).addSubscriber(clientName);
    }

    public void unsubscribe(String clientName, String stockName)
    {
        if(!stockList.containsKey(stockName))
            return;
        stockList.get(stockName).removeSubscriber(clientName);
    }

    public Vector<String> getSubscribedStocks(String clientName)
    {
        Vector<String> subscribedStocks = new Vector<>();
        for(var stock: stockList.values())
        {
            if(stock.isSubscribed(clientName))
                subscribedStocks.add(stock.getName());
        }
        return subscribedStocks;
    }
    @Override
    public void run() {
        while (true) {
            Scanner scn = new Scanner(System.in);
            String command = scn.nextLine();
            if(command.equalsIgnoreCase("exit"))
            {
                try {
                    writeFiles();
                } catch (IOException e) {
                    throw new RuntimeException(e);
                }
                System.exit(0);
            }
            else
            {
                String[] tokens = command.split(" ");
                if(tokens[0].equalsIgnoreCase("i"))
                {
                    increasePrice(tokens[1], Double.parseDouble(tokens[2]));
                }
                else if(tokens[0].equalsIgnoreCase("d"))
                {
                    decreasePrice(tokens[1], Double.parseDouble(tokens[2]));
                }
                else if(tokens[0].equalsIgnoreCase("c"))
                {
                    changeCount(tokens[1], Integer.parseInt(tokens[2]));
                }
            }
        }
    }

    public void increasePrice(String stockName, double price)
    {
        if(!stockList.containsKey(stockName))
            return;
        stockList.get(stockName).setPrice(stockList.get(stockName).getPrice()+price);
        var list = stockList.get(stockName).getSubscriberList();
        for(var client: list)
        {
            if(!pendingNotifications.containsKey(client))
                pendingNotifications.put(client, new Vector<>());
            pendingNotifications.get(client).add(stockName+" price increased by "+price);
        }
    }

    public void decreasePrice(String stockName, double price)
    {
        if(!stockList.containsKey(stockName))
            return;
        stockList.get(stockName).setPrice(stockList.get(stockName).getPrice()-price);
        var list = stockList.get(stockName).getSubscriberList();
        for(var client: list)
        {
            if(!pendingNotifications.containsKey(client))
                pendingNotifications.put(client, new Vector<>());
            pendingNotifications.get(client).add(stockName+" price decreased by "+price);
        }
    }

    public void changeCount(String stockName, int count)
    {
        if(!stockList.containsKey(stockName))
            return;
        if(count>0) {
            stockList.get(stockName).setCount(count);
            var list = stockList.get(stockName).getSubscriberList();
            for (var client : list) {
                if (!pendingNotifications.containsKey(client))
                    pendingNotifications.put(client, new Vector<>());
                pendingNotifications.get(client).add(stockName + " count changed to " + count);
            }
        }
    }

    public void readFiles() {
        BufferedReader br;
        try{
            br = new BufferedReader(new FileReader(INPUT_FILE_NAME));
            while (true) {
                String line = br.readLine();
                if (line == null) break;
                Stock st = new Stock(line);
                stockList.put(st.getName(), st);
            }
            br.close();
        } catch (IOException e) {
            System.out.println(INPUT_FILE_NAME + " file not found");
        }
        try{
            br = new BufferedReader(new FileReader(SAVED_SUBSCRIBER_LIST));
            while (true) {
                String line = br.readLine();
                if (line == null) break;
                String[] tokens = line.split("__");
                String clientName = tokens[0];
                String stockName = tokens[1];
                subscribe(clientName, stockName);
            }
            br.close();
        } catch (IOException e) {
            System.out.println(SAVED_SUBSCRIBER_LIST + " file not found");
        }
        try{
            br = new BufferedReader(new FileReader(SAVED_NOTIFICATIONS));
            while (true) {
                String line = br.readLine();
                if (line == null) break;
                String[] tokens = line.split("__");
                String clientName = tokens[0];
                String notification = tokens[1];
                if (!pendingNotifications.containsKey(clientName))
                    pendingNotifications.put(clientName, new Vector<>());
                pendingNotifications.get(clientName).add(notification);
            }
            br.close();
        } catch (IOException e) {
            System.out.println(SAVED_NOTIFICATIONS + " file not found");
        }
    }

    public void writeFiles() throws IOException {
        BufferedWriter bw = new BufferedWriter(new FileWriter(INPUT_FILE_NAME));
        for(var stock: stockList.values())
        {
            String str = stock.getName()+" "+stock.getCount()+" "+stock.getPrice();
            bw.write(str);
            bw.write(System.lineSeparator());
        }
        bw.close();
        BufferedWriter crw = new BufferedWriter(new FileWriter(SAVED_NOTIFICATIONS));
        for(var client: pendingNotifications.keySet())
        {
            for(var notification: pendingNotifications.get(client))
            {
                String str = client+"__"+notification;
                crw.write(str);
                crw.write(System.lineSeparator());
            }
        }
        crw.close();
        BufferedWriter srw = new BufferedWriter(new FileWriter(SAVED_SUBSCRIBER_LIST));
        for(var stock: stockList.values())
        {
            var list = stock.getSubscriberList();
            for(var client: list)
            {
                String str = stock.getName()+"__"+client;
                srw.write(str);
                srw.write(System.lineSeparator());
            }
        }
        srw.close();
    }
    public static void main(String[] args) throws IOException {
        System.out.println("Server Started...");
        new Server();
    }
}
