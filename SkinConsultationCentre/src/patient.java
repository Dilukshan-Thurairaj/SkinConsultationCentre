public class patient extends person{
    //inherits the person class
    //Child class

    //private variable to store  a patient's unique id
    private String patientId;

    //Constructor of patient class
    patient (String patientId,String name, String surname, String dateOfBirth, int mobileNo){
        //The parent class constructor is called
        super(name,surname,dateOfBirth,mobileNo);
        setPatientId(patientId);
    }

    //Setter
    public void setPatientId(String patientId) {
        this.patientId = patientId;
    }

    //Getter
    public String getPatientId() {
        return patientId;
    }

    //Over-riding the toString method
    @Override
    public String toString(){
        return this.getPatientId()
                //Calling the parent class's toString method
                + "\n" + super.toString();
    }
}
