
public class consultation {

    //private variables declared

    //This variable is declared to identify with which doctor the patient has consulted.
    private int consDocLicenseNo;
    private String consultationDate;
    private int consultationTime;
    private int consultationCost;
    private String textNote;


    //constructor
    consultation(int consDocLicenseNo,String consultationDate,int consultationTime,int consultationCost,String textNote){
        setConsDocLicenseNo(consDocLicenseNo);
        setConsultationDate(consultationDate);
        setConsultationTime(consultationTime);
        setConsultationCost(consultationCost);
        setTextNote(textNote);
    }


    //Setter methods

    public void setConsDocLicenseNo(int consDocLicenseNo) {
        this.consDocLicenseNo = consDocLicenseNo;
    }

    public void setConsultationDate(String consultationDate) {
        this.consultationDate = consultationDate;
    }

    public void setConsultationTime(int consultationTime) {
        this.consultationTime = consultationTime;
    }

    public void setConsultationCost(int consultationCost) {
        this.consultationCost = consultationCost;
    }

    public void setTextNote(String textNote) {
        this.textNote = textNote;
    }


    //Getter methods

    public int getConsDocLicenseNo() {
        return consDocLicenseNo;
    }
    public String getConsultationDate() {
        return consultationDate;
    }

    public int getConsultationTime() {
        return consultationTime;
    }

    public int getConsultationCost() {
        return consultationCost;
    }

    public String getTextNote() {
        return textNote;
    }

    public String toString(){
        return this.getConsDocLicenseNo()
                + "\n"+this.getConsultationDate()
                + "\n"+ this.getConsultationTime()
                + "\n"+ this.getConsultationCost()
                + "\n"+ this.getTextNote();
    }


}
