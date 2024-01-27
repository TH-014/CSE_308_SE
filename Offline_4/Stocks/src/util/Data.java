package util;

import Publisher.Stock;

import java.io.Serializable;
import java.util.HashMap;
import java.util.Vector;

public class Data implements Serializable {
    private String clientName;
    private Vector<String> stockList;
    private Vector<String> notificationList;
    private Vector<String> subscribedStocks;

    public Vector<String> getSubscribedStocks() {
        return subscribedStocks;
    }

    public void setSubscribedStocks(Vector<String> subscribedStocks) {
        this.subscribedStocks = subscribedStocks;
    }

    public Data(String clientName) {
        this.clientName = clientName;
    }

    public String getClientName() {
        return clientName;
    }

    public void setClientName(String clientName) {
        this.clientName = clientName;
    }

    public Vector<String> getStockList() {
        return stockList;
    }

    public void setStockList(HashMap<String, Stock> stockMap) {
        stockList = new Vector<>();
        for(String stockName: stockMap.keySet())
        {
            stockList.add(stockName+" "+stockMap.get(stockName).getCount()+" "+stockMap.get(stockName).getPrice());
        }
    }

    public Vector<String> getNotificationList() {
        return notificationList;
    }

    public void setNotificationList(Vector<String> notificationList) {
        this.notificationList = new Vector<>();
        this.notificationList.addAll(notificationList);
    }
}
