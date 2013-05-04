/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package databasepackage;

import com.mysql.jdbc.Connection;
import java.awt.Dimension;
import java.awt.event.KeyEvent;
import java.awt.event.KeyListener;
import java.sql.DriverManager;
import java.sql.SQLException;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.swing.CellEditor;
import javax.swing.event.CellEditorListener;
import javax.swing.event.ChangeEvent;
import javax.swing.table.TableCellEditor;

/**
 *
 * @author Администратор
 */
public class Client extends javax.swing.JFrame {

    /**
     * Creates new form Client
     */
    private String logger;
    private static String url = "jdbc:mysql://localhost/mycamp";
    private static String name = "root";
    private static String password = "lager";
    private Connection con;
    
    public Client() 
    {
        initComponents();
        Dimension screenSize = java.awt.Toolkit.getDefaultToolkit().getScreenSize();
        int x = (int) ((screenSize.getWidth() - this.getWidth()) / 2);
        if (x < 0) {
            x = 0;
        }
        int y = (int) ((screenSize.getHeight() - this.getHeight()) / 2);
        if (y < 0) {
            y = 0;
        }
        this.setBounds(x, y, this.getWidth(), this.getHeight());
        getConnection();
        /*TableCellEditor cell = addEmployeeTable.getCellEditor();
        cell.addCellEditorListener(new CellEditorListener()
        {
            @Override
            public void editingStopped(ChangeEvent e) 
            {
                System.out.println("Stop");
            }
            @Override
            public void editingCanceled(ChangeEvent e) 
            {
                System.out.println("Cancel");
            }
        });
        addEmployeeTable.setCellEditor(cell);*/
    }
    private void getConnection()
    {
        logger = "";
        try 
        {
            Class.forName("com.mysql.jdbc.Driver");
            logger = logger + new java.util.Date().toString() + ":\n";
            logger = logger + "Driver loading success!\n";
            try 
            {
                con = (Connection) DriverManager.getConnection(url, name, password);
                logger = logger + new java.util.Date().toString() + ":\n";
                logger = logger + "Connected\n";
                Thread employee = new Thread(new Employee(con,employeeTable,addEmployeeTable,false));
                employee.start();
                try 
                {
                    employee.join();
                } 
                catch (InterruptedException ex) 
                {
                    Logger.getLogger(Client.class.getName()).log(Level.SEVERE, null, ex);
                }
                logger = logger + new java.util.Date().toString() + ":\n";
                logger = logger + "Disconnected\n";
            } 
            catch (SQLException e) 
            {
                logger = logger + new java.util.Date().toString() + ":\n";
                logger = logger + e.getMessage() + "\n";
            }
        } 
        catch (ClassNotFoundException e) 
        {
            logger = logger + new java.util.Date().toString() + ":\n";
            logger = logger + e.getMessage() + "\n";
        }
        log.setText(logger);
    }

