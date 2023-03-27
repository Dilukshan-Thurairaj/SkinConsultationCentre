import javax.swing.*;
import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileWriter;
import java.io.IOException;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.time.format.DateTimeFormatter;
import java.time.format.DateTimeParseException;
import java.util.*;


public class JFrame_patientConsultation {

    //Initializing a boolean to true
    boolean available = true;

    //Creating an arrayList to store patient objects
    ArrayList<patient> patientList = new ArrayList<>();

    //Creating an arrayList to store consultation objects
    ArrayList<consultation> consultationList = new ArrayList<>();

    //variables to store the information from the previous frame JTextFields
    String patID,firstname,surname,dateOfBirth,consultDate,consultNote;
    int medLicenseNo,mobNo,consultTime,consultCost;

    //Initializing consultation, patient and encryption key
    private consultation consultation;
    private patient patient;

    private Encryption encryption;

    //Constructor
    JFrame_patientConsultation(){

    }

    //Getter for patient object booked
    public patient getPatientBooked(){
        return patient;
    }

    //Getter for consultation object booked
    public consultation getConsultationBooked(){
        return consultation;
    }

    //Getter for encryption Key object created
    public Encryption getEncryption() {
        return encryption;
    }

    //Getting all the information from previous frame
    public void patientInformation(String patientID, String fName, String sName, String dob, String mobileNo, String consultDate, String consultTime,
                                   String consultHour, String consultNote, int docMedLicense, ArrayList<doctor> docList)
    {
        //Read all information in the file and store it in the arraylist
        readFile();
        try {
            //Setting the variables in this class
            this.patID = patientID;
            this.firstname = fName;
            this.surname = sName;
            this.dateOfBirth = dob;
            this.mobNo = Integer.parseInt(mobileNo);


            //Calling the method to validate the date
            formatDOB(dateOfBirth, new Date());

            //Creating an instance of patient class and passing the variables
            patient = new patient(patID,firstname,surname,dateOfBirth,mobNo);

            //Calling method to create the consultation instance
            consultationInformation(docMedLicense,consultDate,consultTime,consultHour,consultNote,patient,docList);

            //Saving all information to the file
            saveFile();


        }
        //Catch error in date format entered
        catch (DateTimeParseException e) {
            JOptionPane.showMessageDialog(null,
                    "The entered date is wrong!!!\nPlease enter the date in this format -> 1/1/1111\nEnter a correct Date of Birth",
                    "Error", JOptionPane.ERROR_MESSAGE);
        }
        //catch any string added to int variables
        catch (NumberFormatException e) {
            JOptionPane.showMessageDialog(null,
                    "Wrong Mobile number!!!!\nPlease enter a proper mobile number to continue",
                    "Error", JOptionPane.ERROR_MESSAGE);
        }
        //Catch any exception other than the above-mentioned
        catch (Exception e) {
            JOptionPane.showMessageDialog(null,
                    "Wrong Date!!!\nThe entered date is in the future\nPlease enter your correct date of birth",
                    "Error", JOptionPane.ERROR_MESSAGE);
        }
    }

