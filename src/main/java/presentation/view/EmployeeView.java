package presentation.view;

import java.awt.Font;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.Observable;
import java.util.Observer;

import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JOptionPane;


public class EmployeeView extends JFrame implements Observer {

    private JLabel titleLabel;

    public EmployeeView() {


        this.setDefaultCloseOperation(HIDE_ON_CLOSE);
        this.setBounds(500, 150, 900, 500);
        this.getContentPane().setLayout(null);

        Font biggerFont = new Font("Times New Roman", Font.PLAIN, 18);
        Font hugeFont = new Font("Times New Roman", Font.PLAIN, 32);

        titleLabel = new JLabel("Employee view");
        titleLabel.setFont(hugeFont);
        titleLabel.setBounds(300, 50, 450, 50);
        getContentPane().add(titleLabel);


    }

    @Override
    public void update(Observable o, Object arg) {

        this.setVisible(true);
        System.out.println(arg.toString());
        int x = JOptionPane.showConfirmDialog(null, arg, "Notification !", 2);
        if (x == 0) {
            System.out.println("Chef will cook!");
            this.setVisible(false);
        } else {
            System.out.println("Chef is busy, but will still cook later!");
            this.setVisible(false);
        }
        // System.out.println(x);
        // JOptionPane.showMessageDialog(null, arg);

    }
}
