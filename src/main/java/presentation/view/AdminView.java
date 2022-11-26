package presentation.view;

import BusinessLayer.BaseProduct;
import BusinessLayer.DeliveryService;
import BusinessLayer.MenuItem;
import presentation.controller.BaseController;
import presentation.controller.CompositeController;
import presentation.controller.GenerateReportController;

import java.awt.Font;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.io.FileNotFoundException;
import java.util.ArrayList;

import javax.swing.*;
import javax.swing.table.DefaultTableModel;


public class AdminView extends JFrame {

    private JLabel titleLabel;
    private CompositeView compositeView;

    //private JButton backButton;
    private JButton createBaseButton=new JButton("Add");
    private JButton createCompositeButton=new JButton("Create composite");
    private JButton deleteButton=new JButton("Delete");
    private JButton editButton=new JButton("Edit");
    private JButton generateReportButton=new JButton("Generate button");

    private JTable objectsTable=new JTable();
    private DeliveryService deliveryService= new DeliveryService();



    public AdminView() throws FileNotFoundException {

        this.setDefaultCloseOperation(EXIT_ON_CLOSE);
        this.setBounds(350, 150, 900, 700);
        this.getContentPane().setLayout(null);

        Font biggerFont = new Font("Times New Roman", Font.PLAIN, 18);
        Font hugeFont = new Font("Times New Roman", Font.PLAIN, 32);

        //objectsTable = new JTable();
        objectsTable.setBounds(500, 100, 300, 400);
        objectsTable.setRowHeight(20);
        setTable((ArrayList<MenuItem>) deliveryService.getMenu());
        JScrollPane scrollPane = new JScrollPane(objectsTable);
        JPanel panel = new JPanel();
        panel.setLayout(new BoxLayout(panel, BoxLayout.Y_AXIS));
        panel.setBounds(100, 100, 300, 400);
        panel.add(scrollPane);
        getContentPane().add(panel);

        titleLabel = new JLabel("Admin View");
        titleLabel.setFont(hugeFont);
        titleLabel.setBounds(300, 50, 450, 50);
        getContentPane().add(titleLabel);

//        backButton = new JButton("Back");
//        backButton.setBounds(750, 550, 100, 50);
//        getContentPane().add(backButton);

        createBaseButton = new JButton("Add element");
        createBaseButton.setBounds(20, 550, 125, 50);
        getContentPane().add(createBaseButton);

        createCompositeButton = new JButton("CreateComposite");
        createCompositeButton.setBounds(160, 550, 125, 50);
        getContentPane().add(createCompositeButton);


        deleteButton = new JButton("Delete");
        deleteButton.setBounds(300, 550, 125, 50);
        getContentPane().add(deleteButton);


        editButton = new JButton("Edit");
        editButton.setBounds(450, 550, 125, 50);
        getContentPane().add(editButton);


        generateReportButton = new JButton("GenerateReport");
        generateReportButton.setBounds(600, 550, 125, 50);
        getContentPane().add(generateReportButton);

        createBaseButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                BaseController baseController = new BaseController();
                try {
                    baseController.start( false, null);
                } catch (FileNotFoundException ex) {
                    ex.printStackTrace();
                }
            }
        });

        createCompositeButton.addActionListener(new ActionListener() {
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

        deleteButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                String s = getSelectedRow();
                try {
                    deliveryService.deleteMenuItem(s);
                } catch (FileNotFoundException ex) {
                    ex.printStackTrace();
                }

                JOptionPane.showMessageDialog(null, "product deleted");
            }
        });

        editButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                String title = getSelectedRow();
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

        generateReportButton.addActionListener(new ActionListener() {
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

    public String getSelectedRow(){
        DefaultTableModel model = (DefaultTableModel)objectsTable.getModel();

        int columns = objectsTable.getColumnCount();
        int row = objectsTable.getSelectedRow();
        String[] value = new String[columns];
        for(int i=0;i<columns; i++) {
            value[i] = objectsTable.getModel().getValueAt(row, i).toString();
        }
        return value[0];
    }

    public void addDeleteActionListener(ActionListener actionListener)
    {
        deleteButton.addActionListener(actionListener);
    }
    public void addCreateBaseActionListener(ActionListener actionListener)
    {
        createBaseButton.addActionListener(actionListener);
    }
    public void addCreateCompActionListener(ActionListener actionListener)
    {
        createCompositeButton.addActionListener(actionListener);
    }

    public void addEditActionListener(ActionListener actionListener) {
        editButton.addActionListener(actionListener);
    }

    public void setGenerateReportButton(ActionListener actionListener) {
        generateReportButton.addActionListener(actionListener);
    }


}