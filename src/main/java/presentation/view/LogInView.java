package presentation.view;

import BusinessLayer.Client;
import BusinessLayer.DeliveryService;
import BusinessLayer.Role;
import DataLayer.Serializator;

import java.awt.Font;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

import javax.swing.*;

public class LogInView extends JFrame {

    private JLabel titleLabel;
    private JLabel usernameLabel;
    private JLabel paswLabel;
    private JTextField usernameText;
    private JTextField paswText;
    private JButton LogInButton;
    private JButton RegisterButton;

    public LogInView() {
        this.setDefaultCloseOperation(EXIT_ON_CLOSE);
        this.setBounds(500, 150, 700, 700);
        this.getContentPane().setLayout(null);

        // use a bigger font
        Font biggerFont = new Font("Times New Roman", Font.PLAIN, 18);
        Font hugeFont = new Font("Times New Roman",Font.PLAIN,32);

//        titleLabel = new JLabel("User operations");
//        titleLabel.setFont(hugeFont);
//        titleLabel.setBounds(300,50,450,50);
//        getContentPane().add(titleLabel);


        LogInButton = new JButton("LogIn");
        LogInButton.setFont(biggerFont);
        LogInButton.setBounds(100,400,150,50);
        getContentPane().add(LogInButton);

        RegisterButton = new JButton("Register");
        RegisterButton.setFont(biggerFont);
        RegisterButton.setBounds(300,400,150,50);
        getContentPane().add(RegisterButton);

        usernameLabel = new JLabel("UserName");
        usernameLabel.setBounds(100,250,150,50);
        getContentPane().add(usernameLabel);

        paswLabel = new JLabel("Password");
        paswLabel.setBounds(100,300,300,50);
        getContentPane().add(paswLabel);

        usernameText = new JTextField();
        usernameText.setBounds(300,250,300,50);
        getContentPane().add(usernameText);

        paswText = new JTextField();
        paswText.setBounds(300,300,300,50);
        getContentPane().add(paswText);

        addLogInButtonActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                String username = getUsername();
                String password = getPassword();

                DeliveryService deliveryService = null;
                try {
                    deliveryService = new DeliveryService();
                } catch (FileNotFoundException ex) {
                    ex.printStackTrace();
                }
                try {
                    if (deliveryService.logIn(username, password))
                        System.out.println("True");
                } catch (IOException ex) {
                    ex.printStackTrace();
                }
            }
        });

        addRegisterButtonActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                String username = getUsername();
                String password = getPassword();

                DeliveryService deliveryService = null;
                try {
                    deliveryService = new DeliveryService();
                } catch (FileNotFoundException ex) {
                    ex.printStackTrace();
                }
                try {
                    if(deliveryService.register(username, password))
                        System.out.println("True");
                } catch (IOException ex) {
                    ex.printStackTrace();
                }
            }
        });
    }


    public String getUsername() {
        return usernameText.getText();
    }

    public String getPassword(){
        return paswText.getText();
    }


    public void addRegisterButtonActionListener(ActionListener actionListener)
    {
        RegisterButton.addActionListener(actionListener);
    }

    public void addLogInButtonActionListener(ActionListener actionListener)
    {
        LogInButton.addActionListener(actionListener);
    }
}
