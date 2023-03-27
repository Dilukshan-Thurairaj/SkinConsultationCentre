import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.io.File;
import java.io.FileWriter;
import java.io.IOException;

public class JFrame_endFrame extends JFrame implements ActionListener {

    JButton saveBtn;
    patient patient;
    consultation consultation;

    Encryption encryptionKey;
    JFrame_endFrame(patient patient,consultation consultation, Encryption encryptionKey){


        this.patient = patient;
        this.consultation = consultation;
        this.encryptionKey = encryptionKey;

        //setting frame
        this.setTitle("Westminster Skin Consultation");
        this.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        this.getContentPane().setBackground(new Color(239,228,90));
        this.setSize(500,500);

        JLabel headLabel = new JLabel();
        headLabel.setText("Westminster Skin Consultation: Thank you for booking");
        headLabel.setFont(new Font("ROMAN_BASELINE",Font.BOLD,30));
        headLabel.setHorizontalAlignment(JLabel.CENTER);
        headLabel.setForeground(Color.black);

        //Patient Details
        JLabel patient_heading = new JLabel();
        patient_heading.setText("Patient Details");

        JPanel patHead = new JPanel();
        patHead.setLayout(new FlowLayout());
        patHead.add(patient_heading);

        //PatientId displayed
        JLabel patient_patID = new JLabel();
        patient_patID.setText("Your patientID: ");

        JLabel patID = new JLabel();
        patID.setText(patient.getPatientId());

        JPanel patID_panel = new JPanel();
        patID_panel.setLayout(new FlowLayout());
        patID_panel.add(patient_patID);
        patID_panel.add(patID);

        //Patient Firstname displayed
        JLabel patient_fName = new JLabel();
        patient_fName.setText("Your Firstname: ");

        JLabel fName = new JLabel();
        fName.setText(patient.getName());

        JPanel fName_panel = new JPanel();
        fName_panel.setLayout(new FlowLayout());
        fName_panel.add(patient_fName);
        fName_panel.add(fName);

        //Patient Surname displayed
        JLabel patient_SName = new JLabel();
        patient_SName.setText("Your Surname: ");

        JLabel SName = new JLabel();
        SName.setText(patient.getSurname());

        JPanel sName_panel = new JPanel();
        sName_panel.setLayout(new FlowLayout());
        sName_panel.add(patient_SName);
        sName_panel.add(SName);

        //Patient Date of Birth
        JLabel patient_dob = new JLabel();
        patient_dob.setText("Your Date of Birth: ");

        JLabel dob = new JLabel();
        dob.setText(patient.getDateOfBirth());

        JPanel dob_panel = new JPanel();
        dob_panel.setLayout(new FlowLayout());
        dob_panel.add(patient_dob);
        dob_panel.add(dob);

        //Patient Mobile Number
        JLabel patient_mobNo = new JLabel();
        patient_mobNo.setText("Your Mobile Number: ");

        JLabel mobNo = new JLabel();
        //Changing it to string as string parameter is allowed for setText method
        mobNo.setText(String.valueOf(patient.getMobileNo()));

        JPanel mobNo_panel = new JPanel();
        mobNo_panel.setLayout(new FlowLayout());
        mobNo_panel.add(patient_mobNo);
        mobNo_panel.add(mobNo);


        JPanel patient_Details = new JPanel();
        patient_Details.setLayout(new GridLayout(6,0));
        patient_Details.add(patHead);
        patient_Details.add(patID_panel);
        patient_Details.add(fName_panel);
        patient_Details.add(sName_panel);
        patient_Details.add(dob_panel);
        patient_Details.add(mobNo_panel);


        //Consultation Details
        JLabel consultation_heading = new JLabel();
        consultation_heading.setText("Consultation Details");

        JPanel consHead = new JPanel();
        consHead.setLayout(new FlowLayout());
        consHead.add(consultation_heading);

        //Doctor License of the doctor with whom the consultation booked is displayed
        JLabel consultation_docLicense = new JLabel();
        consultation_docLicense.setText("Your booked doctor medical license No: ");

        JLabel docLicenseNo = new JLabel();
        //Changing it to string as string parameter is allowed for setText method
        docLicenseNo.setText(String.valueOf(consultation.getConsDocLicenseNo()));

        JPanel docLicense_panel = new JPanel();
        docLicense_panel.setLayout(new FlowLayout());
        docLicense_panel.add(consultation_docLicense);
        docLicense_panel.add(docLicenseNo);

        //Consultation date is displayed
        JLabel consultation_date = new JLabel();
        consultation_date.setText("Your date of consultation: ");

        JLabel consult_date = new JLabel();
        consult_date.setText(consultation.getConsultationDate());

        JPanel date_panel = new JPanel();
        date_panel.setLayout(new FlowLayout());
        date_panel.add(consultation_date);
        date_panel.add(consult_date);

        //Consultation time is displayed
        JLabel consultation_time = new JLabel();
        consultation_time.setText("Your consultation time (Please be 5 to 10 minutes before the time of consultation): ");

        JLabel time = new JLabel();
        //Changing it to string as string parameter is allowed for setText method
        time.setText(String.valueOf(consultation.getConsultationTime()));

        JPanel time_panel = new JPanel();
        time_panel.setLayout(new FlowLayout());
        time_panel.add(consultation_time);
        time_panel.add(time);

        //Consultation Cost is displayed
        JLabel consultation_cost = new JLabel();
        consultation_cost.setText("Your Cost for consultation (Don't forget to pay to the reception before consulting your booked doctor): ");

        JLabel cost = new JLabel();
        //Changing it to string as string parameter is allowed for setText method
        cost.setText(String.valueOf(consultation.getConsultationCost()));

        JPanel cost_panel = new JPanel();
        cost_panel.setLayout(new FlowLayout());
        cost_panel.add(consultation_cost);
        cost_panel.add(cost);


        JPanel consultation_Details = new JPanel();
        consultation_Details.setLayout(new GridLayout(5,0));
        consultation_Details.add(consHead);
        consultation_Details.add(docLicense_panel);
        consultation_Details.add(date_panel);
        consultation_Details.add(time_panel);
        consultation_Details.add(cost_panel);

        //Combining patient and consultation details
        JPanel combineDetails = new JPanel();
        combineDetails.setLayout(new GridLayout(0,2));
        combineDetails.add(patient_Details);
        combineDetails.add(consultation_Details);

        //Button to saveInformation
        JLabel saveInformation = new JLabel();
        saveInformation.setText("Click to get the booking receipt");

        saveBtn = new JButton("Receipt");
        saveBtn.setPreferredSize(new Dimension(100,40));
        saveBtn.setFocusable(false);
        saveBtn.addActionListener( this);

        JPanel btnPanel = new JPanel();
        btnPanel.setLayout(new FlowLayout());
        btnPanel.setBackground(new Color(239,228,90));
        btnPanel.add(saveInformation);
        btnPanel.add(saveBtn);


        this.add(headLabel,BorderLayout.NORTH);
        this.add(combineDetails,BorderLayout.CENTER);
        this.add(btnPanel,BorderLayout.SOUTH);
        this.setVisible(true);
    }

