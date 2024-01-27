package Publisher;

import util.Data;
import util.SocketWrapper;

import java.util.Vector;

public class WriteThreadServer implements Runnable{
    private final Server server;
    private Thread thr;
    private final SocketWrapper socketWrapper;
    private final String clientName;


    public WriteThreadServer(Server server, SocketWrapper socketWrapper, String clientName) {
        this.clientName = clientName;
        this.server = server;
        this.socketWrapper = socketWrapper;
        this.thr = new Thread(this);
        thr.start();
    }
    @Override
    public void run() {
        try{
            System.out.println("Sending data to "+clientName);
            while(true)
            {
                Data data = new Data(clientName);
                //get info from server
                data.setStockList(server.stockList);
                if(server.pendingNotifications.containsKey(clientName)) {
//                    System.out.println(clientName+" has pending notifications");
                    data.setNotificationList(server.pendingNotifications.get(clientName));
                    server.pendingNotifications.get(clientName).clear();
                }
                else
                    data.setNotificationList(new Vector<>());
                if(server.stockList.containsKey(clientName))
                    data.setSubscribedStocks(server.getSubscribedStocks(clientName));
                else
                    data.setSubscribedStocks(new Vector<>());
                socketWrapper.write(data);
                try {
                    Thread.sleep(500);
                } catch (InterruptedException e) {
                    e.printStackTrace();
                }
            }
        }
        catch (Exception e)
        {
            System.out.println(e);
        }

    }
}
