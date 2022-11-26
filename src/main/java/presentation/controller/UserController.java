package presentation.controller;

import BusinessLayer.Client;
import BusinessLayer.DeliveryService;
import BusinessLayer.MenuItem;
import BusinessLayer.Order;
import presentation.view.*;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.io.FileNotFoundException;
import java.time.LocalDateTime;
import java.util.ArrayList;

public class UserController {

    private UserView userView;
    public DeliveryService deliveryService;
    private LogInView logInView;
    private Client user;

    public void start(UserView userView, Client user) throws FileNotFoundException {
        this.user = user;
        this.userView = userView;
//        userView.setVisible(true);
//        this.logInView = logInView;

        deliveryService = new DeliveryService();

        userView.setTable(new ArrayList<>(deliveryService.getMenu()));

        initializeUserInterfaceButtons();
    }

    public void initializeUserInterfaceButtons() {
//        userView.addCreateOrderActionListener(new ActionListener() {
//            @Override
//            public void actionPerformed(ActionEvent e) {
//
//                try {
//                    deliveryService.addOrder(userView.getSelectedRows(), user.getUserId());
//                } catch (FileNotFoundException ex) {
//                    ex.printStackTrace();
//                }
//            }
//        });

        userView.addSearchActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                ArrayList<MenuItem> arrayList = deliveryService.searchProducts( userView.getTitle(),
                        userView.getRating(),
                        userView.getCalories(),
                        userView.getProteins(),
                        userView.getFats(),
                        userView.getSodium(),
                        userView.getPrice());

                userView.setTable(arrayList);


            }
        });

        userView.addShowInfoButtonActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                String string = userView.getSelectedRow();
                System.out.println(string);
                String s= "";
                for(MenuItem menuItem1 : deliveryService.getMenu()) {
                    if(menuItem1.getTitle().equals(string)) {
                        s+= menuItem1.toString();
                    }
                }
                userView.setInfo(s);

            }
        });


    }


}