    @Override
    public void actionPerformed(ActionEvent e) {
        if(e.getSource() == saveBtn){

            //Using JFileChooser to allow user to save all the consultation information --> Receipt
            JFileChooser fileChooser = new JFileChooser();

            fileChooser.setSelectedFile(new File("Receipt - Westminster Skin Consultation"));

            int response = fileChooser.showSaveDialog(fileChooser);


            if (response == JFileChooser.APPROVE_OPTION){
                try {
                    FileWriter writer= new FileWriter(fileChooser.getSelectedFile().getAbsolutePath());
                    writer.write("Patient Details");
                    writer.write("\n");
                    writer.write("Customer patient ID: "+patient.getPatientId());
                    writer.write("\n");
                    writer.write("Customer Firstname: "+patient.getName());
                    writer.write("\n");
                    writer.write("Customer Surname: "+patient.getSurname());
                    writer.write("\n");
                    writer.write("Customer Date of birth: "+patient.getDateOfBirth());
                    writer.write("\n");
                    writer.write("Customer Mobile number: "+patient.getMobileNo());

                    writer.write("\n\n");

                    writer.write("Consultation Details");
                    writer.write("\n");
                    writer.write("Consulting Doctor Medical License No: "+consultation.getConsDocLicenseNo());
                    writer.write("\n");
                    writer.write("Date of Consultation: "+consultation.getConsultationDate());
                    writer.write("\n");
                    writer.write("Time of Consultation: "+consultation.getConsultationTime());
                    writer.write("\n");
                    writer.write("Cost of Consultation: "+consultation.getConsultationCost());
                    writer.write("\n");
                    writer.write("Text Note to the consulting doctor: "+encryptionKey.decryptText(consultation.getTextNote()));

                    writer.write("\n\n");

                    writer.write("Thank you for consulting at Westminster Skin Consultation\nHave a good day :)");
                    writer.close();
                    this.dispose();
                }
                catch (IOException exception) {
                    JOptionPane.showMessageDialog(null,
                            "Wrong Path to save the file\nPlease retry",
                            "Error", JOptionPane.ERROR_MESSAGE);
                }


            }
        }
    }
}
