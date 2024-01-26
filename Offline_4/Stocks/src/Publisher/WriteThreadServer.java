package Publisher;

import util.Data;
import util.SocketWrapper;

public class WriteThreadServer implements Runnable{
    private Server server;
    private Thread thr;
    private SocketWrapper socketWrapper;
    private String clientName;


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
            while(true)
            {
                Data data = new Data(clientName);
                //get info from server
                data.setStockList(server.stockList);
                data.setNotificationList(server.pendingNotifications.get(clientName));
                server.pendingNotifications.get(clientName).clear();
                data.setSubscribedStocks(server.getSubscribedStocks(clientName));
                socketWrapper.write(data);
            }
        }
        catch (Exception e)
        {
            System.out.println(e);
        }

    }
}
