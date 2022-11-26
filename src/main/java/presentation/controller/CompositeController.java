package presentation.controller;

import BusinessLayer.CompositeProduct;
import BusinessLayer.DeliveryService;
import BusinessLayer.MenuItem;
import presentation.view.CompositeView;
import presentation.view.LogInView;

import javax.swing.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.io.FileNotFoundException;
import java.util.ArrayList;

public class CompositeController {

    private CompositeView compositeView;
    public DeliveryService deliveryService;
    private LogInView logInView;


    public void start() throws FileNotFoundException {

        //this.logInView = logInView;
        this.compositeView = new CompositeView();
        compositeView.setVisible(true);
        deliveryService = new DeliveryService();
        compositeView.setTable(new ArrayList<>(deliveryService.getMenu()));


        initializeUserInterfaceButtons();
    }

    public void initializeUserInterfaceButtons() {

        compositeView.addEditButtonActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                ArrayList<MenuItem> menuItems = new ArrayList<>();
                ArrayList<String> rows = compositeView.getProducts();
                int i=0;
                if(rows.size()>0)
                    do {
                        for(MenuItem menuItem : deliveryService.getMenu()) {
                            if(rows.get(i).equals(menuItem.getTitle()))
                                menuItems.add(menuItem);
                        }
                        i++;
                    }while(i< rows.size());

                CompositeProduct compositeProduct = new CompositeProduct(compositeView.getTitle(), menuItems);

                try {
                    deliveryService.addCompositeMenuItem(compositeView.getTitle(), menuItems);
                } catch (FileNotFoundException ex) {
                    ex.printStackTrace();
                }
                JOptionPane.showMessageDialog(null,"menu created");

            }
        });

    }
}