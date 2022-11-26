package DataLayer;

import BusinessLayer.DeliveryService;
import BusinessLayer.MenuItem;
import BusinessLayer.Order;

import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.PrintWriter;
import java.util.ArrayList;
import java.util.HashMap;

public class FileWriter {

    public void write(String path, String text) {
        PrintWriter pw = null;
        try {
            pw = new PrintWriter(new FileOutputStream(path));
        } catch (FileNotFoundException e) {
            e.printStackTrace();
        }
        pw.write(text);
        pw.close();
    }

    public void bill(Order order, HashMap<Order, ArrayList<MenuItem>> orders) throws FileNotFoundException {
        //DeliveryService deliveryService = new DeliveryService();
        ArrayList<MenuItem> menuItems = orders.get(order);
        int s = 0;
        for(MenuItem menuItem : menuItems) {
            s += menuItem.computePrice();
        }

        String bill = "Order number " + order.getOrderId() +"\n";
        bill+="DATE: "+order.getOrderDay()+"."+order.getOrderMonth()+"."+order.getOrderYear() +"\n";
        bill+="HOUR: "+order.getHour() +"\n";
        bill+="PODUCTS: "+"\n";
        for(MenuItem menuItem : menuItems) {
            bill += menuItem.getTitle()+ "\n";
            //System.out.println(menuItem.toString());
        }
        bill+="\nTotal Price: " + s;

        String name="bill" + order.getOrderId() + ".txt";
        FileWriter fileWriter = new FileWriter();
        //System.out.println(name);
        fileWriter.write(name, bill);
        //write(new String("bill" + order.getOrderId() + ".txt"), bill);
    }
}
