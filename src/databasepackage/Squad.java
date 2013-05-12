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
public class Squad implements Runnable
{
    
    private Connection con;
    private JTextField squadID;
    private JTextField squadName;
    private JTextArea mottoTo;
    private JTextArea mottoFrom;
    private JComboBox educatorList;
    private JComboBox leaderList;
    private JTable squadTable;
    private boolean addData;
    
    Squad(Connection _con,JTextField _squadID, JTextField _squadName, JTextArea _mottoTo, 
            JTextArea _mottoFrom, JComboBox _educatorList, 
            JComboBox _leaderList, JTable _squadTable, boolean _addData) 
    {
        con = _con;
        squadID = _squadID;
        squadName = _squadName;
        mottoTo = _mottoTo;
        mottoFrom = _mottoFrom;
        educatorList = _educatorList;
        leaderList = _leaderList;
        squadTable = _squadTable;
        addData = _addData;
    }
    @Override
    public void run() 
    {
        try
        {
            Statement statement = (Statement) con.createStatement();
            ResultSet rs;
            String query;
            squadTable.getSelectionModel().addListSelectionListener(new ListSelectionListener() 
            {
                @Override
                public void valueChanged(ListSelectionEvent e) 
                {
                    ResultSet rs;
                    String query;
                    String ID = String.valueOf(squadTable.getValueAt(squadTable.getSelectedRow(), 0));
                    try 
                    {
                        Statement statement = (Statement) con.createStatement();
                        query = "select motto from squad where ID = " + ID;
                        rs = statement.executeQuery(query);
                        while(rs.next())
                        {
                            mottoFrom.setText(rs.getString(1));
                        }
                    } 
                    catch (SQLException ex) 
                    {
                        Logger.getLogger(Event.class.getName()).log(Level.SEVERE, null, ex);
                    }
                }
            });
            if(addData)
            {
                int i = 0;
                String educatorID = "";
                String leaderID = "";
                while(((String)educatorList.getSelectedItem()).charAt(i) != '.' )
                {
                    educatorID = educatorID + ((String)educatorList.getSelectedItem()).charAt(i);
                    i++;
                }
                i = 0;
                while(((String)leaderList.getSelectedItem()).charAt(i) != '.' )
                {
                    leaderID = leaderID + ((String)leaderList.getSelectedItem()).charAt(i);
                    i++;
                }
                statement.executeUpdate("INSERT INTO squad (ID,name,motto,emploee_ID,leaderID)"
                        + "VALUES (" + squadID.getText() + ",'" + squadName.getText() + "','" 
                        + mottoTo.getText() + "'," + String.valueOf(educatorID) + 
                        "," + String.valueOf(leaderID) + ")");
                query = "select * from squad where ID = " + squadID.getText();
                rs = statement.executeQuery(query);
                i = squadTable.getRowCount();
                DefaultTableModel myModel = (DefaultTableModel) squadTable.getModel();
                myModel.addRow(new String[1]);
                while(rs.next())
                {
                    squadTable.setValueAt(rs.getInt(1), i, 0);
                    squadTable.setValueAt(rs.getString(2), i, 1);
                    squadTable.setValueAt(Employee.getEmployee(rs.getInt(4), con), i, 2);
                    squadTable.setValueAt(Employee.getEmployee(rs.getInt(5), con), i, 3);
                    i++;
                }
            }
            else
            {
                query = "select ID,Surname from employee where post = 'воспитатель' and ID NOT IN "
                        + "(select emploee_ID from squad)";
                rs = statement.executeQuery(query);
                while(rs.next())
                {
                    educatorList.addItem(rs.getInt(1)+"."+rs.getString(2));
                }
                query = "select ID,Surname from employee where post = 'вожатый' and ID NOT IN "
                        + "(select leaderID from squad)";
                rs = statement.executeQuery(query);
                while(rs.next())
                {
                    leaderList.addItem(rs.getInt(1)+"."+rs.getString(2));
                }
                query = "select * from squad";
                rs = statement.executeQuery(query);
                int i = 0;
                DefaultTableModel myModel = (DefaultTableModel) squadTable.getModel();
                while(rs.next())
                {
                    myModel.addRow(new String[0]);
                    squadTable.setValueAt(rs.getInt(1), i, 0);
                    squadTable.setValueAt(rs.getString(2), i, 1);
                    squadTable.setValueAt(Employee.getEmployee(rs.getInt(4), con), i, 2);
                    squadTable.setValueAt(Employee.getEmployee(rs.getInt(5), con), i, 3);
                    i++;
                }
            }
        }
        catch (SQLException ex)
        {
            Logger.getLogger(Inventory.class.getName()).log(Level.SEVERE, null, ex);
        }
    }
}
