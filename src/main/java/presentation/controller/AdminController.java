package presentation.controller;


import BusinessLayer.BaseProduct;
import BusinessLayer.DeliveryService;
import BusinessLayer.MenuItem;
import presentation.view.AdminView;
import presentation.view.LogInView;



import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.io.FileNotFoundException;
import java.util.ArrayList;

public class AdminController {
    private AdminView adminView;
    public DeliveryService deliveryService;
    private LogInView logInView;


    public void start(LogInView logInView) throws FileNotFoundException {

        this.logInView = logInView;
//        this.adminView = new AdminView();
//        adminView.setVisible(true);
//        deliveryService = new DeliveryService();
//        adminView.setTable(new ArrayList<>(deliveryService.getMenu()));

        initializeUserInterfaceButtons();
    }

    public void initializeUserInterfaceButtons() {
        adminView.addDeleteActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {

                String s = adminView.getSelectedRow();
                try {
                    deliveryService.deleteMenuItem(s);
                } catch (FileNotFoundException ex) {
                    ex.printStackTrace();
                }

            }
        });

        adminView.addCreateBaseActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                BaseController baseController = new BaseController();
                try {
                    baseController.start(false, null);
                } catch (FileNotFoundException ex) {
                    ex.printStackTrace();
                }
            }
        });

        adminView.addCreateCompActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                CompositeController compositeController = new CompositeController();
                try {
                    compositeController.start();
                } catch (FileNotFoundException ex) {
                    ex.printStackTrace();
                }
            }
        });
        adminView.addEditActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                String title = adminView.getSelectedRow();
                BaseProduct baseProduct=null;
                for(MenuItem menuItem : deliveryService.getMenu()) {
                    if ((menuItem instanceof BaseProduct) && menuItem.getTitle().equals(title))
                        baseProduct = (BaseProduct) menuItem;
                }

                BaseController baseController = new BaseController();
                try {
                    baseController.start( true, baseProduct);
                } catch (FileNotFoundException ex) {
                    ex.printStackTrace();
                }
            }
        });

        adminView.setGenerateReportButton(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                GenerateReportController reportController = new GenerateReportController();
                try {
                    reportController.start();
                } catch (FileNotFoundException ex) {
                    ex.printStackTrace();
                }
            }
        });

    }
}