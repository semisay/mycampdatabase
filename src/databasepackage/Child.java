/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package databasepackage;

import com.mysql.jdbc.Statement;
import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.swing.JComboBox;
import javax.swing.JTable;
import javax.swing.JTextArea;
import javax.swing.JTextField;
import javax.swing.event.ListSelectionEvent;
import javax.swing.event.ListSelectionListener;
import javax.swing.table.DefaultTableModel;

/**
 *
 * @author Администратор
 */
public class Child implements Runnable
{
    private Connection con;
    private static boolean error;
    private JTextField childID;
    private JTextField childName;
    private JTextField childSurname;
    private JTextField childPatronymic;
    private JTextField childDOB;
    private JComboBox bloodGroupList;
    private JTextField childGrowth;
    private JTextField childWeight;
    private JComboBox medicalGroupList;
    private JComboBox squadList;
    private JTextArea addContraindication;
    private JTable childTable;
    private JTextArea contraindication;
    private JTextArea childInformation;
    private boolean AddData;
    private JTextField errorStatus;
    
    Child(Connection _con,JTextField _childID, JTextField _childName, JTextField _childSurname, 
            JTextField _childPatronymic, JTextField _childDOB, JComboBox _bloodGroupList, 
            JTextField _childGrowth, JTextField _childWeight, JComboBox _medicalGroupList, 
            JComboBox _squadList, JTextArea _addContraindication, JTable _childTable, 
            JTextArea _contraindication, JTextArea _childInformation, boolean _addData, 
            JTextField _errorStatus) 
    {
        con = _con;
        childID = _childID;
        childName = _childName;
        childSurname = _childSurname;
        childPatronymic = _childPatronymic;
        childDOB = _childDOB;
        bloodGroupList = _bloodGroupList;
        childGrowth = _childGrowth;
        childWeight = _childWeight;
        medicalGroupList = _medicalGroupList;
        squadList = _squadList;
        addContraindication = _addContraindication;
        childTable = _childTable;
        contraindication = _contraindication;
        childInformation = _childInformation;
        AddData = _addData;
        errorStatus = _errorStatus;
        error = false;
    }
    @Override
    public void run() 
    {
        try 
        {
            Statement statement = (Statement) con.createStatement();
            ResultSet rs;
            int maxID = 0;
            childTable.getSelectionModel().addListSelectionListener(new ListSelectionListener() 
            {
                @Override
                public void valueChanged(ListSelectionEvent e) 
                {
                    ResultSet rs;
                    String query;
                    String id = String.valueOf(childTable.getValueAt(childTable.getSelectedRow(), 0));
                    try 
                    {
                        Statement statement = (Statement) con.createStatement();
                        query = "select contraindications from child where id = " + id;
                        rs = statement.executeQuery(query);
                        while(rs.next())
                        {
                            contraindication.setText(rs.getString(1));
                        }
                        if (Parent.getParentCount(con,(Integer)childTable.getValueAt(childTable.getSelectedRow(), 0)) > 0) 
                        {
                            childInformation.setText("This child has " + 
                                    String.valueOf(Parent.getParentCount(con,(Integer)childTable.getValueAt(childTable.getSelectedRow(), 0)))
                                    + " parent(s):\n");
                            query = "select * from parent where ID in (" + 
                                    "select parent_ID from child_has_parent where child_ID = "
                                    + String.valueOf(childTable.getValueAt(childTable.getSelectedRow(), 0)) + ")";
                            rs = statement.executeQuery(query);
                            int i = 0;
                            while(rs.next())
                            {
                                childInformation.append(rs.getString(2) + " "
                                        + rs.getString(3) + " "
                                        + rs.getString(4) + "\nJob: "
                                        + rs.getString(5) + "\nTelephone: "
                                        + rs.getString(6) + "\n\n");
                                i++;
                            }

                        }
                        else
                        {
                            childInformation.setText("This child hasn't parent");
                        }
                    } 
                    catch (SQLException ex) 
                    {
                        Logger.getLogger(Event.class.getName()).log(Level.SEVERE, null, ex);
                    }
                }
            });
            if(AddData)
            {
                if( (childSurname.getText().length() == 0) ||
                    (childName.getText().length() == 0) ||
                    (childPatronymic.getText().length() == 0) ||
                    (addContraindication.getText().length() == 0))
                {
                    throw new SQLException();
                }
                statement.executeUpdate("INSERT INTO child (ID, surname, name, patronymic, DOB, blood_group,"
                        + " growth, weight, medical_group, contraindications, squad_ID) " + 
                "VALUES (" + childID.getText() + ", "
                           + " '" + childSurname.getText() + "', " 
                           + " '" + childName.getText() + "', "
                           + " '" + childPatronymic.getText() + "', "
                           + " '" + childDOB.getText() + "', "
                           + " '" + String.valueOf(bloodGroupList.getSelectedItem()) + "', "
                           + " "  + childGrowth.getText() + ", "
                           + " "  + childWeight.getText() + ", "
                           + " '" + String.valueOf(medicalGroupList.getSelectedItem()) + "', "
                           + " '" + addContraindication.getText() + "', "
                           + String.valueOf(squadList.getSelectedItem()) + ")");
                rs = statement.executeQuery("select * from child where ID = " + childID.getText());
                int i = childTable.getRowCount();
                DefaultTableModel myModel = (DefaultTableModel) childTable.getModel();
                myModel.addRow(new String[1]);
                while (rs.next()) 
                {
                    childTable.setValueAt(rs.getInt(1), i, 0);
                    childTable.setValueAt(rs.getString(2), i, 1);
                    childTable.setValueAt(rs.getString(3), i, 2);
                    childTable.setValueAt(rs.getString(4), i, 3);
                    childTable.setValueAt(rs.getDate(5), i, 4);
                    childTable.setValueAt(rs.getString(6), i, 5);
                    childTable.setValueAt(rs.getInt(7), i, 6);
                    childTable.setValueAt(rs.getDouble(8), i, 7);
                    childTable.setValueAt(rs.getString(9), i, 8);
                    childTable.setValueAt(rs.getInt(11), i, 9);
                    i++;
                }
                if(!error) 
                {
                    errorStatus.setText("Data is successfully added");
                }
            }
            else
            {
                String query = "select ID from squad";
                rs = statement.executeQuery(query);
                while(rs.next())
                {
                    squadList.addItem(rs.getInt(1));
                }
                query = "select * from child";
                rs = statement.executeQuery(query);
                try 
                {
                    int i = 0;
                    DefaultTableModel myModel = (DefaultTableModel) childTable.getModel();
                    while (rs.next()) 
                    {
                        myModel.addRow(new String[0]);
                        childTable.setValueAt(rs.getInt(1), i, 0);
                        if (rs.getInt(1) > maxID) 
                        {
                            maxID = rs.getInt(1);
                        }
                        childTable.setValueAt(rs.getString(2), i, 1);
                        childTable.setValueAt(rs.getString(3), i, 2);
                        childTable.setValueAt(rs.getString(4), i, 3);
                        childTable.setValueAt(rs.getDate(5), i, 4);
                        childTable.setValueAt(rs.getString(6), i, 5);
                        childTable.setValueAt(rs.getInt(7), i, 6);
                        childTable.setValueAt(rs.getDouble(8), i, 7);
                        childTable.setValueAt(rs.getString(9), i, 8);
                        childTable.setValueAt(rs.getInt(11), i, 9);
                        i++;
                    }
                    if(!error) 
                    {
                        childID.setText(String.valueOf(maxID+1));
                        errorStatus.setText("Data is recived");
                    }
                } 
                finally 
                {
                    rs.close();
                }
            }
        } 
        catch (SQLException ex) 
        {
            System.out.println(ex.getErrorCode());
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
