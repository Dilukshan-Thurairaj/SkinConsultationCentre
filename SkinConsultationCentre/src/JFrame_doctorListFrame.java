import javax.swing.*;
import javax.swing.table.DefaultTableCellRenderer;
import java.awt.*;
import java.awt.event.*;

public class JFrame_doctorListFrame implements ActionListener {
    //implementing ActionListener interface

    //Creating a frame
    JFrame frame = new JFrame();

    //initializing instance
    westminsterSkinConsultationManager manager1;

    //Button to sort
    JButton sortBtn;

    //table
    JFrame_doctorTable tableModel;

    //JScrollPane for table
    JScrollPane jScrollPane;


    //constructor
    JFrame_doctorListFrame() {

        //calling the static instance in westminsterSkinConsultationManager class and storing it to this class
        manager1 = westminsterSkinConsultationManager.manager;

        //Creating a frame
        frame.setTitle("Westminster Skin Consultation");
        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        frame.setSize(500, 500);
        frame.setLocationRelativeTo(null);
        frame.setExtendedState(JFrame.MAXIMIZED_BOTH);
        frame.setLayout(new BorderLayout());

        //Calling the savedInformation method in westminsterSkinConsultationManager class --> to read all saved information in file
        manager1.savedInformation();

        //Creating a table and passing the arrayList
        tableModel = new JFrame_doctorTable(manager1.getDoctorList());
        JTable table = new JTable(tableModel);

        // All methods in MouseListener interface was not needed so MouseAdapter class method is overridden
        table.addMouseListener(new MouseAdapter() {
            @Override
            public void mouseClicked(MouseEvent e) {
                //row of the clicked point
                int row = table.rowAtPoint(e.getPoint());
                //col of the clicked point
                int col = table.columnAtPoint(e.getPoint());
                //Tracks user click in column 0 --> Medical license No column only
                if (row>= 0 && col==0){
                    try {
                        //Getting the value at the point clicked and storing it to a variable
                        int medLicenseNo = Integer.parseInt(String.valueOf(table.getValueAt(row, col)));

                        //Disposing this frame
                        frame.dispose();

                        //Opening a new Consultation frame by passing the medLicenseNo of the cell clicked and the ArrayList of doctors
                        new JFrame_Consultation(medLicenseNo,manager1.getDoctorList());

                    }
                    //Catching the exception when formatting a number into int
                    catch (NumberFormatException exception){
                        System.out.println("Error");
                    }
                }
                //If other columns (other than medical license No) clicked the following JOptionPane is used to alert user.
                else{
                    JOptionPane.showMessageDialog(null,
                            "Wrong Click!!\nPlease click only the Medical license number (Med-License No) in the table to book your consultation with the doctor",
                            "Error",JOptionPane.ERROR_MESSAGE);
                }
            }
            });

        //Setting the row height of the table
        table.setRowHeight(60);
        table.setBackground(new Color(239,228,90));

        //Centering the data in each table column
        DefaultTableCellRenderer centerRender = new DefaultTableCellRenderer();
        centerRender.setHorizontalAlignment(JLabel.CENTER);
        for (int i =0; i < table.getColumnCount(); i++) {
            table.getColumnModel().getColumn(i).setCellRenderer(centerRender);
        }

        //Adding and aligning items in the frame
        jScrollPane = new JScrollPane(table);
        jScrollPane.setBackground(new Color(239,228,90));

        //Heading
        JLabel headLabel = new JLabel();
        headLabel.setText("Westminster Skin Consultation: Doctor List");
        headLabel.setFont(new Font("ROMAN_BASELINE",Font.BOLD,30));
        headLabel.setHorizontalAlignment(JLabel.CENTER);
        headLabel.setForeground(Color.black);

        JLabel label2 = new JLabel();
        label2.setText("NOTE: Please Click the Medical License number (Med-Licence No cell) of the doctor to book your appointment");
        label2.setFont(new Font("SERIF",Font.ITALIC,18));
        label2.setHorizontalAlignment(JLabel.CENTER);
        label2.setForeground(Color.black);

        //Heading Panel
        JPanel panel2 = new JPanel();
        panel2.setLayout(new BorderLayout());
        panel2.setPreferredSize(new Dimension(100,100));
        panel2.setBackground(new Color(239,228,90));
        panel2.add(headLabel,BorderLayout.CENTER);
        panel2.add(label2,BorderLayout.SOUTH);


        //Button to sort the table
        sortBtn = new JButton("Sort");
        sortBtn.setPreferredSize(new Dimension(100,50));
        sortBtn.setVerticalAlignment(JButton.CENTER);
        sortBtn.setHorizontalAlignment(JButton.CENTER);
        sortBtn.setFocusable(false);
        sortBtn.addActionListener(this);

        JLabel btnLabel = new JLabel();
        btnLabel.setText("Click the button \"Sort\" to sort the information in the table");
        btnLabel.setFont(new Font("SERIF",Font.BOLD,15));
        btnLabel.setHorizontalAlignment(JLabel.CENTER);

        JLabel label1 = new JLabel();
        label1.setText("West Skin Consultation 2022: Dilukshan Thurairaj");
        label1.setFont(new Font("SERIF",Font.ITALIC,15));
        label1.setForeground(Color.black);
        label1.setHorizontalAlignment(JLabel.CENTER);

        JPanel btnPanel = new JPanel();
        btnPanel.setLayout(new FlowLayout());
        btnPanel.setBackground(new Color(239,228,90));
        btnPanel.add(sortBtn);

        JPanel panel1 = new JPanel();
        panel1.setLayout(new BorderLayout());
        panel1.setBackground(new Color(239,228,90));
        panel1.setPreferredSize(new Dimension(100,118));
        panel1.add(btnPanel,BorderLayout.CENTER);
        panel1.add(btnLabel,BorderLayout.NORTH);
        panel1.add(label1,BorderLayout.SOUTH);


        //adding all items to the frame.
        frame.add(panel2,BorderLayout.NORTH);
        frame.add(panel1,BorderLayout.SOUTH);
        frame.add(jScrollPane);
        frame.setVisible(true);
    }


