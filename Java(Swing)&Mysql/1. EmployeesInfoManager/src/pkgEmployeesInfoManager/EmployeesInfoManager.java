/**
 * Program : EmployeesInfoManager Program
 * Purpose : Manage employee's information(hired date, working day, name ...)
 * Function :   1. Load employee's information from file(as database) when program start
 *              2. Export employee's information to CSV file with file chooser
 *              3. Add employee's information
 *              4. Edit employee's information( Double click employee on the list )
 *              5. Sorting employee list by name or hired date
 *              6. Save employee list in the file(as database) when program exit.
 * Script by : Donghyeok Seo
 * Date : 2020.02.19
 */

package pkgEmployeesInfoManager;

import java.io.File;
import java.io.IOException;
import java.io.PrintWriter;
import java.time.LocalDate;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Calendar;
import java.util.Comparator;
import java.util.Date;
import java.util.List;
import java.util.Scanner;
import javax.swing.DefaultListModel;
import javax.swing.JFileChooser;
import javax.swing.JOptionPane;
import javax.swing.filechooser.FileNameExtensionFilter;

public class EmployeesInfoManager extends javax.swing.JFrame {
    
    enum Button {Add,Edit}// Add : in the menu bar , Edit : double click on the list    
    
    private DefaultListModel<EmployeeSchedule> modelEmployeeList = new DefaultListModel<>();
    private DefaultListModel<Weekday> modelWeekdays = new DefaultListModel<>();
    private final String FILE_NAME = "employees.txt";
    
    private Button dlgFrom;

    public EmployeesInfoManager() {
        initComponents();
        
        dlgAddEdit.setLocation(this.getLocation());
        dlgStats.setLocation(this.getLocation());
        
        EmployeeSchedule.initStatsMap();
        
        loadDataFromFile();
        sortListBy(EmployeeSchedule.compareByName);
        
        FileNameExtensionFilter filter = new FileNameExtensionFilter("CSV files", "csv");
        fileChooser.setFileFilter(filter);
        modelWeekdays.addAll(Arrays.asList(Weekday.values()));
    }
    
    
    void resetDlgAddEditFields(){
        
        if(dlgFrom == Button.Edit){
            
            EmployeeSchedule emp = lstEmployees.getSelectedValue();
            dlgAddEdit_tfName.setText(emp.getName());
            Calendar cal = Calendar.getInstance();
            LocalDate hiredDate = emp.getDateHired();
            
            cal.set(hiredDate.getYear(),hiredDate.getMonthValue() - 1 ,hiredDate.getDayOfMonth());
                    
            dlgAddEdit_jxDatePicker.setDate(cal.getTime());
            dlgAddEdit_cbIsManager.setSelected(emp.getIsManager());
            dlgAddEdit_tfDepartment.setText(emp.getDepartment());
            
            Weekday[] weekday = emp.getWorkdays();
            
            int[] arr = new int[weekday.length];
            for(int i=0; i < weekday.length ; i++){
                int index = 0;
                
                for(Weekday w : Weekday.values()){
                    if(w.equals(weekday[i])){
                        arr[i] = index;
                    } else{
                        index++;
                    }  
                }
            }
            
            dlgAddEdit_lstWeekdays.setSelectedIndices(arr);
            
            dlgAddEdit_btAddEdit.setText("Update");
        } else {
            dlgAddEdit_tfName.setText("");
            dlgAddEdit_jxDatePicker.setDate(new Date());
            dlgAddEdit_cbIsManager.setSelected(false);
            dlgAddEdit_tfDepartment.setText("");
            dlgAddEdit_btAddEdit.setText("Add");
            dlgAddEdit_lstWeekdays.clearSelection();
        }
    }
    
    void sortListBy(Comparator<EmployeeSchedule> cmp){
        ArrayList<EmployeeSchedule> tmpList = new ArrayList<>();
        for(Object o : modelEmployeeList.toArray()){
            EmployeeSchedule e = (EmployeeSchedule) o ;
            tmpList.add(e);
        }
        tmpList.sort(cmp);
        
        modelEmployeeList.clear();
        modelEmployeeList.addAll(tmpList);
    }
    
