package presentation.view;

import BusinessLayer.Client;
import BusinessLayer.DeliveryService;
import BusinessLayer.MenuItem;
import presentation.controller.UserController;
import presentation.view.LogInView;

import java.awt.*;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.io.FileNotFoundException;
import java.util.ArrayList;

import javax.swing.*;
import javax.swing.table.DefaultTableModel;


public class UserView extends JFrame {

    DeliveryService deliveryService= new DeliveryService();
    private JLabel titleLabel;
    private JLabel nameLabel;
    private JLabel raitingLabel;
    private JLabel nrcaloriesLabel;
    private JLabel proteinsLabel;
    private JLabel fatsLabel;
    private JLabel sodiuLabel;
    private JLabel priceLabel;
    private JTextField nameText;
    private JTextField raitingText;
    private JTextField nrcaloriesText;
    private JTextField proteinsText;
    private JTextField fatsText;
    private JTextField sodiumText;
    private JTextField priceText;
    private JTable objectsTable=new JTable();

    private JTextArea textArea;

    private JButton addToOrder= new JButton("Add to order");
    private JButton createOrderButton= new JButton("Create order");
    private JButton searchButton= new JButton("Search");
    private JButton showInfoButton= new JButton("Show info");
    Client user;
    private ArrayList<String> products= new ArrayList<>();



