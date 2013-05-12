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
import javax.swing.JTextField;
import javax.swing.table.DefaultTableModel;


public class Employee implements Runnable
{
    private Connection con;
    private JTable employeeTable;
    private boolean AddData;
    private static JTextField ID;
    private JTextField Surname;
    private JTextField Name;
    private JTextField Patronymic;
    private JTextField DOB;
    private JTextField Passport_series;
    private JTextField Passport_number;
    private JTextField Issuing_authority;
    private JTextField Date_of_issue;
    private JTextField Education;
    private JComboBox postList;
    private JTextField Wages;
    
    Employee(Connection connection,JTable emplTable, JTextField id, 
            JTextField surname, JTextField name,
            JTextField patronymic, JTextField dob, JTextField passport_series,
            JTextField passport_number, JTextField issuing_authority,
            JTextField date_of_issuing, JTextField education,
            JComboBox _postList, JTextField wages, boolean fl)
    {
        con = connection;
        employeeTable = emplTable;
        AddData = fl;
        ID = id;
        Surname = surname;
        Name = name;
        Patronymic = patronymic;
        DOB = dob;
        Passport_series = passport_series;
        Passport_number = passport_number;
        Issuing_authority = issuing_authority;
        Date_of_issue = date_of_issuing;
        Education = education;
        postList = _postList;
        Wages = wages;
    }
    @Override
    public void run() 
    {
        try 
        {
            Statement statement = (Statement) con.createStatement();
            ResultSet rs;
            int maxID = 0;
            if(AddData)
            {
                statement.executeUpdate("INSERT INTO employee (ID, surname, name, patronymic, DOB, passport_series, passport_number, issuing_authority, date_of_issue, education, post, wages) " + 
                "VALUES (" + ID.getText() + ", "
                           + " '" + Surname.getText() + "', " 
                           + " '" + Name.getText() + "', "
                           + " '" + Patronymic.getText() + "', "
                           + " '" + DOB.getText() + "', "
                           + " '" + Passport_series.getText() + "', "
                           + " '" + Passport_number.getText() + "', "
                           + " '" + Issuing_authority.getText() + "', "
                           + " '" + Date_of_issue.getText() + "', "
                           + " '" + Education.getText() + "', "
                           + " '" + (String)postList.getSelectedItem() + "', "
                           + Wages.getText() + ")");
                String query = "select * from employee having ID = " + ID.getText();
                rs = statement.executeQuery(query);
                int i = employeeTable.getRowCount();
                DefaultTableModel myModel = (DefaultTableModel) employeeTable.getModel();
                myModel.addRow(new String[1]);
                while (rs.next()) 
                {
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
                        if (rs.getInt(1) > maxID) 
                        {
                            maxID = rs.getInt(1);
                        }
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
                    ID.setText(String.valueOf(maxID+1));
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
    public static String getEmployee(int ID,Connection con)
    {
        try 
        {
            Statement statement = (Statement) con.createStatement();
            ResultSet rs;
            rs = statement.executeQuery("select Surname from employee where ID = " + String.valueOf(ID));
            if(rs.next())
            {
                return rs.getString(1);
            }
            else
            {
                return null;
            }
        } 
        catch (SQLException ex) 
        {
            Logger.getLogger(Employee.class.getName()).log(Level.SEVERE, null, ex);
        }
        return null;
    }
}
