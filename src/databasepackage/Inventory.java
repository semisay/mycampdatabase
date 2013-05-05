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

public class Inventory implements Runnable
{
    private Connection con;
    private JTextField Name;
    private JTextField Count;
    private JComboBox ResponsibleList;
    private JTextArea Responsible;
    private JTable inventoryTable;
    private boolean addDate;
    
    Inventory(Connection connect, JTextField name, JTextField count, JComboBox resp, JTextArea respfor, JTable inventory, boolean add)
    {
        con = connect;
        Name = name;
        Count = count;
        ResponsibleList = resp;
        Responsible = respfor;
        inventoryTable = inventory;
        addDate = add;
    }
    @Override
    public void run() 
    {
        inventoryTable.getSelectionModel().addListSelectionListener(new ListSelectionListener() {
            @Override
            public void valueChanged(ListSelectionEvent e) 
            {
                try 
                {
                    Statement statement = (Statement) con.createStatement();
                    ResultSet rs;
                    String query = "select surname,name,patronymic from employee where ID = "
                        + "(select emploee_ID from employee_has_inventory where inventory_name = '"
                        + inventoryTable.getValueAt(inventoryTable.getSelectedRows()[0], 0) + "')";
                    rs = statement.executeQuery(query);
                    while(rs.next())
                    {
                        Responsible.setText(rs.getString(1)+" "+rs.getString(2)+" "+rs.getString(3));
                    }
                    
                } 
                catch (SQLException ex) 
                {
                    Logger.getLogger(Inventory.class.getName()).log(Level.SEVERE, null, ex);
                }
            }
        });
        try
        {
            Statement statement = (Statement) con.createStatement();
            ResultSet rs;
            if(addDate)
            {
                statement.executeUpdate("INSERT INTO inventory (name,count)"
                        + "VALUES ('" + Name.getText() + "'," + Count.getText() + ")");
                String query = "select * from inventory having name = '" + Name.getText() + "'";
                rs = statement.executeQuery(query);
                int i = inventoryTable.getRowCount();
                DefaultTableModel myModel = (DefaultTableModel) inventoryTable.getModel();
                myModel.addRow(new String[1]);
                while(rs.next())
                {
                    inventoryTable.setValueAt(rs.getString(1), i, 0);
                    inventoryTable.setValueAt(rs.getInt(2), i, 1);
                    i++;
                }
                i = 0;
                String id = "";
                while(((String)ResponsibleList.getSelectedItem()).charAt(i) != '.' )
                {
                    id = id + ((String)ResponsibleList.getSelectedItem()).charAt(i);
                    i++;
                }
                statement.executeUpdate("INSERT INTO employee_has_inventory (emploee_ID,inventory_name)"
                        + "VALUES (" + id + ", '" + Name.getText() + "')");
                Name.setText("");
                Count.setText("");
            }
            else
            {
                String query = "select ID,Surname from employee";
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