    //Creating consultation instance in this method
    private void consultationInformation(int docLicense,String date,String time,String hour,String note, patient Patient,ArrayList<doctor> doctorArrayList)
    {
        try {
            //Setting the variables in this class
            this.medLicenseNo = docLicense;
            this.consultDate = date;
            this.consultTime = Integer.parseInt(time);
            //encryption key created from Encryption class
            encryption = new Encryption();
            //encrypting the text
            this.consultNote = encryption.encryptText(note);

            int consultHour = Integer.parseInt(hour);

            //Passing the patient object created to the method to find the cost per hour
            this.consultCost = costCal(Patient) * consultHour;

            //Calling the method to validate the date entered
            formatConsultDate(consultDate, new Date());

            //Calling method to validate time entered within the day
            formatTime(consultTime,consultDate,new Date());

            //Checking availability of the doctor
            available = selectedDocAvailability(medLicenseNo,consultDate,consultTime,Patient);

            //consultation instance created
            consultation = docAvailability(doctorArrayList,Patient);


            if (consultation == null){
                JOptionPane.showMessageDialog(null,
                        "There no doctors available on the date and time entered\nPlease change the time or date to book your consultation",
                        "Change Date or Time", JOptionPane.INFORMATION_MESSAGE);
            }

            else{
                //The PATIENT OBJECT AND CONSULTATION OBJECT are put into the arraylist
                //The patient object is passed in arraylist here because the consult cost has to be calculated by checking,
                //the entries in the patient arraylist --> to identify whether the user has consulted doctor before.
                consultationList.add(consultation);
                patientList.add(Patient);
            }
        }
        //Catch error in date format entered
        catch (DateTimeParseException e) {
            JOptionPane.showMessageDialog(null,
                    "The entered date is wrong!!!\nPlease enter the date in this format -> 1/1/1111\nEnter a correct date for consultation",
                    "Error", JOptionPane.ERROR_MESSAGE);
        }
        //catch any string added to int variables
        catch (NumberFormatException e) {
            JOptionPane.showMessageDialog(null,
                    "Number not entered!!!!\nPlease enter a number to the time of consultation and the hour of consultation",
                    "Error", JOptionPane.ERROR_MESSAGE);
        }
        //catch if the consultation object is null
        catch (NullPointerException e){
            JOptionPane.showMessageDialog(null,
                    "Sorry there are no doctors available on the date and time entered\nPlease try a new date or a different time",
                    "Change time and date", JOptionPane.INFORMATION_MESSAGE);
        }
        //Catch any exception other than the above-mentioned
        catch (Exception e) {
            JOptionPane.showMessageDialog(null,
                    "Wrong Date!!!\nThe entered date is in the past\nPlease enter a proper date to book your consultation with doctor",
                    "Error", JOptionPane.ERROR_MESSAGE);
        }
    }

    //Method to check the format of consultation date
    private void formatConsultDate(String consultDate, Date date) throws Exception {
        try {
            //Changing date format of the user's entered date
            DateTimeFormatter dateFormat = DateTimeFormatter.ofPattern("d/M/yyyy");
            String enteredDateFormat = dateFormat.format(dateFormat.parse(consultDate));

            //Changing date format of the current Date
            SimpleDateFormat format = new SimpleDateFormat("d/M/yyyy");
            String currentDateFormat = format.format(date);

            //Now changing the String type into Date type
            Date date1 = format.parse(currentDateFormat);
            Date date2 = format.parse(enteredDateFormat);

            //Comparing two dates, to find whether any past date is given for consultation
            //To check whether entered date is in past
            if (date2.compareTo(date1) < 0) {
                throw new Exception();
            }
        }
        catch (ParseException e){
            //Throwing exception
            throw new RuntimeException(e);
        }
    }


    //Method to find whether future date entered by the user for date of birth
    private void formatDOB(String dob, Date date) throws Exception{
        try {
            //Changing date format of the user's entered date
            DateTimeFormatter dateFormat = DateTimeFormatter.ofPattern("d/M/yyyy");
            String enteredDateFormat = dateFormat.format(dateFormat.parse(dob));

            //Changing date format of the current Date
            SimpleDateFormat format = new SimpleDateFormat("d/M/yyyy");
            String currentDateFormat = format.format(date);

            //Now changing the String type into Date type
            Date date1 = format.parse(currentDateFormat);
            Date date2 = format.parse(enteredDateFormat);
            //Comparing two dates, to find whether any future date is given for date of birth
            if (date2.compareTo(date1) > 0 || date2.compareTo(date1) == 0) {
                throw new Exception();
            }
        }
        catch (ParseException e){
            throw new RuntimeException(e);
        }
    }