    public UserView(Client user) throws FileNotFoundException {

        this.user=user;
        this.setDefaultCloseOperation(EXIT_ON_CLOSE);
        this.setBounds(100, 150, 1200, 700);
        this.getContentPane().setLayout(null);

        UserController uc=new UserController();
        uc.start(this, this.user);

        Font biggerFont = new Font("Times New Roman", Font.PLAIN, 18);
        Font hugeFont = new Font("Times New Roman", Font.PLAIN, 32);

        titleLabel = new JLabel("User window");
        titleLabel.setFont(hugeFont);
        titleLabel.setBounds(300, 50, 450, 50);
        getContentPane().add(titleLabel);

        addToOrder = new JButton("Add to order");
        addToOrder.setFont(biggerFont);
        addToOrder.setBounds(60, 550, 150, 50);
        getContentPane().add(addToOrder);

        createOrderButton = new JButton("Create Order");
        createOrderButton.setFont(biggerFont);
        createOrderButton.setBounds(270, 550, 150, 50);
        getContentPane().add(createOrderButton);

        searchButton = new JButton("Search");
        searchButton.setFont(biggerFont);
        searchButton.setBounds(480, 550, 150, 50);
        getContentPane().add(searchButton);


        showInfoButton = new JButton("Show Info");
        showInfoButton.setFont(biggerFont);
        showInfoButton.setBounds(690, 550, 150, 50);
        getContentPane().add(showInfoButton);

        nameText = new JTextField();
        nameText.setBounds(200,100,200,30);
        getContentPane().add(nameText);

        nameLabel = new JLabel("Name");
        nameLabel.setBounds(100,100,200,30);
        getContentPane().add(nameLabel);

        raitingText = new JTextField();
        raitingText.setBounds(200,140,200,30);
        getContentPane().add(raitingText);

        raitingLabel = new JLabel("Raiting");
        raitingLabel.setBounds(100,140,200,30);
        getContentPane().add(raitingLabel);

        nrcaloriesText = new JTextField();
        nrcaloriesText.setBounds(200,180,200,30);
        getContentPane().add(nrcaloriesText);

        nrcaloriesLabel = new JLabel("NrOfCalories");
        nrcaloriesLabel.setBounds(100,180,200,30);
        getContentPane().add(nrcaloriesLabel);

        proteinsText = new JTextField();
        proteinsText.setBounds(200,220,200,30);
        getContentPane().add(proteinsText);

        proteinsLabel = new JLabel("Proteins");
        proteinsLabel.setBounds(100,220,200,30);
        getContentPane().add(proteinsLabel);

        fatsText = new JTextField();
        fatsText.setBounds(200,260,200,30);
        getContentPane().add(fatsText);

        fatsLabel = new JLabel("Fats");
        fatsLabel.setBounds(100,260,200,30);
        getContentPane().add(fatsLabel);

        sodiumText = new JTextField();
        sodiumText.setBounds(200,300,200,30);
        getContentPane().add(sodiumText);

        sodiuLabel= new JLabel("Sodium");
        sodiuLabel.setBounds(100,300,200,30);
        getContentPane().add(sodiuLabel);

        priceText = new JTextField();
        priceText.setBounds(200,340,200,30);
        getContentPane().add(priceText);

        priceLabel= new JLabel("Price");
        priceLabel.setBounds(100,340,200,30);
        getContentPane().add(priceLabel);

        //objectsTable = new JTable();
        objectsTable.setBounds(500, 100, 300, 400);
        objectsTable.setRowHeight(20);
        setTable((ArrayList<MenuItem>) deliveryService.getMenu());
//        for(MenuItem M: deliveryService.getMenu())
//        {
//            System.out.println(M.toString());
//        }
        JScrollPane scrollPane = new JScrollPane(objectsTable);
        JPanel panel = new JPanel();
        panel.setLayout(new BoxLayout(panel, BoxLayout.Y_AXIS));
        panel.setBounds(500, 100, 300, 400);
        panel.add(scrollPane);
        getContentPane().add(panel);

        textArea = new JTextArea();
        textArea.setBounds(900, 100, 200, 300);
        getContentPane().add(textArea);

        addToOrder.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                String title=objectsTable.getModel().getValueAt(objectsTable.getSelectedRow(), objectsTable.getColumnCount()-1).toString();
                products.add(title);
                JOptionPane.showMessageDialog(null, "Product added to order");
            }
        });
        createOrderButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                try {
                    deliveryService.addOrder(products, user.getUserId());
                    JOptionPane.showMessageDialog(null, "Order placed");
                } catch (FileNotFoundException ex) {
                    ex.printStackTrace();
                }
            }
        });

        searchButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                ArrayList<MenuItem> arrayList = deliveryService.searchProducts( getTitle(),
                        getRating(),
                        getCalories(),
                        getProteins(),
                        getFats(),
                        getSodium(),
                        getPrice());
                setTable(arrayList);
            }
        });

        showInfoButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                String string = getSelectedRow();
                System.out.println(string);
                String s= "";
                for(MenuItem menuItem1 : deliveryService.getMenu()) {
                    if(menuItem1.getTitle().equals(string)) {
                        s+= menuItem1.toString();
                    }
                }
                setInfo(s);
            }
        });


    }

    public void setTable(ArrayList<MenuItem> menu) {
        String[] fields = {"Denumire produs"};
        Object[][] data = new Object[menu.size()][1];
        for(int i=0;i<menu.size();i++) {
            data[i][fields.length-1] = menu.get(i).getTitle();
        }

        //objectsTable= new JTable();
        DefaultTableModel tableModel = (DefaultTableModel) objectsTable.getModel();
        tableModel.setDataVector(data, fields);
        objectsTable.setModel(tableModel);
        //objectsTable.setVisible(true);

    }

    public String[] getSelectedRows(){
        int[] selection = objectsTable.getSelectedRows();
        String[] products = new String[selection.length];

        for(int i=0;i<selection.length; i++) {
            products[i] = objectsTable.getModel().getValueAt(selection[i], 0).toString();
        }

        return products;
    }

    public String getSelectedRow(){
        int columns = objectsTable.getColumnCount();
        int row = objectsTable.getSelectedRow();
        String[] value = new String[columns];
        for(int i=0;i<columns; i++) {
            value[i] = objectsTable.getModel().getValueAt(row, i).toString();
        }

        return value[0];
    }



    public void addCreateOrderActionListener(ActionListener actionListener)
    {
        createOrderButton.addActionListener(actionListener);
    }

    public void addSearchActionListener(ActionListener actionListener)
    {
        searchButton.addActionListener(actionListener);
    }

    public void addShowInfoButtonActionListener(ActionListener actionListener)
    {
        showInfoButton.addActionListener(actionListener);
    }

    public String getTitle(){
        return nameText.getText();
    }

    public String getPrice() {
        return priceText.getText();
    }

    public String getRating(){
        return raitingText.getText();
    }

    public String getCalories(){
        return nrcaloriesText.getText();
    }

    public String getProteins() {
        return proteinsText.getText();
    }

    public String getFats(){
        return fatsText.getText();
    }

    public String getSodium() {
        return sodiumText.getText();
    }

    public void setInfo(String s) {
        textArea.setText(s);
    }

    public ArrayList<String> getProducts() {
        return products;
    }
}