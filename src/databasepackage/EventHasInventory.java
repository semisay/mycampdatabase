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
import javax.swing.DefaultListModel;
import javax.swing.JTextArea;

/**
 *
 * @author Администратор
 */
public class EventHasInventory implements Runnable
{
    private Connection con;
    private JTextArea inventoryForEvent;
    private DefaultListModel listModel;
    private String name;
    private static boolean flag;
    
    EventHasInventory(Connection _con, JTextArea _inventoryForEvent, DefaultListModel _listModel,String _name) 
    {
        con = _con;
        inventoryForEvent = _inventoryForEvent;
        listModel = _listModel;
        name = _name;
        flag = false;
    }
    @Override
    public void run() 
    {
        try 
        {
            Statement statement = (Statement) con.createStatement();
            ResultSet rs;
            String query;
            int n = listModel.getSize();
            for (int i = 0; i < n; i++)
            {
                query = "select * from inventory_has_event where event_name = '" + name + "' and "
                        + "inventory_name = '" + (String)listModel.getElementAt(i) + "'";
                rs = statement.executeQuery(query);
                if(rs.next())
                {
                    flag = true;
                    continue;
                }
                else
                {
                     statement.executeUpdate("INSERT INTO inventory_has_event "
                             + "(inventory_name,event_name)"
                             + "VALUES ('" + (String)listModel.getElementAt(i) + "','"
                             + name + "')");
                }
            }
            listModel.removeAllElements();
            inventoryForEvent.setText("");
            query = "select inventory_name from inventory_has_event where event_name = '" + name + "'";
            rs = statement.executeQuery(query);
            while(rs.next())
            {
                inventoryForEvent.append(rs.getString(1)+"\n");
            }
        } 
        catch (SQLException ex) 
        {
            Logger.getLogger(EventHasInventory.class.getName()).log(Level.SEVERE, null, ex);
        }
    }
    public static boolean getFlag()
    {
        return flag;
    }
}
