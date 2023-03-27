import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import java.util.ArrayList;
import java.util.InputMismatchException;

import static org.junit.jupiter.api.Assertions.*;

class westminsterSkinConsultationManagerTest {

    //Creating instance of westminsterSkinConsultationManager class
    westminsterSkinConsultationManager manager = new westminsterSkinConsultationManager();

    @BeforeEach
    void addDoctors(){
        //Calling the add doctor method to create objects and add the objects to the arraylist (getDoctorList)
        manager.addDoctor(123,"Eye-specialist","Dilukshan","Thurairaj","14/5/2003",1111111111);
        manager.addDoctor(124,"Skin","Kavin","Thurairaj","14/4/2007",767666666);
    }

    @Test
    void addingDoctorToSystem(){
        //Creating 2 new objects of doctor class
        doctor doctor1 = new doctor(123,"Eye-specialist","Dilukshan","Thurairaj","14/5/2003",1111111111);
        doctor doctor2 = new doctor(124,"Skin","Kavin","Thurairaj","14/4/2007",767666666);
        //Adding the two doctors to the arraylist
        ArrayList<doctor> docList = new ArrayList<>();
        docList.add(doctor1);
        docList.add(doctor2);


        addDoctors();

        //Looping twice to run through the arraylist
        for(int i = 0 ; i < 2; i++){
            //Checks whether the doctor objects in the both arraylist in westminsterSkinConsultationManager and westminsterSkinConsultationManagerTest has the same information.
            //This ensures that add doctor method in westminsterSkinConsultationManager class successfully creates objects and adds to the arraylist
            //Executes what is expected
            assertEquals(docList.get(i).toString(),manager.getDoctorList().get(i).toString());

        }
    }
}