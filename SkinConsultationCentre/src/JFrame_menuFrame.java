import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

public class JFrame_menuFrame extends JFrame implements ActionListener {
    //extends JFrame and implements ActionListener

    //Creating a new button
    JButton myBtn = new JButton();
    JFrame_menuFrame(){

        //Setting frame
        this.setTitle("Westminster Skin Consultation");
        this.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        this.pack();
        this.getContentPane().setBackground(new Color(239,228,90));
        this.setSize(500,500);
        this.setLocationRelativeTo(null);
        this.setResizable(false);
        this.setLayout(null);

        //Label 1
        JLabel label1 = new JLabel();
        label1.setText("Westminster Skin Consultation");
        label1.setFont(new Font("ROMAN_BASELINE",Font.BOLD,30));
        label1.setForeground(Color.black);
        label1.setBounds(15,15,500,30);

        //Label 2
        JLabel label2 = new JLabel();
        label2.setText("Click the button below to book your doctor");
        label2.setFont(new Font("ROMAN_BASELINE",Font.ITALIC,15));
        label2.setForeground(Color.black);
        label2.setBounds(100,120,300,100);

        //Label 3
        JLabel label3 = new JLabel();
        label3.setText("West Skin Consultation 2022: Dilukshan Thurairaj");
        label3.setFont(new Font("SERIF",Font.ITALIC,15));
        label3.setForeground(Color.black);
        label3.setBounds(90,400,400,100);

        //Creating a button
        myBtn.setBounds(165,190,150,40);
        myBtn.setText("Consult Doctors");
        myBtn.setFocusable(false);
        myBtn.addActionListener(this);
        myBtn.setVerticalAlignment(JButton.CENTER);
        myBtn.setHorizontalAlignment(JButton.CENTER);

        //Add to the frame
        this.add(myBtn);
        this.add(label1);
        this.add(label2);
        this.add(label3);
        this.setVisible(true);
    }

    //Action Listener interface method 
    @Override
    public void actionPerformed(ActionEvent e) {
        //Once button clicked dispose this frame and open new doctorList frame
        if (e.getSource() == myBtn) {
            this.dispose();
            JFrame_doctorListFrame panel = new JFrame_doctorListFrame();
        }
    }
}
