package util;

import Publisher.Stock;

import java.io.Serializable;
import java.util.HashMap;
import java.util.Vector;

public class Data implements Serializable {
    private String clientName;
    private HashMap<String, Stock> stockList;
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

    public HashMap<String, Stock> getStockList() {
        return stockList;
    }

    public void setStockList(HashMap<String, Stock> stockList) {
        this.stockList = stockList;
    }

    public Vector<String> getNotificationList() {
        return notificationList;
    }

    public void setNotificationList(Vector<String> notificationList) {
        this.notificationList = notificationList;
    }
}
