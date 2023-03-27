import javax.swing.table.AbstractTableModel;
import java.util.ArrayList;

public class JFrame_doctorTable extends AbstractTableModel {
    //extends the abstractTableModel class
    //Referred lecture slides
    private final String[] columnNames = {"Med-License No","Specialization","Firstname","Surname","Date of Birth","Mobile No"};
    private final ArrayList<doctor> tableList;

    //Constructor
    JFrame_doctorTable(ArrayList<doctor> doctorList){
        this.tableList = doctorList;
    }


    //Size of list is the number of rows
    @Override
    public int getRowCount() {
        return tableList.size();
    }

    //Length of columnNames array is the number of columns
    @Override
    public int getColumnCount() {
        return columnNames.length;
    }


    //Method to get the value at a specific row and column
    @Override
    public Object getValueAt(int rowIndex, int columnIndex) {
        Object temp = null;

        if (columnIndex == 0){
            temp = tableList.get(rowIndex).getMedLicenceNo();
        }
        else if (columnIndex == 1){
            temp = tableList.get(rowIndex).getSpecial();
        }
        else if (columnIndex == 2){
            temp = tableList.get(rowIndex).getName();
        }
        else if (columnIndex == 3){
            temp = tableList.get(rowIndex).getSurname();
        }
        else if (columnIndex == 4){
            temp = tableList.get(rowIndex).getDateOfBirth();
        }
        else if (columnIndex == 5){
            temp = tableList.get(rowIndex).getMobileNo();
        }
        return temp;
    }

    //Get column names
    @Override
    public String getColumnName(int col){
        return columnNames[col];
    }

    @Override
    public Class getColumnClass ( int col){
        if (col == 0){
            return Integer.class;
        }
        else if(col == 5){
            return Integer.class;
        }
        else {
            return String.class;
        }
    }

}