    /**
     * This method is called from within the constructor to initialize the form.
     * WARNING: Do NOT modify this code. The content of this method is always
     * regenerated by the Form Editor.
     */
    @SuppressWarnings("unchecked")
    // <editor-fold defaultstate="collapsed" desc="Generated Code">//GEN-BEGIN:initComponents
    private void initComponents() {

        mainTabbedPane = new javax.swing.JTabbedPane();
        employeeTab = new javax.swing.JPanel();
        addEmployee = new javax.swing.JButton();
        employeeScrollPane = new javax.swing.JScrollPane();
        employeeTable = new javax.swing.JTable();
        updateEmployee = new javax.swing.JButton();
        addEmployeeScrollPane = new javax.swing.JScrollPane();
        addEmployeeTable = new javax.swing.JTable();
        inventoryTab = new javax.swing.JPanel();
        eventTab = new javax.swing.JPanel();
        squadTab = new javax.swing.JPanel();
        childTab = new javax.swing.JPanel();
        parentTab = new javax.swing.JPanel();
        orphanageTab = new javax.swing.JPanel();
        logTab = new javax.swing.JPanel();
        logScrollPane = new javax.swing.JScrollPane();
        log = new javax.swing.JTextArea();

        setDefaultCloseOperation(javax.swing.WindowConstants.EXIT_ON_CLOSE);
        setTitle("myCamp");

        addEmployee.setText("addEmployee");
        addEmployee.setEnabled(false);
        addEmployee.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                addEmployeeActionPerformed(evt);
            }
        });

        employeeTable.setModel(new javax.swing.table.DefaultTableModel(
            new Object [][] {

            },
            new String [] {
                "ID", "Surname", "Name", "Patronymic", "DOB", "Passport series", "Passport number", "Issuing authority", "Date of issue", "Education", "Post", "Wages"
            }
        ) {
            boolean[] canEdit = new boolean [] {
                false, false, false, false, false, false, false, false, false, false, false, false
            };

            public boolean isCellEditable(int rowIndex, int columnIndex) {
                return canEdit [columnIndex];
            }
        });
        employeeScrollPane.setViewportView(employeeTable);

        updateEmployee.setText("Update");
        updateEmployee.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                updateEmployeeActionPerformed(evt);
            }
        });

        addEmployeeTable.setModel(new javax.swing.table.DefaultTableModel(
            new Object [][] {
                {null, null, null, null, null, null, null, null, null, null, null}
            },
            new String [] {
                "Surname", "Name", "Patronymic", "DOB", "Passport Series", "Passport Number", "Issuing Authority", "Date of Issue", "Education", "Post", "Wages"
            }
        ));
        addEmployeeScrollPane.setViewportView(addEmployeeTable);

        javax.swing.GroupLayout employeeTabLayout = new javax.swing.GroupLayout(employeeTab);
        employeeTab.setLayout(employeeTabLayout);
        employeeTabLayout.setHorizontalGroup(
            employeeTabLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addComponent(employeeScrollPane, javax.swing.GroupLayout.DEFAULT_SIZE, 1026, Short.MAX_VALUE)
            .addGroup(employeeTabLayout.createSequentialGroup()
                .addGap(0, 0, Short.MAX_VALUE)
                .addComponent(updateEmployee)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                .addComponent(addEmployee))
            .addComponent(addEmployeeScrollPane)
        );
        employeeTabLayout.setVerticalGroup(
            employeeTabLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(employeeTabLayout.createSequentialGroup()
                .addComponent(addEmployeeScrollPane, javax.swing.GroupLayout.PREFERRED_SIZE, 43, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                .addGroup(employeeTabLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(addEmployee)
                    .addComponent(updateEmployee))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(employeeScrollPane))
        );

        mainTabbedPane.addTab("Employee", employeeTab);

        javax.swing.GroupLayout inventoryTabLayout = new javax.swing.GroupLayout(inventoryTab);
        inventoryTab.setLayout(inventoryTabLayout);
        inventoryTabLayout.setHorizontalGroup(
            inventoryTabLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGap(0, 1026, Short.MAX_VALUE)
        );
        inventoryTabLayout.setVerticalGroup(
            inventoryTabLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGap(0, 510, Short.MAX_VALUE)
        );

        mainTabbedPane.addTab("Inventory", inventoryTab);

        javax.swing.GroupLayout eventTabLayout = new javax.swing.GroupLayout(eventTab);
        eventTab.setLayout(eventTabLayout);
        eventTabLayout.setHorizontalGroup(
            eventTabLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGap(0, 1026, Short.MAX_VALUE)
        );
        eventTabLayout.setVerticalGroup(
            eventTabLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGap(0, 510, Short.MAX_VALUE)
        );

        mainTabbedPane.addTab("Event", eventTab);

        javax.swing.GroupLayout squadTabLayout = new javax.swing.GroupLayout(squadTab);
        squadTab.setLayout(squadTabLayout);
        squadTabLayout.setHorizontalGroup(
            squadTabLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGap(0, 1026, Short.MAX_VALUE)
        );
        squadTabLayout.setVerticalGroup(
            squadTabLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGap(0, 510, Short.MAX_VALUE)
        );

        mainTabbedPane.addTab("Squad", squadTab);

        javax.swing.GroupLayout childTabLayout = new javax.swing.GroupLayout(childTab);
        childTab.setLayout(childTabLayout);
        childTabLayout.setHorizontalGroup(
            childTabLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGap(0, 1026, Short.MAX_VALUE)
        );
        childTabLayout.setVerticalGroup(
            childTabLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGap(0, 510, Short.MAX_VALUE)
        );

        mainTabbedPane.addTab("Child", childTab);

        javax.swing.GroupLayout parentTabLayout = new javax.swing.GroupLayout(parentTab);
        parentTab.setLayout(parentTabLayout);
        parentTabLayout.setHorizontalGroup(
            parentTabLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGap(0, 1026, Short.MAX_VALUE)
        );
        parentTabLayout.setVerticalGroup(
            parentTabLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGap(0, 510, Short.MAX_VALUE)
        );

        mainTabbedPane.addTab("Parent", parentTab);

        javax.swing.GroupLayout orphanageTabLayout = new javax.swing.GroupLayout(orphanageTab);
        orphanageTab.setLayout(orphanageTabLayout);
        orphanageTabLayout.setHorizontalGroup(
            orphanageTabLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGap(0, 1026, Short.MAX_VALUE)
        );
        orphanageTabLayout.setVerticalGroup(
            orphanageTabLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGap(0, 510, Short.MAX_VALUE)
        );

        mainTabbedPane.addTab("Orphanage", orphanageTab);

        log.setEditable(false);
        log.setColumns(20);
        log.setRows(5);
        logScrollPane.setViewportView(log);

        javax.swing.GroupLayout logTabLayout = new javax.swing.GroupLayout(logTab);
        logTab.setLayout(logTabLayout);
        logTabLayout.setHorizontalGroup(
            logTabLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addComponent(logScrollPane, javax.swing.GroupLayout.DEFAULT_SIZE, 1026, Short.MAX_VALUE)
        );
        logTabLayout.setVerticalGroup(
            logTabLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addComponent(logScrollPane, javax.swing.GroupLayout.DEFAULT_SIZE, 510, Short.MAX_VALUE)
        );

        mainTabbedPane.addTab("Log", logTab);

        javax.swing.GroupLayout layout = new javax.swing.GroupLayout(getContentPane());
        getContentPane().setLayout(layout);
        layout.setHorizontalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addComponent(mainTabbedPane)
        );
        layout.setVerticalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addComponent(mainTabbedPane)
        );

        pack();
    }// </editor-fold>//GEN-END:initComponents

    private void addEmployeeActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_addEmployeeActionPerformed
        
        
        Thread employee = new Thread(new Employee(con,employeeTable,addEmployeeTable,true));
        employee.start();
        try 
        {
            employee.join();
        } 
        catch (InterruptedException ex) 
        {
            Logger.getLogger(Client.class.getName()).log(Level.SEVERE, null, ex);
        }
        for(int i = 0; i < 11; i++)
        {
            addEmployeeTable.setValueAt("", 0, i);
        }
        
    }//GEN-LAST:event_addEmployeeActionPerformed

    private void updateEmployeeActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_updateEmployeeActionPerformed
        
        Thread employee = new Thread(new Employee(con,employeeTable,addEmployeeTable,false));
        employee.start();
        try 
        {
            employee.join();
        } 
        catch (InterruptedException ex) 
        {
            Logger.getLogger(Client.class.getName()).log(Level.SEVERE, null, ex);
        }       // TODO add your handling code here:
    }//GEN-LAST:event_updateEmployeeActionPerformed

    /**
     * @param args the command line arguments
     */
    public static void main(String args[]) {
        /* Set the Nimbus look and feel */
        //<editor-fold defaultstate="collapsed" desc=" Look and feel setting code (optional) ">
        /* If Nimbus (introduced in Java SE 6) is not available, stay with the default look and feel.
         * For details see http://download.oracle.com/javase/tutorial/uiswing/lookandfeel/plaf.html 
         */
        try {
            for (javax.swing.UIManager.LookAndFeelInfo info : javax.swing.UIManager.getInstalledLookAndFeels()) {
                if ("Nimbus".equals(info.getName())) {
                    javax.swing.UIManager.setLookAndFeel(info.getClassName());
                    break;
                }
            }
        } catch (ClassNotFoundException ex) {
            java.util.logging.Logger.getLogger(Client.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (InstantiationException ex) {
            java.util.logging.Logger.getLogger(Client.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (IllegalAccessException ex) {
            java.util.logging.Logger.getLogger(Client.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (javax.swing.UnsupportedLookAndFeelException ex) {
            java.util.logging.Logger.getLogger(Client.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        }
        //</editor-fold>

        /* Create and display the form */
        java.awt.EventQueue.invokeLater(new Runnable() {
            @Override
            public void run() {
                new Client().setVisible(true);
            }
        });
    }
    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.JButton addEmployee;
    private javax.swing.JScrollPane addEmployeeScrollPane;
    private javax.swing.JTable addEmployeeTable;
    private javax.swing.JPanel childTab;
    private javax.swing.JScrollPane employeeScrollPane;
    private javax.swing.JPanel employeeTab;
    private javax.swing.JTable employeeTable;
    private javax.swing.JPanel eventTab;
    private javax.swing.JPanel inventoryTab;
    private javax.swing.JTextArea log;
    private javax.swing.JScrollPane logScrollPane;
    private javax.swing.JPanel logTab;
    private javax.swing.JTabbedPane mainTabbedPane;
    private javax.swing.JPanel orphanageTab;
    private javax.swing.JPanel parentTab;
    private javax.swing.JPanel squadTab;
    private javax.swing.JButton updateEmployee;
    // End of variables declaration//GEN-END:variables
}