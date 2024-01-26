package Publisher;

import util.SocketWrapper;

public class ReadThreadServer implements Runnable {
    private Server server;
    private Thread thr;
    private SocketWrapper socketWrapper;
    private String clientName;


    public ReadThreadServer(Server server, SocketWrapper socketWrapper) {
        this.server = server;
        this.socketWrapper = socketWrapper;
        this.thr = new Thread(this);
        thr.start();
    }

    public void run() {
        try {
            Object obj = socketWrapper.read();
            clientName = (String) obj;
            System.out.println("Client name: " + clientName+" is connected");
            new WriteThreadServer(server, socketWrapper, clientName);
            while (true) {
                obj = socketWrapper.read();
                if (obj != null) {
                    if(obj instanceof String line)
                    {
                        String [] tokens = line.split(" ");
                        if(tokens[0].equalsIgnoreCase("s"))
                        {
                            server.subscribe(clientName, tokens[1]);
                        }
                        else if(tokens[0].equalsIgnoreCase("u"))
                        {
                            server.unsubscribe(clientName, tokens[1]);
                        }
                    }
                }
            }
        } catch (Exception e) {
            System.out.println(e);
        }
    }
}