    //Method to check the format of consultation time
    private void formatTime(int hourEnter,String consultDate,Date date) throws ParseException {

        //Checking the date entered with the current date
        //Changing date format of the user's entered date
        DateTimeFormatter dateFormat = DateTimeFormatter.ofPattern("d/M/yyyy");
        String enteredDateFormat = dateFormat.format(dateFormat.parse(consultDate));

        //Changing date format of the current Date
        SimpleDateFormat format = new SimpleDateFormat("d/M/yyyy");
        String currentDateFormat = format.format(date);

        //Now changing the String type into Date type
        //Current date
        Date date1 = format.parse(currentDateFormat);
        //Entered date by the user
        Date date2 = format.parse(enteredDateFormat);


        Calendar calendar = GregorianCalendar.getInstance(); // creating a new calendar instance
        int currentHour = calendar.get(Calendar.HOUR_OF_DAY);//Get hour in 24hr format


        //Validating the time entered by the user (Checking whether entered time is past)
        if (((currentHour > hourEnter) && (date2.compareTo(date1) < 0)) || ((currentHour > hourEnter) && (date2.compareTo(date1) == 0))){
            JOptionPane.showMessageDialog(null,
                    "The entered time has passed!!!\nPlease enter a proper time to consult the doctor",
                    "Error", JOptionPane.ERROR_MESSAGE);
        }

    }

    //Method to send the cost of consultation to the previous frame when the button is clicked
    public int CostBtn(String patientID,String fName,String sName,String dob,String mobileNo){
        int cost = 0;
        try {
            if (fName.length() > 0 && sName.length() > 0) {

                //Calling the method to validate the date
                formatDOB(dob, new Date());

                //Creating new patient instance
                patient patient = new patient(patientID, fName, sName, dob, Integer.parseInt(mobileNo));

                //read information in the files
                readFile();

                //passing the patient instance created to find the cost of consultation
                cost = costCal(patient);

                //clear the read information
                patientList.clear();
                consultationList.clear();

            }
            else{
                JOptionPane.showMessageDialog(null,
                        "Please enter your name and surname to calculate your consultation cost",
                        "Error", JOptionPane.ERROR_MESSAGE);
            }
        }
        catch (DateTimeParseException e) {
            JOptionPane.showMessageDialog(null,
                    "The entered date is wrong!!!\nPlease enter the date in this format -> 1/1/1111\nEnter a correct Date of Birth",
                    "Error", JOptionPane.ERROR_MESSAGE);
        }
        catch (NumberFormatException e) {
            JOptionPane.showMessageDialog(null,
                    "Wrong Mobile number!!!!\nPlease enter a proper mobile number to continue",
                    "Error", JOptionPane.ERROR_MESSAGE);
        }
        catch (Exception e) {
            JOptionPane.showMessageDialog(null,
                    "Wrong Date!!!\nThe entered date is in the future\nPlease enter your correct date of birth",
                    "Error", JOptionPane.ERROR_MESSAGE);
        }
        return  cost;
    }

    //method to calculate the cost (15 or 25)
    private int costCal(patient Patient){
        int consult = 15;
        for (patient i : patientList) {
            //If all these value match that means the user is consulting the doctor using Westminster Skin Consultation for the second time to book doctor
            // So the loop breaks.
            //If his data does not match with the data in system then it's the first time user books for doctor
            if (i.getName().equalsIgnoreCase(Patient.getName()) && i.getSurname().equalsIgnoreCase(Patient.getSurname())
                    && i.getDateOfBirth().equalsIgnoreCase(Patient.getDateOfBirth())) {
                consult = 25;
                break;
            }
        }
        return consult;
    }

    //method to check the selected doctor's availability on the date and time asked for consultation by the user
    private boolean selectedDocAvailability(int medLicense, String date, int time, patient patient){
        int cost = costCal(patient);
        boolean availability = true;
        for (consultation i : consultationList){
            if (medLicense == i.getConsDocLicenseNo() && date.equals(i.getConsultationDate())
                    && (time == i.getConsultationTime() || time < (i.getConsultationTime() + (i.getConsultationCost() / cost))))
            {
                availability = false;
                break;
            }
        }
        return availability;
    }


