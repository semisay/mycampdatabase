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
import javax.swing.JButton;
import javax.swing.JComboBox;
import javax.swing.JList;
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
public class Event implements Runnable 
{
    private Connection con;
    private JTextField Name;
    private JTextField Type;
    private JTextField Date;
    private JTextField Duration;
    private JComboBox ResponsibleForEvent;
    private JTable eventTable;
    private JTextArea Gist;
    private JTextArea addGist;
    private JComboBox listOfInventoryForEvent;
    private JButton selectInventory;
    private JButton deleteInventory;
    private JList listOfSelectedInventory;
    private JButton addInventoryForEvent;
    private boolean addDate;
    
    Event(Connection _con,JTextField _Name, JTextField _Type, JTextField _Date,
            JTextField _Duration, JComboBox _ResponsibleForEvent,
            JTable _eventTable, JTextArea _Gist, JTextArea _addGist, JComboBox _listOfInventoryForEvent, 
            JButton _selectInventory, JButton _deleteInventory, JList _listOfSelectedInventory, 
            JButton _addInventoryForEvent,boolean _addDate)
    {
        con = _con;
        Name = _Name;
        Type = _Type;
        Date = _Date;
        Duration = _Duration;
        ResponsibleForEvent = _ResponsibleForEvent;
        eventTable = _eventTable;
        Gist = _Gist;
        addGist = _addGist;
        addDate = _addDate;
        listOfInventoryForEvent = _listOfInventoryForEvent;
        selectInventory = _selectInventory;
        deleteInventory = _deleteInventory;
        listOfSelectedInventory = _listOfSelectedInventory;
        addInventoryForEvent = _addInventoryForEvent;
    }
    @Override
    public void run() 
    {
        try
        {
            Statement statement = (Statement) con.createStatement();
            ResultSet rs;
            String query = "";
            eventTable.getSelectionModel().addListSelectionListener(new ListSelectionListener() 
            {
                @Override
                public void valueChanged(ListSelectionEvent e) 
                {
                    ResultSet rs;
                    String query;
                    String name = (String)eventTable.getValueAt(eventTable.getSelectedRow(), 0);
                    try 
                    {
                        Statement statement = (Statement) con.createStatement();
                        query = "select gist from event where name = '" + name + "'";
                        rs = statement.executeQuery(query);
                        while(rs.next())
                        {
                            Gist.setText(rs.getString(1));
                        }
                    } 
                    catch (SQLException ex) 
                    {
                        Logger.getLogger(Event.class.getName()).log(Level.SEVERE, null, ex);
                    }
                }
            });
            if(addDate)
            {
                int i = 0;
                String id = "";
                while(((String)ResponsibleForEvent.getSelectedItem()).charAt(i) != '.' )
                {
                    id = id + ((String)ResponsibleForEvent.getSelectedItem()).charAt(i);
                    i++;
                }
                statement.executeUpdate("INSERT INTO event (name,type,date,duration,gist,emploee_ID)"
                        + "VALUES ('" + Name.getText() + "','" + Type.getText() +
                        "','" + Date.getText() + "'," + Duration.getText() + ",'" +
                        addGist.getText() + "'," + id+")");
                query = "select * from event having name = '" + Name.getText() + "'";
                rs = statement.executeQuery(query);
                i = eventTable.getRowCount();
                DefaultTableModel myModel = (DefaultTableModel) eventTable.getModel();
                myModel.addRow(new String[0]);
                while(rs.next())
                {
                    eventTable.setValueAt(rs.getString(1), i, 0);
                    eventTable.setValueAt(rs.getString(2), i, 1);
                    eventTable.setValueAt(rs.getDate(3), i, 2);
                    eventTable.setValueAt(rs.getInt(4), i, 3);
                    eventTable.setValueAt(rs.getString(6), i, 4);
                    i++;
                }
            }
            else
            {
                query = "select ID,Surname from employee";
                rs = statement.executeQuery(query);
                while(rs.next())
                {
                    ResponsibleForEvent.addItem(rs.getInt(1)+"."+rs.getString(2));
                }
                query = "select * from event";
                rs = statement.executeQuery(query);
                int i = 0;
                DefaultTableModel myModel = (DefaultTableModel) eventTable.getModel();
                while(rs.next())
                {
                    myModel.addRow(new String[0]);
                    eventTable.setValueAt(rs.getString(1), i, 0);
                    eventTable.setValueAt(rs.getString(2), i, 1);
                    eventTable.setValueAt(rs.getDate(3), i, 2);
                    eventTable.setValueAt(rs.getInt(4), i, 3);
                    eventTable.setValueAt(rs.getString(6), i, 4);
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