    @Override
    public void actionPerformed(ActionEvent e) {
        //This will execute when the sortBtn is clicked
        if (e.getSource() == sortBtn){
            //Removing existing table --> because the data in it is not sorted
            frame.remove(jScrollPane);

            //Sorting the list and sending the Array List to JFrame_doctorTable class to create the table
            manager1.sortList();
            JFrame_doctorTable tableModel = new JFrame_doctorTable(manager1.getDoctorList());
            JTable table = new JTable(tableModel);
            frame.add(new JScrollPane(table));

            //Overriding the method mouseClicked in MouseAdapter class to catch the mouse click on the table column --> Medical License No
            table.addMouseListener(new MouseAdapter() {
                @Override
                public void mouseClicked(MouseEvent e) {
                    int row = table.rowAtPoint(e.getPoint());
                    int col = table.columnAtPoint(e.getPoint());
                    if (row>= 0 && col==0){
                        try {
                            int medLicenseNo = Integer.parseInt(String.valueOf(table.getValueAt(row, col)));
                            System.out.println(medLicenseNo);

                            frame.dispose();

                            //Creating a new Consultation frame
                            new JFrame_Consultation(medLicenseNo, manager1.getDoctorList());
                        }
                        catch (NumberFormatException exception){
                            System.out.println("Error");
                        }
                    }
                    else{
                        JOptionPane.showMessageDialog(null,
                                "Wrong Click!!\nPlease click only the Medical license number (Med-License No) in the table to book your consultation with the doctor",
                                "Error",JOptionPane.ERROR_MESSAGE);
                    }
                }
            });

            //Setting the row height of the table
            table.setRowHeight(60);
            table.setBackground(new Color(239,228,90));

            //Centering the data in each table column
            DefaultTableCellRenderer centerRender = new DefaultTableCellRenderer();
            centerRender.setHorizontalAlignment(JLabel.CENTER);
            for (int i =0; i < table.getColumnCount(); i++) {
                table.getColumnModel().getColumn(i).setCellRenderer(centerRender);
            }

            //To refresh the frame
            frame.revalidate();

            //After clicking the Sort button it would stop working
            sortBtn.setEnabled(false);
        }
    }
}
