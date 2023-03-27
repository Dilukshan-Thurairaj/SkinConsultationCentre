
public class person {
    //Super/Parent class of doctor and patient class

    //Private variables declared
    private String name;
    private String surname;
    private String dateOfBirth;
    private int mobileNo;

    //Constructor of person class
    person(String name, String surname, String dateOfBirth, int mobileNo){
        setName(name);
        setSurname(surname);
        setDateOfBirth(dateOfBirth);
        setMobileNo(mobileNo);
    }


    //Getters
    public String getName() {
        return name;
    }

    public String getSurname() {
        return surname;
    }

    public String getDateOfBirth() {
        return dateOfBirth;
    }

    public int getMobileNo() {
        return mobileNo;
    }

    //Setters
    public void setName(String name) {
        this.name = name;
    }

    public void setSurname(String surname) {
        this.surname = surname;
    }

    public void setDateOfBirth(String dateOfBirth) {
        this.dateOfBirth = dateOfBirth;
    }

    public void setMobileNo(int mobileNo) {
        this.mobileNo = mobileNo;
    }


    //Over-riding the toString method
    @Override
    public String toString(){
        return this.getName()
                + "\n"+ this.getSurname()
                + "\n"+ this.getDateOfBirth()
                + "\n"+ this.getMobileNo();
    }

}