    public consultation docAvailability(ArrayList<doctor> doctorArrayList,patient Patient){
        consultation consultation = null;
        //if the stored availability is true then add consultation with that doctor
        if (available) {
            consultation = new consultation(medLicenseNo, consultDate, consultTime, consultCost, consultNote);
        }
        //Book with another doctor who is available on the time and date
        else {
            boolean availability;
            for (doctor doc : doctorArrayList) {
                availability = selectedDocAvailability(doc.getMedLicenceNo(), consultDate, consultTime, Patient);
                if (availability) {
                    consultation = new consultation(doc.getMedLicenceNo(), consultDate, consultTime, consultCost, consultNote);
                    break;
                }
            }
        }
        return consultation;
    }


    //Method to generate unique patientID
    public String patientID (){
        return UUID.randomUUID().toString();
    }

    //Method to delete the patient and consultation from the system
    public void deleteConsultation(String patientID, consultation consult){

        //read saved Information
        readFile();

        for(patient i : patientList){
            if (patientID.equals(i.getPatientId()))
            {
                patientList.remove(i);
                break;
            }
        }

        for(consultation i : consultationList) {
            if (consult.toString().equals(i.toString()))
            {
                consultationList.remove(i);
                break;
            }
        }
        //Save information after deleting
        saveFile();

    }


    //Saving information of patients and consultation in a file
    private void saveFile(){
        //Patient File
        try {
            FileWriter file = new FileWriter("patientInformation.txt");
            for (patient i : patientList) {
                file.write(i.toString());
                file.write("\n\n");
            }
            file.close();
        }
        catch (IOException e) {
            System.out.println("The file to save all information is missing!!!");
        }

        //Consultation file
        try {
            FileWriter file = new FileWriter("consultInformation.txt");

            for (consultation i : consultationList) {
                file.write(i.toString());
                file.write("\n\n");
            }
            file.close();
        }
        catch (IOException e) {
            System.out.println("The file to save all information is missing!!!");
        }
    }

    //Reading the saved information in the files
    private void readFile() {

        //Read patient File
        File patientFile = new File("patientInformation.txt");
        if (patientFile.canRead()) {
            try {
                Scanner read = new Scanner(patientFile);
                while (read.hasNext()) {
                    String patID = read.next();
                    String firstName = read.next();
                    String surname = read.next();
                    String dateOfBirth = read.next();
                    String mobileNo = read.next();
                    int mobileNumInt = Integer.parseInt(mobileNo);
                    patient patient = new patient(patID,firstName,surname,dateOfBirth,mobileNumInt);
                    patientList.add(patient);
                }
                read.close();
            }
            catch (FileNotFoundException e) {
                System.out.println("The file to read the saved information is missing!!!\nAdd that file to read and store it to the system.");
            }
        }

        //Read consultation File
        File consultFile = new File("consultInformation.txt");
        if (consultFile.canRead()) {
            try {
                Scanner read = new Scanner(consultFile);
                while (read.hasNext()) {
                    String docLicense = read.next();
                    String consultDate = read.next();
                    String consultTime = read.next();
                    String consultCost = read.next();
                    String consultNote = read.next();
                    int consDocLicenseInt = Integer.parseInt(docLicense);
                    int consultTimeInt = Integer.parseInt(consultTime);
                    int consultCostInt = Integer.parseInt(consultCost);
                    consultation consult = new consultation(consDocLicenseInt,consultDate,consultTimeInt,consultCostInt,consultNote);
                    consultationList.add(consult);
                }
                read.close();
            }
            catch (FileNotFoundException e) {
                System.out.println("The file to read the saved information is missing!!!\nAdd that file to read and store it to the system.");
            }
        }
    }

}
