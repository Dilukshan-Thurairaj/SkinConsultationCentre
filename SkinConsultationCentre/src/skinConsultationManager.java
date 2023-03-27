
public interface skinConsultationManager {


    //The methods in the interface
    void addDoctor(int medicalNo,String special,String fName,String sName,String format,int mobileNo);

    void deleteDoctor(int deleteMedNo);

    void printDoctors();

    void saveFile();

}
