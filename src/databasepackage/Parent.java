/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package databasepackage;

import com.mysql.jdbc.Connection;
import com.mysql.jdbc.Statement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.logging.Level;
import java.util.logging.Logger;
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
public class Parent implements Runnable
{

    private Connection con;
    private JTextField addParentID;
    private JTextField addParentSurname;
    private JTextField addParentName;
    private JTextField addParentPatronymic;
    private JTextField addParentJob;
    private JTextField addParentTelephone;
    private JTextField addParentStatus;
    private JTable parentTable;
    private boolean AddData;
    private JTextField errorStatus;
    private static boolean error;
    private int child_id;
    private JTextArea childrenList;
    
    Parent(Connection _con, JTextField _addParentID, JTextField _addParentSurname, 
            JTextField _addParentName, JTextField _addParentPatronymic, 
            JTextField _addParentJob, JTextField _addParentTelephone, 
            JTextField _addParentStatus, JTable _parentTable ,boolean _b, 
            JTextField _errorStatus,int _child_id, JTextArea _childrenList) 
    {
        childrenList = _childrenList;
        child_id = _child_id;
        con = _con;
        addParentID = _addParentID;
        addParentSurname = _addParentSurname;
        addParentName = _addParentName;
        addParentPatronymic = _addParentPatronymic;
        addParentJob = _addParentJob;
        addParentTelephone = _addParentTelephone;
        addParentStatus = _addParentStatus;
        AddData = _b;
        errorStatus = _errorStatus;
        error = false;
        parentTable = _parentTable;
    }

    
    @Override
    public void run() 
    {
        try
        {
            parentTable.getSelectionModel().addListSelectionListener(new ListSelectionListener() 
            {
                @Override
                public void valueChanged(ListSelectionEvent e) 
                {
                    ResultSet rs;
                    String query;
                    String id = String.valueOf(parentTable.getValueAt(parentTable.getSelectedRow(), 0));
                    try 
                    {
                        Statement statement = (Statement) con.createStatement();
                        query = "select ID,surname,name,patronymic from child where ID in "
                                + "(select child_ID from child_has_parent where parent_ID = " + id + ")";
                        rs = statement.executeQuery(query);
                        childrenList.setText("");
                        while(rs.next())
                        {
                            childrenList.append(String.valueOf(rs.getInt(1)) + ". "
                                    + rs.getString(2) + " "
                                    + rs.getString(3) + " "
                                    + rs.getString(4) + "\n");
                        }
                    } 
                    catch (SQLException ex) 
                    {
                        Logger.getLogger(Event.class.getName()).log(Level.SEVERE, null, ex);
                    }
                }
            });
            Statement statement = (Statement) con.createStatement();
            ResultSet rs;
            int maxID = 0;
            String query;
            if(AddData)
            {
                if( (addParentSurname.getText().length() == 0) ||
                    (addParentName.getText().length() == 0) ||
                    (addParentPatronymic.getText().length() == 0) ||
                    (addParentJob.getText().length() == 0) ||
                    (addParentTelephone.getText().length() == 0))
                {
                    throw new SQLException();
                }
                statement.executeUpdate("INSERT INTO parent (ID,surname,name,patronymic,job,telephone)" +
                        "VALUES (" + addParentID.getText() + ", "
                           + " '" + addParentSurname.getText() + "', " 
                           + " '" + addParentName.getText() + "', "
                           + " '" + addParentPatronymic.getText() + "', "
                           + " '" + addParentJob.getText() + "', "
                           + " '" + addParentTelephone.getText() + "'" + ")");
                query = "select * from parent where ID = " + addParentID.getText();
                rs = statement.executeQuery(query);
                int i = parentTable.getRowCount();
                DefaultTableModel myModel = (DefaultTableModel) parentTable.getModel();
                myModel.addRow(new String[0]);
                while(rs.next())
                {
                    parentTable.setValueAt(rs.getInt(1), i, 0);
                    parentTable.setValueAt(rs.getString(2), i, 1);
                    parentTable.setValueAt(rs.getString(3), i, 2);
                    parentTable.setValueAt(rs.getString(4), i, 3);
                    parentTable.setValueAt(rs.getString(5), i, 4);
                    parentTable.setValueAt(rs.getString(6), i, 5);
                    i++;
                }
                if(!error) 
                {
                    errorStatus.setText("Data is successfully added");
                    statement.executeUpdate("insert into child_has_parent "
                            + "(child_ID,parent_ID)"
                            + "values('" + String.valueOf(child_id) + "','"
                            + addParentID.getText() + "')");
                }
            }
            else
            {
                query = "select * from parent";
                rs = statement.executeQuery(query);
                int i = 0;
                DefaultTableModel myModel = (DefaultTableModel) parentTable.getModel();
                while(rs.next())
                {
                    myModel.addRow(new String[0]);
                    parentTable.setValueAt(rs.getInt(1), i, 0);
                    if (rs.getInt(1) > maxID) 
                    {
                            maxID = rs.getInt(1);
                    }
                    parentTable.setValueAt(rs.getString(2), i, 1);
                    parentTable.setValueAt(rs.getString(3), i, 2);
                    parentTable.setValueAt(rs.getString(4), i, 3);
                    parentTable.setValueAt(rs.getString(5), i, 4);
                    parentTable.setValueAt(rs.getString(6), i, 5);
                    i++;
                }
                if(!error) 
                {
                    addParentID.setText(String.valueOf(maxID+1));
                    errorStatus.setText("Data is recived");
                }
            }
        }
        catch (SQLException ex)
        {
            error = true;
            System.out.println(ex.getMessage());
            switch(ex.getErrorCode())
            {
                case 1064:
                {
                    addParentStatus.setText("You have not entered all the data");
                    break;
                }
                case 1292:
                {
                    addParentStatus.setText("Invalid date. Format date: yyyy-mm-dd");
                    break;
                }
                case 1054:
                {
                    addParentStatus.setText("You entered in the numeric keypad char");
                    break;
                }
                case 1406:
                {
                    addParentStatus.setText("You entered is too long a word");
                    break;
                }
                case 1062:
                {
                    addParentStatus.setText("This record already exists");
                    break;
                }
                default:
                    addParentStatus.setText("You have not entered all the data");
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
    public static int getParentCount(java.sql.Connection con, Integer integer) 
    {
        try 
        {
            Statement statement = (Statement) con.createStatement();
            ResultSet rs = statement.executeQuery("select COUNT(*) from child_has_parent "
                    + "where child_ID =" + String.valueOf(integer));
            if(rs.next()) 
            {
                return rs.getInt(1);
            }
            else 
            {
                return 0;
            }
        } 
        catch (SQLException ex) 
        {
            Logger.getLogger(Parent.class.getName()).log(Level.SEVERE, null, ex);
        }
        return 0;
    }
}