    void setStatusMsg(String msg){ lblStatus.setText(msg); }
    
    void loadDataFromFile() {
        try ( Scanner fileInput = new Scanner(new File(FILE_NAME))) {
            String errorMsg = "";
            while (fileInput.hasNextLine()) {
                try {
                    String dataLine = fileInput.nextLine();
                    modelEmployeeList.addElement(new EmployeeSchedule(dataLine));
                } catch (InvalidValueException ex) {
                    String warningMsg = "WARNING:Loading data:" + ex.getMessage();
                    System.out.println(warningMsg);
                    errorMsg += warningMsg + "\n";
                }
            }
            if(!errorMsg.isEmpty()){
                JOptionPane.showMessageDialog(this,
                    errorMsg,
                    "File data Error",
                    JOptionPane.ERROR_MESSAGE
                );
            }
        } catch (IOException ex) {
            System.out.println("ERROR:Cannot read file");
            JOptionPane.showMessageDialog(this,
                    "Cannot read file:" + FILE_NAME,
                    "File Loading Error",
                    JOptionPane.ERROR_MESSAGE
            );
        }
    }
    
    void saveDataToFile(String fileName) {
        try ( PrintWriter fileOutput = new PrintWriter(new File(fileName))) {
            for (Object o : modelEmployeeList.toArray()) {
                fileOutput.println(((EmployeeSchedule) o).toDataString());
            }
        } catch (IOException ex) {
            System.out.println("ERROR:Cannot open file");
            JOptionPane.showMessageDialog(this,
                    "Cannot open file:" + fileName,
                    "File Saving Error",
                    JOptionPane.ERROR_MESSAGE
            );
        }
    }
    
    
    
