public class doctor extends person{
    //inherits person class
    //Child class

    //private variable to store doctor's medical licence number
     private int medLicenceNo;

     //private variable to store doctor's specialisation
     private String special;




    //Constructor
    doctor(int medLicenceNo,String special,String fName,String sName,String dob,int mobileNo){
        //Superclass constructor called
        super(fName,sName,dob,mobileNo);
        setMedLicenceNo(medLicenceNo);
        setSpecial(special);
    }


    //Setter methods
    public void setMedLicenceNo(int medLicenceNo){
        this.medLicenceNo = medLicenceNo;
    }

    public void setSpecial(String special){
        this.special = special;
    }




    //Getter methods
    public int getMedLicenceNo(){
        return medLicenceNo;
    }

    public String getSpecial(){
        return special;
    }

    //Over-riding toString method
    @Override
    public String toString(){
        return  this.getMedLicenceNo()
                + "\n" + this.getSpecial()
                //Calling the parent class toString method
                + "\n" + super.toString();
    }



}
