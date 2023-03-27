import java.util.Scanner;

public class JFrame_mainFrame {
    public static void main(String[] args) {

        //Welcoming the customer
        System.out.println("Welcome to Westminster Skin Consultation");
        System.out.println("Welcome Customer :)");
        System.out.println();
        System.out.println("Enter '1' to open the Westminster Skin Consultation GUI\nHere you can book your consultations");
        System.out.print("Enter: ");

        Scanner input = new Scanner(System.in);

        int entry = input.nextInt();
        //If number 1 entered open the GUI
        if (entry == 1) {
            new JFrame_menuFrame();
        }
        //else --> retry
        else{
            System.out.println("Please enter 1 to open the GUI\nPlease Retry");
        }
    }
}