    /**
     * This method is called from within the constructor to initialize the form.
     * WARNING: Do NOT modify this code. The content of this method is always
     * regenerated by the Form Editor.
     */
    @SuppressWarnings("unchecked")
    // <editor-fold defaultstate="collapsed" desc="Generated Code">//GEN-BEGIN:initComponents
    private void initComponents() {

        fileChooser = new javax.swing.JFileChooser();
        btnGrpSortBy = new javax.swing.ButtonGroup();
        dlgAddEdit = new javax.swing.JDialog();
        jLabel1 = new javax.swing.JLabel();
        dlgAddEdit_tfName = new javax.swing.JTextField();
        dlgAddEdit_cbIsManager = new javax.swing.JCheckBox();
        jLabel2 = new javax.swing.JLabel();
        jLabel3 = new javax.swing.JLabel();
        dlgAddEdit_jxDatePicker = new org.jdesktop.swingx.JXDatePicker();
        dlgAddEdit_tfDepartment = new javax.swing.JTextField();
        jLabel4 = new javax.swing.JLabel();
        jScrollPane2 = new javax.swing.JScrollPane();
        dlgAddEdit_lstWeekdays = new javax.swing.JList<>();
        dlgAddEdit_btCancel = new javax.swing.JButton();
        dlgAddEdit_btAddEdit = new javax.swing.JButton();
        dlgStats = new javax.swing.JDialog();
        jLabel5 = new javax.swing.JLabel();
        dlgStats_btDismiss = new javax.swing.JButton();
        statsCanvasPanel1 = new pkgEmployeesInfoManager.StatsCanvasPanel();
        lblStatus = new javax.swing.JLabel();
        jScrollPane1 = new javax.swing.JScrollPane();
        lstEmployees = new javax.swing.JList<>();
        jMenuBar1 = new javax.swing.JMenuBar();
        jMenu1 = new javax.swing.JMenu();
        miExport = new javax.swing.JMenuItem();
        jSeparator1 = new javax.swing.JPopupMenu.Separator();
        miExit = new javax.swing.JMenuItem();
        jMenu2 = new javax.swing.JMenu();
        rbSortByName = new javax.swing.JRadioButtonMenuItem();
        rbSortByHiredDate = new javax.swing.JRadioButtonMenuItem();
        mAdd = new javax.swing.JMenu();
        mStats = new javax.swing.JMenu();

        jLabel1.setText("Name: ");

        dlgAddEdit_cbIsManager.setText("is Manager");

        jLabel2.setText("HireDate: ");

        jLabel3.setText("DepartMent: ");

        jLabel4.setText("Works on Days: ");

        dlgAddEdit_lstWeekdays.setModel(modelWeekdays);
        jScrollPane2.setViewportView(dlgAddEdit_lstWeekdays);

        dlgAddEdit_btCancel.setText("Cancel");
        dlgAddEdit_btCancel.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                dlgAddEdit_btCancelActionPerformed(evt);
            }
        });

        dlgAddEdit_btAddEdit.setText("Add/Update");
        dlgAddEdit_btAddEdit.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                dlgAddEdit_btAddEditActionPerformed(evt);
            }
        });

        javax.swing.GroupLayout dlgAddEditLayout = new javax.swing.GroupLayout(dlgAddEdit.getContentPane());
        dlgAddEdit.getContentPane().setLayout(dlgAddEditLayout);
        dlgAddEditLayout.setHorizontalGroup(
            dlgAddEditLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(dlgAddEditLayout.createSequentialGroup()
                .addGroup(dlgAddEditLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(dlgAddEditLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                        .addGroup(dlgAddEditLayout.createSequentialGroup()
                            .addContainerGap()
                            .addComponent(jLabel1)
                            .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                            .addComponent(dlgAddEdit_tfName, javax.swing.GroupLayout.PREFERRED_SIZE, 146, javax.swing.GroupLayout.PREFERRED_SIZE))
                        .addGroup(dlgAddEditLayout.createSequentialGroup()
                            .addGap(31, 31, 31)
                            .addComponent(dlgAddEdit_cbIsManager))
                        .addGroup(dlgAddEditLayout.createSequentialGroup()
                            .addContainerGap()
                            .addComponent(jLabel2)
                            .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                            .addComponent(dlgAddEdit_jxDatePicker, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
                        .addGroup(dlgAddEditLayout.createSequentialGroup()
                            .addContainerGap()
                            .addComponent(jLabel3, javax.swing.GroupLayout.PREFERRED_SIZE, 64, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                            .addComponent(dlgAddEdit_tfDepartment)))
                    .addGroup(dlgAddEditLayout.createSequentialGroup()
                        .addGap(35, 35, 35)
                        .addComponent(dlgAddEdit_btCancel)
                        .addGap(18, 18, 18)
                        .addComponent(dlgAddEdit_btAddEdit)))
                .addGap(27, 27, 27)
                .addGroup(dlgAddEditLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(jLabel4)
                    .addComponent(jScrollPane2, javax.swing.GroupLayout.PREFERRED_SIZE, 110, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
        );

        dlgAddEditLayout.linkSize(javax.swing.SwingConstants.HORIZONTAL, new java.awt.Component[] {jLabel1, jLabel2, jLabel3});

        dlgAddEditLayout.setVerticalGroup(
            dlgAddEditLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(dlgAddEditLayout.createSequentialGroup()
                .addContainerGap()
                .addGroup(dlgAddEditLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jLabel1)
                    .addComponent(dlgAddEdit_tfName, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(jLabel4))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                .addGroup(dlgAddEditLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(dlgAddEditLayout.createSequentialGroup()
                        .addComponent(dlgAddEdit_cbIsManager)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                        .addGroup(dlgAddEditLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                            .addComponent(jLabel2)
                            .addComponent(dlgAddEdit_jxDatePicker, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                        .addGap(7, 7, 7)
                        .addGroup(dlgAddEditLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                            .addComponent(jLabel3)
                            .addComponent(dlgAddEdit_tfDepartment, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                        .addGap(18, 18, 18)
                        .addGroup(dlgAddEditLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                            .addComponent(dlgAddEdit_btCancel)
                            .addComponent(dlgAddEdit_btAddEdit)))
                    .addComponent(jScrollPane2, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
        );

        jLabel5.setText("Employees Working on: ");

        dlgStats_btDismiss.setText("Dismiss");
        dlgStats_btDismiss.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                dlgStats_btDismissActionPerformed(evt);
            }
        });

        javax.swing.GroupLayout statsCanvasPanel1Layout = new javax.swing.GroupLayout(statsCanvasPanel1);
        statsCanvasPanel1.setLayout(statsCanvasPanel1Layout);
        statsCanvasPanel1Layout.setHorizontalGroup(
            statsCanvasPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGap(0, 430, Short.MAX_VALUE)
        );
        statsCanvasPanel1Layout.setVerticalGroup(
            statsCanvasPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGap(0, 200, Short.MAX_VALUE)
        );

        javax.swing.GroupLayout dlgStatsLayout = new javax.swing.GroupLayout(dlgStats.getContentPane());
        dlgStats.getContentPane().setLayout(dlgStatsLayout);
        dlgStatsLayout.setHorizontalGroup(
            dlgStatsLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(dlgStatsLayout.createSequentialGroup()
                .addContainerGap()
                .addGroup(dlgStatsLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(dlgStatsLayout.createSequentialGroup()
                        .addComponent(jLabel5)
                        .addGap(66, 66, 66)
                        .addComponent(dlgStats_btDismiss, javax.swing.GroupLayout.PREFERRED_SIZE, 95, javax.swing.GroupLayout.PREFERRED_SIZE))
                    .addComponent(statsCanvasPanel1, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
        );
        dlgStatsLayout.setVerticalGroup(
            dlgStatsLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(dlgStatsLayout.createSequentialGroup()
                .addContainerGap()
                .addGroup(dlgStatsLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jLabel5)
                    .addComponent(dlgStats_btDismiss))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(statsCanvasPanel1, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
        );

        setDefaultCloseOperation(javax.swing.WindowConstants.DO_NOTHING_ON_CLOSE);
        setPreferredSize(new java.awt.Dimension(600, 400));
        setResizable(false);
        addWindowListener(new java.awt.event.WindowAdapter() {
            public void windowClosing(java.awt.event.WindowEvent evt) {
                formWindowClosing(evt);
            }
        });

        lblStatus.setText("...");
        getContentPane().add(lblStatus, java.awt.BorderLayout.PAGE_END);

        lstEmployees.setModel(modelEmployeeList);
        lstEmployees.setSelectionMode(javax.swing.ListSelectionModel.SINGLE_SELECTION);
        lstEmployees.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                lstEmployeesMouseClicked(evt);
            }
        });
        jScrollPane1.setViewportView(lstEmployees);

        getContentPane().add(jScrollPane1, java.awt.BorderLayout.CENTER);

        jMenu1.setText("File");

        miExport.setText("Export to csv...");
        miExport.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                miExportActionPerformed(evt);
            }
        });
        jMenu1.add(miExport);
        jMenu1.add(jSeparator1);

        miExit.setText("Exit");
        miExit.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                miExitActionPerformed(evt);
            }
        });
        jMenu1.add(miExit);

        jMenuBar1.add(jMenu1);

        jMenu2.setText("SortBy");

        btnGrpSortBy.add(rbSortByName);
        rbSortByName.setSelected(true);
        rbSortByName.setText("Name");
        rbSortByName.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                rbSortByNameActionPerformed(evt);
            }
        });
        jMenu2.add(rbSortByName);

        btnGrpSortBy.add(rbSortByHiredDate);
        rbSortByHiredDate.setText("Hired Date");
        rbSortByHiredDate.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                rbSortByHiredDateActionPerformed(evt);
            }
        });
        jMenu2.add(rbSortByHiredDate);

        jMenuBar1.add(jMenu2);

        mAdd.setText("Add");
        mAdd.addMenuKeyListener(new javax.swing.event.MenuKeyListener() {
            public void menuKeyPressed(javax.swing.event.MenuKeyEvent evt) {
            }
            public void menuKeyReleased(javax.swing.event.MenuKeyEvent evt) {
            }
            public void menuKeyTyped(javax.swing.event.MenuKeyEvent evt) {
                mAddMenuKeyTyped(evt);
            }
        });
        mAdd.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                mAddMouseClicked(evt);
            }
        });
        jMenuBar1.add(mAdd);

        mStats.setText("Stats");
        mStats.addMenuKeyListener(new javax.swing.event.MenuKeyListener() {
            public void menuKeyPressed(javax.swing.event.MenuKeyEvent evt) {
            }
            public void menuKeyReleased(javax.swing.event.MenuKeyEvent evt) {
            }
            public void menuKeyTyped(javax.swing.event.MenuKeyEvent evt) {
                mStatsMenuKeyTyped(evt);
            }
        });
        mStats.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                mStatsMouseClicked(evt);
            }
        });
        jMenuBar1.add(mStats);

        setJMenuBar(jMenuBar1);

        pack();
        setLocationRelativeTo(null);
    }// </editor-fold>//GEN-END:initComponents

    private void miExportActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_miExportActionPerformed
        if(fileChooser.showSaveDialog(this) == JFileChooser.APPROVE_OPTION){
            File targetFile = fileChooser.getSelectedFile();
            String filePath = targetFile.getPath();
            if (!filePath.matches(".*.csv")) {
                filePath += ".csv";
            }

            saveDataToFile(filePath);
            
            setStatusMsg("Exported: \"" + filePath + "\"");
        }
    }//GEN-LAST:event_miExportActionPerformed

    private void formWindowClosing(java.awt.event.WindowEvent evt) {//GEN-FIRST:event_formWindowClosing
        saveDataToFile(FILE_NAME);
        dlgAddEdit.dispose();
        dlgStats.dispose();
        dispose();
    }//GEN-LAST:event_formWindowClosing

    private void miExitActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_miExitActionPerformed
        saveDataToFile(FILE_NAME);
        dlgAddEdit.dispose();
        dlgStats.dispose();
        dispose();
    }//GEN-LAST:event_miExitActionPerformed

    private void rbSortByNameActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_rbSortByNameActionPerformed
        sortListBy(EmployeeSchedule.compareByName);
        
        setStatusMsg("List is sorted by Name");
    }//GEN-LAST:event_rbSortByNameActionPerformed

    private void rbSortByHiredDateActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_rbSortByHiredDateActionPerformed
        sortListBy(EmployeeSchedule.compareByDateHire);
        
        setStatusMsg("List is sorted by Hired date");
    }//GEN-LAST:event_rbSortByHiredDateActionPerformed

    private void mAddMouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_mAddMouseClicked
        dlgAddEdit.pack();
        dlgFrom = Button.Add;
        resetDlgAddEditFields();
        dlgAddEdit.setVisible(true);
    }//GEN-LAST:event_mAddMouseClicked

    private void mAddMenuKeyTyped(javax.swing.event.MenuKeyEvent evt) {//GEN-FIRST:event_mAddMenuKeyTyped
        dlgAddEdit.pack();
        dlgFrom = Button.Add;
        resetDlgAddEditFields();
        dlgAddEdit.setVisible(true);
    }//GEN-LAST:event_mAddMenuKeyTyped

    private void dlgAddEdit_btCancelActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_dlgAddEdit_btCancelActionPerformed
        dlgAddEdit.dispose();
    }//GEN-LAST:event_dlgAddEdit_btCancelActionPerformed

    private void dlgAddEdit_btAddEditActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_dlgAddEdit_btAddEditActionPerformed
        try{
            EmployeeSchedule newEmp = new EmployeeSchedule(
                    dlgAddEdit_tfName.getText(),
                    dlgAddEdit_cbIsManager.isSelected(),
                    dlgAddEdit_tfDepartment.getText(),
                    dlgAddEdit_jxDatePicker.getDate()
            );
            
            List<Weekday> weekday = dlgAddEdit_lstWeekdays.getSelectedValuesList();
            Weekday[] arr = new Weekday[weekday.size()];
            
            newEmp.setWorkdays(weekday.toArray(arr));
           
            if(dlgFrom.equals(Button.Add)){
                modelEmployeeList.addElement(newEmp);
                setStatusMsg("Added: " + newEmp.toString());
                
            } else if(dlgFrom.equals(Button.Edit)){
                int index = lstEmployees.getSelectedIndex();
                modelEmployeeList.remove(index);
                modelEmployeeList.add(index, newEmp);
                setStatusMsg("Edited: " + newEmp.toString());
                
            } else {
                System.out.println("Internel Error: Check your code");
                return;
            }
            
            if(rbSortByName.isSelected()){
                sortListBy(EmployeeSchedule.compareByName);
            } else {
                sortListBy(EmployeeSchedule.compareByDateHire);
            }
            
        }catch (InvalidValueException ex){
            System.out.println("Invalid Data: " + ex.getMessage());
            JOptionPane.showMessageDialog(this,
                    "Invalud Data:" + ex.getMessage(),
                    "Validation Error",
                    JOptionPane.ERROR_MESSAGE
            );
        }
        
        dlgAddEdit.dispose();
    }//GEN-LAST:event_dlgAddEdit_btAddEditActionPerformed

    private void lstEmployeesMouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_lstEmployeesMouseClicked
        if (evt.getClickCount() == 2) {
            dlgAddEdit.pack();
            dlgAddEdit.setVisible(true);
            dlgFrom = Button.Edit;
            
            resetDlgAddEditFields();
        }
    }//GEN-LAST:event_lstEmployeesMouseClicked

    private void mStatsMenuKeyTyped(javax.swing.event.MenuKeyEvent evt) {//GEN-FIRST:event_mStatsMenuKeyTyped
        dlgStats.pack();
        dlgStats.setVisible(true);
    }//GEN-LAST:event_mStatsMenuKeyTyped

    private void mStatsMouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_mStatsMouseClicked
        dlgStats.pack();
        dlgStats.setVisible(true);
    }//GEN-LAST:event_mStatsMouseClicked

    private void dlgStats_btDismissActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_dlgStats_btDismissActionPerformed
        dlgStats.dispose();
    }//GEN-LAST:event_dlgStats_btDismissActionPerformed
    
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
            java.util.logging.Logger.getLogger(EmployeesInfoManager.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (InstantiationException ex) {
            java.util.logging.Logger.getLogger(EmployeesInfoManager.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (IllegalAccessException ex) {
            java.util.logging.Logger.getLogger(EmployeesInfoManager.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (javax.swing.UnsupportedLookAndFeelException ex) {
            java.util.logging.Logger.getLogger(EmployeesInfoManager.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        }
        //</editor-fold>
        //</editor-fold>

        /* Create and display the form */
        java.awt.EventQueue.invokeLater(new Runnable() {
            public void run() {
                new EmployeesInfoManager().setVisible(true);
            }
        });
    }

    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.ButtonGroup btnGrpSortBy;
    private javax.swing.JDialog dlgAddEdit;
    private javax.swing.JButton dlgAddEdit_btAddEdit;
    private javax.swing.JButton dlgAddEdit_btCancel;
    private javax.swing.JCheckBox dlgAddEdit_cbIsManager;
    private org.jdesktop.swingx.JXDatePicker dlgAddEdit_jxDatePicker;
    private javax.swing.JList<Weekday> dlgAddEdit_lstWeekdays;
    private javax.swing.JTextField dlgAddEdit_tfDepartment;
    private javax.swing.JTextField dlgAddEdit_tfName;
    private javax.swing.JDialog dlgStats;
    private javax.swing.JButton dlgStats_btDismiss;
    private javax.swing.JFileChooser fileChooser;
    private javax.swing.JLabel jLabel1;
    private javax.swing.JLabel jLabel2;
    private javax.swing.JLabel jLabel3;
    private javax.swing.JLabel jLabel4;
    private javax.swing.JLabel jLabel5;
    private javax.swing.JMenu jMenu1;
    private javax.swing.JMenu jMenu2;
    private javax.swing.JMenuBar jMenuBar1;
    private javax.swing.JScrollPane jScrollPane1;
    private javax.swing.JScrollPane jScrollPane2;
    private javax.swing.JPopupMenu.Separator jSeparator1;
    private javax.swing.JLabel lblStatus;
    private javax.swing.JList<EmployeeSchedule> lstEmployees;
    private javax.swing.JMenu mAdd;
    private javax.swing.JMenu mStats;
    private javax.swing.JMenuItem miExit;
    private javax.swing.JMenuItem miExport;
    private javax.swing.JRadioButtonMenuItem rbSortByHiredDate;
    private javax.swing.JRadioButtonMenuItem rbSortByName;
    private pkgEmployeesInfoManager.StatsCanvasPanel statsCanvasPanel1;
    // End of variables declaration//GEN-END:variables
}
