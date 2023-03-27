import java.util.Comparator;

public class myDoctorComparator implements Comparator<doctor> {
    //implementing Comparator interface

    //Overriding method
    @Override
    public int compare(doctor d1, doctor d2) {
        //compareTo method used to compare the string "surname" and return what's greater to sort the arrayList
        return d1.getSurname().compareToIgnoreCase(d2.getSurname());
    }
}
