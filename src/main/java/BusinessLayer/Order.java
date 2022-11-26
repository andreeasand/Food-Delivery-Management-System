package BusinessLayer;

import java.io.Serializable;
import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.Date;
import java.util.GregorianCalendar;

public class Order implements Serializable {
    private int orderId;
    private int clientId;
    private LocalDateTime orderDate;
    // ArrayList<MenuItem> menuItems;

    public Order(){}

    public Order(int orderId, int clientId, LocalDateTime orderDate) {
        this.orderId = orderId;
        this.clientId = clientId;
        this.orderDate = orderDate;
        // this.menuItems=menuItems;
    }

    public int getOrderId() {
        return orderId;
    }

    public void setOrderId(int orderId) {
        this.orderId = orderId;
    }

    public int getClientId() {
        return clientId;
    }

    public void setClientId(int clientId) {
        this.clientId = clientId;
    }

    public LocalDateTime getOrderDate() {
        return orderDate;
    }

    public void setOrderDate(LocalDateTime orderDate) {
        this.orderDate = orderDate;
    }

    public int getHour(){
        return orderDate.getHour();
    }

    public int getOrderDay(){
        return orderDate.getDayOfMonth();
    }
    public int getOrderMonth(){
        return  orderDate.getMonthValue();
    }
    public int getOrderYear(){
        return orderDate.getYear();
    }

//    public ArrayList<MenuItem> getMenuItems() {
//        return menuItems;
//    }
//
//    public void setMenuItems(ArrayList<MenuItem> menuItems) {
//        this.menuItems = menuItems;
//    }

    @Override
    public int hashCode() {
        return orderId;
    }
}
