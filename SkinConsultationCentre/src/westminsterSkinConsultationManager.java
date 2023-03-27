import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileWriter;
import java.io.IOException;
import java.time.format.DateTimeFormatter;
import java.time.format.DateTimeParseException;
import java.util.*;

public class westminsterSkinConsultationManager implements skinConsultationManager {
    //implementing the interface

    //Array List to store doctor objects
    private final ArrayList<doctor> doctorList = new ArrayList<>();

    //Variable to count of the number of doctors added
    private int doctorCount;


    //creating an instance of this class, is static so the main class can also access it.
    static westminsterSkinConsultationManager manager = new westminsterSkinConsultationManager();

    //Getter for the doctor arraylist created
    public ArrayList<doctor> getDoctorList() {
        return doctorList;
    }

    //sorting the information in the arrayList
    public void sortList(){
        Collections.sort(doctorList, new myDoctorComparator());
    }

    //main
    public static void main(String[] args) {
        Scanner input = new Scanner(System.in);
        //boolean variable set to true
        boolean loop = true;

        //Calling the method to read the information stored in the file
        manager.savedInformation();


        try{
            //while true
            while (loop) {
                //Print information to console menu
                System.out.println("Welcome to the Westminster Skin Consultation");
                System.out.println("Welcome Manager!");
                System.out.println("Enter '1' to add a new doctor to the system");
                System.out.println("Enter '2' to delete a doctor from the system");
                System.out.println("Enter '3' to print the list of the doctors in the consultation centre");
                System.out.println("Enter '4' to save all the information entered to the system");
                System.out.println("Enter '5' to exit the program");
                System.out.print("Enter: ");

                //get input
                int entry = input.nextInt();

                if (entry == 1) {
                    manager.addDoctorSelection();
                } else if (entry == 2) {
                    manager.deleteDoctorSelection();
                } else if (entry == 3) {
                    manager.printDoctorsSelection();
                } else if (entry == 4) {
                    manager.saveDoctorsSelection();
                } else if (entry == 5) {
                    //Program ends
                    System.out.println("The program ends\nCome back later :)");
                    loop = false;
                }
                else{
                    System.out.println("Please enter (1,2,3,4,5) numbers to work in the system and not any other numbers!!!");
                }
            }
        }
        //Catching the exception --> entering string to int variables
        catch(InputMismatchException e){
            System.out.println("Please enter only numbers as input (numbers that are prompt in the console menu and not other values)\nPlease try again");
        }
        //Catch any exception other than the above
        catch (Exception e){
            System.out.println("There is a error in the system when entering number in console menu");
        }

    }

    public void addDoctorSelection() {
        try {
            //If the doctor count is less than 10
            if (doctorCount < 10) {
                Scanner input = new Scanner(System.in);
                //Add doctor to system
                System.out.println("Add a Doctor to Westminster Skin Consultation");

                //Getting the medical license number from the manager
                System.out.print("Enter the medical license number of the doctor: ");
                int medicalNo = input.nextInt();

                //If the medical license number already exists then the below code is executed
                for (doctor i : doctorList) {
                    while (i.getMedLicenceNo() == medicalNo) {
                        System.out.println();
                        System.out.print("The entered medical license number is taken by another doctor in the system" +
                                "\nPlease enter a different medical license number: ");
                        medicalNo = input.nextInt();
                    }
                }

                //Getting the specialisation of the doctor from the manager
                System.out.print("Enter the specialisation of the doctor: ");
                String special = input.next();

                //Getting the Firstname of the doctor from the manager
                System.out.print("Enter the firstname of the doctor: ");
                String fName = input.next();

                //Getting the Surname of the doctor from the manager
                System.out.print("Enter the surname of the doctor: ");
                String sName = input.next();

                input.nextLine();

                //A loop to iterate till user enters a correct date
                boolean dateLoop = true;
                String format = null;
                //while true
                while (dateLoop) {
                    System.out.print("Enter the doctor's date of birth (Enter in this format DD/MM/YY): ");
                    String dobEntry = input.nextLine();
                    //Using the DateTimeFormatter class to specify the format of the date
                    DateTimeFormatter dateFormat = DateTimeFormatter.ofPattern("d/M/yyyy");

                    try {
                        //Formatting the string entered to the specified date format
                        format = dateFormat.format(dateFormat.parse(dobEntry));
                        //Loop stops
                        dateLoop = false;
                    }
                    //If the date entered by the user not in the correct format the below exception is caught,
                    //The loop repeats till a correct date format is entered.
                    catch (DateTimeParseException e) {
                        System.out.println("Invalid date!!!\nRetry!");
                        System.out.println("Please enter the date in this format (1/1/1111) to accept the date");
                    }
                }

                //Getting the mobile number of the doctor from the manager
                System.out.print("Enter doctors mobile phone number: ");
                int mobileNo = input.nextInt();
                //passing arguments (variables) to the addDoctor method
                manager.addDoctor(medicalNo, special, fName, sName, format, mobileNo);
                System.out.println("Doctor successfully added :)\nDon't Forget to save the information");
            }

            //If more than 10 doctors are added the below code is executed
            else {
                System.out.println();
                //Text block
                System.out.println("""
                        Maximum number of doctors reached!!!
                        Maximum of 10 doctors are allowed to be entered to the system
                        Please try again later after a doctor is deleted from the system""");
                System.out.println();
            }
        }
        //Catching the exception --> entering string to int variables
        catch (InputMismatchException e){
            System.out.println("Please only enter numbers for the doctor Medical License No and Mobile Number prompt and not any other values");
            System.out.println();
        }
        //catch any exception other than the above
        catch (Exception e){
            System.out.println("There is a error in the system when adding doctor");
        }
    }


