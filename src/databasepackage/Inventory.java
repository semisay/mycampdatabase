package databasepackage;

import com.mysql.jdbc.Connection;
import com.mysql.jdbc.Statement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.logging.Level;
import java.util.logging.Logger;
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
    private boolean addDate;
    
    Inventory(Connection connect, JTextField name, JTextField count, JComboBox resp, JTable inventory, boolean add)
    {
        con = connect;
        Name = name;
        Count = count;
        ResponsibleList = resp;
        inventoryTable = inventory;
        addDate = add;
    }
    @Override
    public void run() 
    {
        try
        {
            Statement statement = (Statement) con.createStatement();
            ResultSet rs;
            String query = "";
            if(addDate)
            {
                int i = 0;
                String id = "";
                System.out.println(ResponsibleList.getSelectedItem());
                while(((String)ResponsibleList.getSelectedItem()).charAt(i) != '.' )
                {
                    id = id + ((String)ResponsibleList.getSelectedItem()).charAt(i);
                    i++;
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
                    inventoryTable.setValueAt(rs.getInt(3), i, 2);
                    i++;
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
                    inventoryTable.setValueAt(rs.getInt(3), i, 2);
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
