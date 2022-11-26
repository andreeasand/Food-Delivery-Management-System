package presentation.view;

import BusinessLayer.DeliveryService;
import BusinessLayer.MenuItem;

import java.awt.Font;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.io.FileNotFoundException;
import java.util.ArrayList;

import javax.swing.*;
import javax.swing.table.DefaultTableModel;


public class CompositeView extends JFrame {


    private JLabel titleLabel;
    private JButton addButton;
    private JButton completeMenu;
    //private JButton backButton;
    private JLabel title1Label;
    private JTextField titleText;

    private JTable objectsTable;
    private ArrayList<String> products= new ArrayList<>();


    public CompositeView() throws FileNotFoundException {

        DeliveryService deliveryService= new DeliveryService();
        this.setDefaultCloseOperation(HIDE_ON_CLOSE);
        this.setBounds(500, 150, 900, 700);
        this.getContentPane().setLayout(null);

        Font biggerFont = new Font("Times New Roman", Font.PLAIN, 18);
        Font hugeFont = new Font("Times New Roman", Font.PLAIN, 32);

        titleLabel = new JLabel("Composite operations ");
        titleLabel.setFont(hugeFont);
        titleLabel.setBounds(300, 50, 450, 50);
        getContentPane().add(titleLabel);

//        backButton = new JButton("Back");
//        backButton.setBounds(750, 550, 100, 50);
//        getContentPane().add(backButton);

        title1Label = new JLabel("Name the menu: ");
        title1Label.setBounds(250, 150, 150, 40);
        getContentPane().add(title1Label);

        titleText = new JTextField();
        titleText.setBounds(100, 200, 300, 40);
        getContentPane().add(titleText);

        addButton = new JButton("Add Item");
        addButton.setFont(biggerFont);
        addButton.setBounds(50, 550, 125, 40);
        getContentPane().add(addButton);

        completeMenu = new JButton("Complete Menu");
        completeMenu.setFont(biggerFont);
        completeMenu.setBounds(650, 550, 225, 40);
        getContentPane().add(completeMenu);

        objectsTable = new JTable();
        objectsTable.setBounds(500, 100, 300, 400);
        objectsTable.setRowHeight(20);
        setTable((ArrayList<MenuItem>) deliveryService.getMenu());
        objectsTable.setRowSelectionAllowed(true);
        objectsTable.setSelectionMode(ListSelectionModel.MULTIPLE_INTERVAL_SELECTION);
        objectsTable.setColumnSelectionAllowed(false);
        objectsTable.setRowSelectionAllowed(true);
        objectsTable.addRowSelectionInterval(1,(objectsTable.getRowCount()-2));
        JScrollPane scrollPane = new JScrollPane(objectsTable);
        JPanel panel = new JPanel();
        panel.setLayout(new BoxLayout(panel, BoxLayout.Y_AXIS));
        panel.setBounds(500, 100, 300, 400);
        panel.add(scrollPane);
        getContentPane().add(panel);

        addButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                addProduct();
                JOptionPane.showMessageDialog(null, "Product added to menu");

            }
        });

    }

    public void setTable(ArrayList<MenuItem> menu) {
        String[] fields = {"Denumire produs"};
        Object[][] data = new Object[menu.size()][fields.length];
        for(int i=0;i<menu.size();i++) {
            data[i][fields.length-1] = menu.get(i).getTitle();
        }

        DefaultTableModel tableModel = (DefaultTableModel) objectsTable.getModel();
        tableModel.setDataVector(data, fields);
        objectsTable.setModel(tableModel);

    }

    public void addProduct(){
        String title= objectsTable.getModel().getValueAt(objectsTable.getSelectedRow(), objectsTable.getColumnCount()-1).toString();

//        for(int i=0;i<selection.length; i++) {
//            products[i] = objectsTable.getModel().getValueAt(selection[i], objectsTable.getColumnCount()-1).toString();
//        }
        products.add(title);
    }

    public ArrayList<String> getProducts() {
        return products;
    }

    public String getTitle(){
        return titleText.getText();
    }

    public void addEditButtonActionListener(ActionListener actionListener)
    {
        completeMenu.addActionListener(actionListener);
    }

}