    public void deleteDoctorSelection() {
        try {
            Scanner input = new Scanner(System.in);
            //if doctorList is not empty the below code is executed
            if (!doctorList.isEmpty()) {
                System.out.println("Medical license number of the doctors in the centre: ");
                //Print all med license numbers of doctors in the arrayList
                for (doctor j : doctorList) {
                    System.out.println(j.getMedLicenceNo());
                }

                System.out.println();
                //Get med license number input from user
                System.out.print("Enter the medical license number to delete that doctor: ");
                int deleteMedNo = input.nextInt();

                //Passing the med license number to the deleteDoctor method
                manager.deleteDoctor(deleteMedNo);
            }
            //else --> no doctors in the system
            else {
                System.out.println("No doctors are added to the system yet :(\nTherefore,no doctors available to be deleted");
                System.out.println();
            }
        }
        //Catching the exception --> entering string to int variables
        catch (InputMismatchException e){
            System.out.println("Enter only the medical license number of the doctor to the prompt and not other values");
            System.out.println();
        }
        //catch any exception other than the above
        catch (Exception e){
            System.out.println("There is a error in the system when deleting a doctor");
        }
    }

    public void printDoctorsSelection() {
        //if doctorList is not empty the below code is executed
        if (!doctorList.isEmpty()) {
            manager.printDoctors();
        }
        //else --> no doctors in the system
        else{
            System.out.println("No doctors are added to the system yet :(\nTherefore,no doctors available to be printed");
        }
    }

    public void saveDoctorsSelection() {
        //if doctorList is not empty the below code is executed
        if (!doctorList.isEmpty()) {
            manager.saveFile();
        }
        //else --> no doctors in the system
        else{
            System.out.println("No doctors are added to the system yet :(\nTherefore,no doctors available to be saved");
        }
    }

    public void savedInformation() {
        //Reading back the saved information from the file
        File file = new File("westminsterSkinConsultation.txt");
        if (file.canRead()) {
            try {
                Scanner read = new Scanner(file);
                while (read.hasNext()) {
                    System.out.println();
                    String id = read.next();
                    String special = read.next();
                    String fName = read.next();
                    String sName = read.next();
                    String dob = read.next();
                    String mobileNum = read.next();
                    int intId = Integer.parseInt(id);
                    int mobileNumInt = Integer.parseInt(mobileNum);
                    //Read the file and create doctor object
                    manager.addDoctor(intId, special, fName, sName, dob, mobileNumInt);
                }
                read.close();
            } catch (FileNotFoundException e) {
                System.out.println("The file to read the saved information is missing!!!\nAdd that file to read and store it to the system.");
            }
            catch (NoSuchElementException e){
                System.out.println("There is a doctor's information missing in the document\nTherefore please check the save file and remove or update that doctor's information");
            }
            catch (Exception e){
                System.out.println("There is some problem when reading back the information from the saved file");
            }
        }
    }


    //Over-ridden methods from the skinConsultationManager interface
    @Override
    public void addDoctor(int medicalNo, String special, String fName, String sName, String dob, int number) {
        //Creating doctor object from the arguments passed from the addDoctorSelection method
        doctor Doctor = new doctor(medicalNo, special, fName, sName, dob, number);
        //Add the object created to the arrayList
        doctorList.add(Doctor);
        //Increase the doctor count
        doctorCount++;
    }

    @Override
    public void deleteDoctor(int deleteMedNo) {
        //Deleting the doctor from the system
        boolean delete = false;

        for (int i = 0; i < doctorList.size(); i++) {
            int number = doctorList.get(i).getMedLicenceNo();

            //if the medLicense number entered by the user matches with the number --> any of th medLicense numbers in the arrayList
            if (number == deleteMedNo) {
                System.out.println();
                //Printing out the deleted doctor object information
                System.out.println("Deleted doctor information: ");
                System.out.println(doctorList.get(i).toString());
                doctorList.remove(i);
                //Doctor count reduces
                doctorCount = doctorCount - 1;
                System.out.println();
                System.out.println("The number of doctors that remain in the centre is " + doctorCount+"\nDon't Forget to save the information");
                //Checking whether something is deleted in the loop
                delete = true;
                break;
            }
        }
        //if nothing is deleted the program prints the below statement
        if (!delete) {
            System.out.println("The entered medical license number does not match with the numbers in the system!!!");
        }
    }

    @Override
    public void printDoctors() {

        //Printing out the arrayList of doctor object
        System.out.println("Sorted List of doctor information: ");
        //Calling the sortList method to sort the array
        manager.sortList();

        //Print all doctors
        for (int i = 0; i < doctorList.size(); i++) {
            System.out.println("Information of Doctor " + (i + 1) + " in the consultation");
            System.out.println(doctorList.get(i).toString());
            System.out.println();

        }

    }

    @Override
    public void saveFile() {
        try {
            //Save the information in the system to a file
            FileWriter file = new FileWriter("westminsterSkinConsultation.txt");
            for (doctor i : doctorList) {
                file.write(i.toString());
                file.write("\n\n");
            }
            file.close();
            System.out.println("Information is saved to the file successfully :)");
        } catch (IOException e) {
            System.out.println("The file to save all information is missing!!!");
        }

    }

}



