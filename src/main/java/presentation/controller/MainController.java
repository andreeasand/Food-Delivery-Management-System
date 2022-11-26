package presentation.controller;

import BusinessLayer.Client;
import BusinessLayer.DeliveryService;
import presentation.view.*;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;



public class MainController {

    private UserView userView;
    private DeliveryService deliveryService;
    private AdminView adminGUI;
    private EmployeeView employeeView;
    private LogInView logInView;
    private Client user;
    private CompositeView compositeView;
    private BaseView baseView;

    public void start() {
        logInView = new LogInView();
        logInView.setVisible(true);

    }
}
