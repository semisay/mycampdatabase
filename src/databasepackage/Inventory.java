package databasepackage;

import com.mysql.jdbc.Connection;
import com.mysql.jdbc.Statement;
import java.sql.ResultSet;
import java.sql.SQLException;
import javax.swing.JComboBox;
import javax.swing.JTable;
import javax.swing.JTextField;
import javax.swing.table.DefaultTableModel;

public class Inventory implements Runnable
{
    private Connection con;
    private JTextField Name;
    private JTextField Count;
    private JComboBox ResponsibleList;
    private JTable inventoryTable;
    private boolean addData;
    private static boolean error;
    private JTextField errorStatus;
    
    Inventory(Connection connect, JTextField name, JTextField count, JComboBox resp, JTable inventory, boolean add,JTextField _error)
    {
        con = connect;
        Name = name;
        Count = count;
        ResponsibleList = resp;
        inventoryTable = inventory;
        addData = add;
        error = false;
        errorStatus = _error;
    }
    @Override
    public void run() 
    {
        try
        {
            Statement statement = (Statement) con.createStatement();
            ResultSet rs;
            String query = "";
            if(addData)
            {
                int i = 0;
                String id = "";
                System.out.println(ResponsibleList.getSelectedItem());
                while(((String)ResponsibleList.getSelectedItem()).charAt(i) != '.' )
                {
                    id = id + ((String)ResponsibleList.getSelectedItem()).charAt(i);
                    i++;
                }
                if(Name.getText().length() == 0)
                {
                    throw new SQLException();
                }
                statement.executeUpdate("INSERT INTO inventory (name,count,employee_ID)"
                        + "VALUES ('" + Name.getText() + "'," + Count.getText() + "," + id+")");
                query = "select * from inventory having name = '" + Name.getText() + "'";
                rs = statement.executeQuery(query);
                i = inventoryTable.getRowCount();
                DefaultTableModel myModel = (DefaultTableModel) inventoryTable.getModel();
                myModel.addRow(new String[1]);
                while(rs.next())
                {
                    inventoryTable.setValueAt(rs.getString(1), i, 0);
                    inventoryTable.setValueAt(rs.getInt(2), i, 1);
                    inventoryTable.setValueAt(Employee.getEmployee(rs.getInt(3), con), i, 2);
                    i++;
                }
                if(!error) 
                {
                    errorStatus.setText("Data is successfully added");
                }
            }
            else
            {
                query = "select ID,Surname from employee";
                rs = statement.executeQuery(query);
                while(rs.next())
                {
                    ResponsibleList.addItem(rs.getInt(1)+"."+rs.getString(2));
                }
                query = "select * from inventory";
                rs = statement.executeQuery(query);
                int i = 0;
                DefaultTableModel myModel = (DefaultTableModel) inventoryTable.getModel();
                while(rs.next())
                {
                    myModel.addRow(new String[0]);
                    inventoryTable.setValueAt(rs.getString(1), i, 0);
                    inventoryTable.setValueAt(rs.getInt(2), i, 1);
                    inventoryTable.setValueAt(Employee.getEmployee(rs.getInt(3), con), i, 2);
                    i++;
                }
                if(!error) 
                {
                    errorStatus.setText("Data is recived");
                }
            }
        }
        catch (SQLException ex)
        {
            error = true;
            switch(ex.getErrorCode())
            {
                case 1064:
                {
                    errorStatus.setText("You have not entered all the data");
                    break;
                }
                case 1292:
                {
                    errorStatus.setText("Invalid date. Format date: yyyy-mm-dd");
                    break;
                }
                case 1054:
                {
                    errorStatus.setText("You entered in the numeric keypad char");
                    break;
                }
                case 1406:
                {
                    errorStatus.setText("You entered is too long a word");
                    break;
                }
                case 1062:
                {
                    errorStatus.setText("This record already exists");
                    break;
                }
                default:
                    errorStatus.setText("You have not entered all the data");
            }
        }
    }
    public static boolean getError()
    {
        return error;
    }
    public static void setError()
    {
        error = false;
    }     
}
