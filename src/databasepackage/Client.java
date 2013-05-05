/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package databasepackage;

import com.mysql.jdbc.Connection;
import java.awt.Dimension;
import java.sql.DriverManager;
import java.sql.SQLException;
import java.util.logging.Level;
import java.util.logging.Logger;

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
        responsibleforTextArea.setLineWrap(true);
        responsibleforTextArea.setWrapStyleWord(true);
        getConnection();
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
                Thread employee = new Thread(new Employee(con,employeeTable,
                ID, Surname, Name, Patronymic, DOB, Passport_Series, Passport_Number,
                Issuing_authority, Date_of_issue, Education, Post, Wages,false));
                Thread inventory = new Thread(new Inventory(con,nameInventory,countInventory,
                        responsibleComboBox,responsibleforTextArea, inventoryTable,false));
                inventory.start();
                employee.start();
                try 
                {
                    employee.join();
                    inventory.join();
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
        jLabel1 = new javax.swing.JLabel();
        ID = new javax.swing.JTextField();
        Surname = new javax.swing.JTextField();
        jLabel2 = new javax.swing.JLabel();
        jLabel3 = new javax.swing.JLabel();
        Name = new javax.swing.JTextField();
        jLabel4 = new javax.swing.JLabel();
        Patronymic = new javax.swing.JTextField();
        jLabel5 = new javax.swing.JLabel();
        DOB = new javax.swing.JTextField();
        jLabel6 = new javax.swing.JLabel();
        Passport_Series = new javax.swing.JTextField();
        jLabel7 = new javax.swing.JLabel();
        Passport_Number = new javax.swing.JTextField();
        Issuing_authority = new javax.swing.JTextField();
        jLabel8 = new javax.swing.JLabel();
        Date_of_issue = new javax.swing.JTextField();
        jLabel9 = new javax.swing.JLabel();
        Education = new javax.swing.JTextField();
        jLabel10 = new javax.swing.JLabel();
        Post = new javax.swing.JTextField();
        jLabel11 = new javax.swing.JLabel();
        Wages = new javax.swing.JTextField();
        jLabel12 = new javax.swing.JLabel();
        inventoryTab = new javax.swing.JPanel();
        jLabel13 = new javax.swing.JLabel();
        nameInventory = new javax.swing.JTextField();
        countInventory = new javax.swing.JTextField();
        jLabel14 = new javax.swing.JLabel();
        jLabel15 = new javax.swing.JLabel();
        responsibleComboBox = new javax.swing.JComboBox();
        addInventory = new javax.swing.JButton();
        jScrollPane1 = new javax.swing.JScrollPane();
        inventoryTable = new javax.swing.JTable();
        jScrollPane2 = new javax.swing.JScrollPane();
        responsibleforTextArea = new javax.swing.JTextArea();
        jLabel16 = new javax.swing.JLabel();
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
        setLocationByPlatform(true);
        setResizable(false);

        addEmployee.setText("addEmployee");
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

        jLabel1.setText("ID");

        jLabel2.setText("Surname");

        jLabel3.setText("Name");

        jLabel4.setText("Patronymic");

        jLabel5.setText("DOB");

        jLabel6.setText("Passport Series");

        jLabel7.setText("Passport Number");

        jLabel8.setText("Issuing authority");

        jLabel9.setText("Date of issue");

        jLabel10.setText("Education");

        jLabel11.setText("Post");

        jLabel12.setText("Wages");

        javax.swing.GroupLayout employeeTabLayout = new javax.swing.GroupLayout(employeeTab);
        employeeTab.setLayout(employeeTabLayout);
        employeeTabLayout.setHorizontalGroup(
            employeeTabLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(employeeTabLayout.createSequentialGroup()
                .addGroup(employeeTabLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(jLabel3, javax.swing.GroupLayout.PREFERRED_SIZE, 36, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addGroup(employeeTabLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING, false)
                        .addComponent(ID, javax.swing.GroupLayout.Alignment.LEADING, javax.swing.GroupLayout.DEFAULT_SIZE, 137, Short.MAX_VALUE)
                        .addComponent(jLabel1, javax.swing.GroupLayout.Alignment.LEADING)
                        .addComponent(jLabel2, javax.swing.GroupLayout.Alignment.LEADING)
                        .addComponent(Surname, javax.swing.GroupLayout.Alignment.LEADING))
                    .addGroup(employeeTabLayout.createSequentialGroup()
                        .addGroup(employeeTabLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addGroup(employeeTabLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                                .addComponent(Name, javax.swing.GroupLayout.DEFAULT_SIZE, 137, Short.MAX_VALUE)
                                .addComponent(Patronymic))
                            .addComponent(jLabel4))
                        .addGap(23, 23, 23)
                        .addGroup(employeeTabLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addGroup(employeeTabLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING)
                                .addComponent(Wages, javax.swing.GroupLayout.PREFERRED_SIZE, 97, javax.swing.GroupLayout.PREFERRED_SIZE)
                                .addGroup(employeeTabLayout.createSequentialGroup()
                                    .addGroup(employeeTabLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING)
                                        .addGroup(employeeTabLayout.createSequentialGroup()
                                            .addGroup(employeeTabLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING)
                                                .addComponent(jLabel7, javax.swing.GroupLayout.Alignment.LEADING)
                                                .addComponent(jLabel8, javax.swing.GroupLayout.Alignment.LEADING))
                                            .addGap(57, 57, 57))
                                        .addGroup(javax.swing.GroupLayout.Alignment.LEADING, employeeTabLayout.createSequentialGroup()
                                            .addGroup(employeeTabLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING)
                                                .addComponent(jLabel6, javax.swing.GroupLayout.Alignment.LEADING)
                                                .addComponent(jLabel5, javax.swing.GroupLayout.Alignment.LEADING))
                                            .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)))
                                    .addGroup(employeeTabLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                                        .addComponent(jLabel12)
                                        .addComponent(Post, javax.swing.GroupLayout.PREFERRED_SIZE, 97, javax.swing.GroupLayout.PREFERRED_SIZE)
                                        .addComponent(jLabel11)
                                        .addComponent(Education, javax.swing.GroupLayout.PREFERRED_SIZE, 97, javax.swing.GroupLayout.PREFERRED_SIZE)
                                        .addComponent(jLabel10)
                                        .addComponent(Date_of_issue, javax.swing.GroupLayout.PREFERRED_SIZE, 97, javax.swing.GroupLayout.PREFERRED_SIZE)
                                        .addComponent(jLabel9))))
                            .addGroup(employeeTabLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING)
                                .addComponent(Passport_Series, javax.swing.GroupLayout.PREFERRED_SIZE, 118, javax.swing.GroupLayout.PREFERRED_SIZE)
                                .addComponent(DOB, javax.swing.GroupLayout.Alignment.LEADING, javax.swing.GroupLayout.PREFERRED_SIZE, 118, javax.swing.GroupLayout.PREFERRED_SIZE)
                                .addComponent(Passport_Number, javax.swing.GroupLayout.PREFERRED_SIZE, 118, javax.swing.GroupLayout.PREFERRED_SIZE)
                                .addComponent(Issuing_authority, javax.swing.GroupLayout.PREFERRED_SIZE, 118, javax.swing.GroupLayout.PREFERRED_SIZE)))
                        .addGap(18, 18, 18)
                        .addComponent(addEmployee)))
                .addContainerGap(505, Short.MAX_VALUE))
            .addComponent(employeeScrollPane, javax.swing.GroupLayout.DEFAULT_SIZE, 1016, Short.MAX_VALUE)
        );
        employeeTabLayout.setVerticalGroup(
            employeeTabLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(employeeTabLayout.createSequentialGroup()
                .addGroup(employeeTabLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(jLabel1)
                    .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, employeeTabLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                        .addComponent(jLabel9)
                        .addComponent(jLabel5)))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addGroup(employeeTabLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(employeeTabLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                        .addComponent(ID, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addComponent(DOB, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                    .addComponent(Date_of_issue, javax.swing.GroupLayout.Alignment.TRAILING, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                .addGroup(employeeTabLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(employeeTabLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                        .addComponent(jLabel2)
                        .addComponent(jLabel10))
                    .addComponent(jLabel6))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addGroup(employeeTabLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(Surname, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(Passport_Series, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(Education, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addGroup(employeeTabLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jLabel3)
                    .addComponent(jLabel7)
                    .addComponent(jLabel11))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addGroup(employeeTabLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(Name, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(Passport_Number, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(Post, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addGroup(employeeTabLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jLabel4)
                    .addComponent(jLabel8)
                    .addComponent(jLabel12))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addGroup(employeeTabLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(Patronymic, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(Issuing_authority, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(Wages, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(addEmployee))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                .addComponent(employeeScrollPane, javax.swing.GroupLayout.DEFAULT_SIZE, 313, Short.MAX_VALUE))
        );

        mainTabbedPane.addTab("Employee", employeeTab);

        jLabel13.setText("Name");

        jLabel14.setText("Count");

        jLabel15.setText("Responsible for");

        addInventory.setText("Add Inventory");
        addInventory.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                addInventoryActionPerformed(evt);
            }
        });

        inventoryTable.setModel(new javax.swing.table.DefaultTableModel(
            new Object [][] {

            },
            new String [] {
                "Name", "Count"
            }
        ));
        jScrollPane1.setViewportView(inventoryTable);

        responsibleforTextArea.setColumns(20);
        responsibleforTextArea.setRows(5);
        jScrollPane2.setViewportView(responsibleforTextArea);

        jLabel16.setText("Responsible for this inventory");

        javax.swing.GroupLayout inventoryTabLayout = new javax.swing.GroupLayout(inventoryTab);
        inventoryTab.setLayout(inventoryTabLayout);
        inventoryTabLayout.setHorizontalGroup(
            inventoryTabLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(inventoryTabLayout.createSequentialGroup()
                .addGroup(inventoryTabLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING)
                    .addComponent(jScrollPane1, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addGroup(inventoryTabLayout.createSequentialGroup()
                        .addGap(74, 74, 74)
                        .addComponent(addInventory)))
                .addGap(105, 105, 105)
                .addComponent(jScrollPane2, javax.swing.GroupLayout.PREFERRED_SIZE, 143, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(0, 0, Short.MAX_VALUE))
            .addGroup(inventoryTabLayout.createSequentialGroup()
                .addGroup(inventoryTabLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                    .addGroup(inventoryTabLayout.createSequentialGroup()
                        .addComponent(jLabel13)
                        .addGap(127, 127, 127))
                    .addGroup(inventoryTabLayout.createSequentialGroup()
                        .addComponent(nameInventory)
                        .addGap(18, 18, 18)))
                .addGroup(inventoryTabLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(countInventory, javax.swing.GroupLayout.PREFERRED_SIZE, 56, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(jLabel14))
                .addGap(18, 18, 18)
                .addGroup(inventoryTabLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(jLabel15, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                    .addGroup(inventoryTabLayout.createSequentialGroup()
                        .addComponent(responsibleComboBox, javax.swing.GroupLayout.PREFERRED_SIZE, 100, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addGap(229, 229, 229)
                        .addComponent(jLabel16)
                        .addGap(0, 303, Short.MAX_VALUE)))
                .addGap(13, 13, 13))
        );
        inventoryTabLayout.setVerticalGroup(
            inventoryTabLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(inventoryTabLayout.createSequentialGroup()
                .addContainerGap()
                .addGroup(inventoryTabLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jLabel13)
                    .addComponent(jLabel14)
                    .addComponent(jLabel15))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addGroup(inventoryTabLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(nameInventory, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(countInventory, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(responsibleComboBox, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(addInventory)
                    .addComponent(jLabel16))
                .addGap(18, 18, 18)
                .addGroup(inventoryTabLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(jScrollPane1, javax.swing.GroupLayout.DEFAULT_SIZE, 438, Short.MAX_VALUE)
                    .addGroup(inventoryTabLayout.createSequentialGroup()
                        .addComponent(jScrollPane2, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addGap(0, 0, Short.MAX_VALUE))))
        );

        mainTabbedPane.addTab("Inventory", inventoryTab);

        javax.swing.GroupLayout eventTabLayout = new javax.swing.GroupLayout(eventTab);
        eventTab.setLayout(eventTabLayout);
        eventTabLayout.setHorizontalGroup(
            eventTabLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGap(0, 1016, Short.MAX_VALUE)
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
            .addGap(0, 1016, Short.MAX_VALUE)
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
            .addGap(0, 1016, Short.MAX_VALUE)
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
            .addGap(0, 1016, Short.MAX_VALUE)
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
            .addGap(0, 1016, Short.MAX_VALUE)
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
            .addComponent(logScrollPane, javax.swing.GroupLayout.DEFAULT_SIZE, 1016, Short.MAX_VALUE)
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
            .addComponent(mainTabbedPane, javax.swing.GroupLayout.PREFERRED_SIZE, 1021, javax.swing.GroupLayout.PREFERRED_SIZE)
        );
        layout.setVerticalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addComponent(mainTabbedPane)
        );

        pack();
    }// </editor-fold>//GEN-END:initComponents

    private void addEmployeeActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_addEmployeeActionPerformed
        
        
        Thread employee = new Thread(new Employee(con,employeeTable,
                ID, Surname, Name, Patronymic, DOB, Passport_Series, Passport_Number,
                Issuing_authority, Date_of_issue, Education, Post, Wages,true));
        employee.start();
        try 
        {
            employee.join();
        } 
        catch (InterruptedException ex) 
        {
            Logger.getLogger(Client.class.getName()).log(Level.SEVERE, null, ex);
        }
        ID.setText("");
        Surname.setText("");
        Name.setText("");
        Patronymic.setText("");
        DOB.setText("");
        Passport_Series.setText("");
        Passport_Number.setText("");
        Issuing_authority.setText("");
        Date_of_issue.setText("");
        Education.setText("");
        Post.setText("");
        Wages.setText("");
        
    }//GEN-LAST:event_addEmployeeActionPerformed

    private void addInventoryActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_addInventoryActionPerformed
        Thread inventory = new Thread(new Inventory(con,nameInventory,countInventory,
                        responsibleComboBox,responsibleforTextArea, inventoryTable,true));
        inventory.start();
    }//GEN-LAST:event_addInventoryActionPerformed

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
    private javax.swing.JTextField DOB;
    private javax.swing.JTextField Date_of_issue;
    private javax.swing.JTextField Education;
    private javax.swing.JTextField ID;
    private javax.swing.JTextField Issuing_authority;
    private javax.swing.JTextField Name;
    private javax.swing.JTextField Passport_Number;
    private javax.swing.JTextField Passport_Series;
    private javax.swing.JTextField Patronymic;
    private javax.swing.JTextField Post;
    private javax.swing.JTextField Surname;
    private javax.swing.JTextField Wages;
    private javax.swing.JButton addEmployee;
    private javax.swing.JButton addInventory;
    private javax.swing.JPanel childTab;
    private javax.swing.JTextField countInventory;
    private javax.swing.JScrollPane employeeScrollPane;
    private javax.swing.JPanel employeeTab;
    private javax.swing.JTable employeeTable;
    private javax.swing.JPanel eventTab;
    private javax.swing.JPanel inventoryTab;
    private javax.swing.JTable inventoryTable;
    private javax.swing.JLabel jLabel1;
    private javax.swing.JLabel jLabel10;
    private javax.swing.JLabel jLabel11;
    private javax.swing.JLabel jLabel12;
    private javax.swing.JLabel jLabel13;
    private javax.swing.JLabel jLabel14;
    private javax.swing.JLabel jLabel15;
    private javax.swing.JLabel jLabel16;
    private javax.swing.JLabel jLabel2;
    private javax.swing.JLabel jLabel3;
    private javax.swing.JLabel jLabel4;
    private javax.swing.JLabel jLabel5;
    private javax.swing.JLabel jLabel6;
    private javax.swing.JLabel jLabel7;
    private javax.swing.JLabel jLabel8;
    private javax.swing.JLabel jLabel9;
    private javax.swing.JScrollPane jScrollPane1;
    private javax.swing.JScrollPane jScrollPane2;
    private javax.swing.JTextArea log;
    private javax.swing.JScrollPane logScrollPane;
    private javax.swing.JPanel logTab;
    private javax.swing.JTabbedPane mainTabbedPane;
    private javax.swing.JTextField nameInventory;
    private javax.swing.JPanel orphanageTab;
    private javax.swing.JPanel parentTab;
    private javax.swing.JComboBox responsibleComboBox;
    private javax.swing.JTextArea responsibleforTextArea;
    private javax.swing.JPanel squadTab;
    // End of variables declaration//GEN-END:variables
}
