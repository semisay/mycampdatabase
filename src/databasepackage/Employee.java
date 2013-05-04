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
import javax.swing.table.DefaultTableModel;


public class Employee implements Runnable
{
    private Connection con;
    private JTable employeeTable;
    private JTable addEmployeeTable;
    private boolean AddDate;
    
    Employee(Connection connection,JTable emplTable, JTable addEmplTable, boolean fl)
    {
        con = connection;
        employeeTable = emplTable;
        AddDate = fl;
        addEmployeeTable = addEmplTable;
    }
    @Override
    public void run() 
    {
        try 
        {
            Statement statement = (Statement) con.createStatement();
            ResultSet rs;
            if(AddDate)
            {
                statement.executeUpdate("INSERT INTO employee (ID, surname, name, patronymic, DOB, passport_series, passport_number, issuing_authority, date_of_issue, education, post, wages) " + 
                "VALUES (" + String.valueOf(employeeTable.getRowCount() + 1)
                         + ", '" + (String)addEmployeeTable.getValueAt(0,0) + "', "
                         + " '" + (String)addEmployeeTable.getValueAt(0,1) + "', " 
                         + " '" + (String)addEmployeeTable.getValueAt(0,2) + "', "
                         + " '" + (String)addEmployeeTable.getValueAt(0,3) + "', "
                         + " '" + (String)addEmployeeTable.getValueAt(0,4) + "', "
                         + " '" + (String)addEmployeeTable.getValueAt(0,5) + "', "
                         + " '" + (String)addEmployeeTable.getValueAt(0,6) + "', "
                         + " '" + (String)addEmployeeTable.getValueAt(0,7) + "', "
                         + " '" + (String)addEmployeeTable.getValueAt(0,8) + "', "
                         + " '" + (String)addEmployeeTable.getValueAt(0,9) + "', "
                         + (String)addEmployeeTable.getValueAt(0,10) + ")");
                String query = "select * from employee having ID = " + String.valueOf(employeeTable.getRowCount()) + 1;
                rs = statement.executeQuery(query);
                int i = employeeTable.getRowCount();
                DefaultTableModel myModel = (DefaultTableModel) employeeTable.getModel();
                while (rs.next()) 
                {
                    myModel.addRow(new String[1]);
                    employeeTable.setValueAt(rs.getInt(1), i, 0);
                    employeeTable.setValueAt(rs.getString(2), i, 1);
                    employeeTable.setValueAt(rs.getString(3), i, 2);
                    employeeTable.setValueAt(rs.getString(4), i, 3);
                    employeeTable.setValueAt(rs.getDate(5), i, 4);
                    employeeTable.setValueAt(rs.getString(6), i, 5);
                    employeeTable.setValueAt(rs.getString(7), i, 6);
                    employeeTable.setValueAt(rs.getString(8), i, 7);
                    employeeTable.setValueAt(rs.getDate(9), i, 8);
                    employeeTable.setValueAt(rs.getString(10), i, 9);
                    employeeTable.setValueAt(rs.getString(11), i, 10);
                    employeeTable.setValueAt(rs.getInt(12), i, 11);
                    i++;
                }
            }
            else
            {
                String query = "select * from employee";
                rs = statement.executeQuery(query);
                try 
                {
                    int i = 0;
                    DefaultTableModel myModel = (DefaultTableModel) employeeTable.getModel();
                    while (rs.next()) 
                    {
                        myModel.addRow(new String[0]);
                        employeeTable.setValueAt(rs.getInt(1), i, 0);
                        employeeTable.setValueAt(rs.getString(2), i, 1);
                        employeeTable.setValueAt(rs.getString(3), i, 2);
                        employeeTable.setValueAt(rs.getString(4), i, 3);
                        employeeTable.setValueAt(rs.getDate(5), i, 4);
                        employeeTable.setValueAt(rs.getString(6), i, 5);
                        employeeTable.setValueAt(rs.getString(7), i, 6);
                        employeeTable.setValueAt(rs.getString(8), i, 7);
                        employeeTable.setValueAt(rs.getDate(9), i, 8);
                        employeeTable.setValueAt(rs.getString(10), i, 9);
                        employeeTable.setValueAt(rs.getString(11), i, 10);
                        employeeTable.setValueAt(rs.getInt(12), i, 11);
                        i++;
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
            Logger.getLogger(Employee.class.getName()).log(Level.SEVERE, null, ex);
        }
    }
}
