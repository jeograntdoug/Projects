/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package pkgLibrary;

import java.awt.Graphics2D;
import java.awt.Image;
import java.awt.image.BufferedImage;
import java.io.File;
import java.io.IOException;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.regex.Matcher;
import java.util.regex.Pattern;
import javax.imageio.ImageIO;
import javax.swing.DefaultListModel;
import javax.swing.ImageIcon;
import javax.swing.JFileChooser;
import javax.swing.JOptionPane;
import javax.swing.filechooser.FileNameExtensionFilter;

/**
 *
 * @author heokc
 */
public class PenalDlgMember extends javax.swing.JPanel {
    private final Pattern imgExtPtn = Pattern.compile("\\.(jpg|jpeg|png|gif)$");
    // set file filter for *.jpg, *.jpeg, *.gif, *.png extentions 
    private final FileNameExtensionFilter filter = new FileNameExtensionFilter(
                        "Image file(*.jpg, *.jpeg, *.gif, *.png)"
                        , "jpg","jpeg","gif","png"
                );
    
    private final String DEFAULT_PHOTO_MESSAGE = "150x150 pixels, clickable";
    
    ArrayList<Member> memberList = new ArrayList<>();
    DefaultListModel<Member> modelMemberList = new DefaultListModel<>();
    
    private BufferedImage bufImage;
    private Database db;
    
    public PenalDlgMember() {
        initComponents();
        
        try{
            db = DatabaseManager.getDatabaseInstance();

        } catch(SQLException ex){
            ex.printStackTrace();
            JOptionPane.showMessageDialog(this,
                    "Cannot connect Dababase(ManageMember). Program exit",
                    "DABABASE ERROR",
                    JOptionPane.ERROR_MESSAGE
            );
        }

        comboMaxBooks.setRenderer(new ExtendedListCellRenderer());
        
        fileChooser.setFileFilter(filter);
    }

     public void reloadMemberList(){ 
        try{
            tfSearch.setText("");// clear search text field
            memberList = db.getMemberList();
            modelMemberList.clear();
            modelMemberList.addAll(memberList);
        } catch (SQLException ex){
            ex.printStackTrace();
            JOptionPane.showMessageDialog(this,
                    "Cannot read data",
                    "ERROR Data load",
                    JOptionPane.ERROR_MESSAGE
            );
            System.exit(1);
        }
    }
     
     
    // reset Input fields by select or not
    private void resetInputFields(){
        Member member = lstMember.getSelectedValue();
        if(member == null){
            lblIdVal.setText("-");
            tfName.setText("");
            tfEmail.setText("");
            comboMaxBooks.setSelectedIndex(-1);
            lblPhotoVal.setIcon(null);
            lblPhotoVal.setText(DEFAULT_PHOTO_MESSAGE);
            
           
            btDelete.setEnabled(false);
            btUpdate.setEnabled(false);
            return;
        } 
        
        lblIdVal.setText(member.getId() + "");
        tfName.setText(member.getName());
        tfEmail.setText(member.getEmail());
        
        int index = Arrays.binarySearch(Member.MAXBOOK_VALUES, member.getMaxBookAllowed());
        comboMaxBooks.setSelectedIndex(index);
        
        bufImage = member.getPhotoBufImg();
        if(bufImage != null){
            lblPhotoVal.setIcon(new ImageIcon(bufImage));
        }
        
        btDelete.setEnabled(true);
        btUpdate.setEnabled(true);
        
       
    }
    
    // convert image size to 150x150, return converted Image
    public static BufferedImage convertImgSize(BufferedImage originalImg){
        BufferedImage newImg = new BufferedImage(150,150,originalImg.getType());
        Graphics2D g = newImg.createGraphics();
        g.drawImage(originalImg.getScaledInstance(150, 150, Image.SCALE_SMOOTH),null,null);
        g.dispose();
        
        return newImg;
    }
    // Check if file extention is in *.jpg, *.jpeg, *.gif, *.png
    private boolean isValidImgFile(File file){
        Matcher m = imgExtPtn.matcher(file.getName());
        if(!m.find()){
            JOptionPane.showMessageDialog(this,
                    "Not supported Image file chosen:" +file.getName(),
                    "No Image chosen",
                    JOptionPane.ERROR_MESSAGE
            );
            return false;
        }
        return true;
    }
    // Choose Image with file chooser(filter :*.jpg, *.jpeg, *.gif, *.png)
    private File chooseImageFile(){
        if(fileChooser.showOpenDialog(this) != JFileChooser.APPROVE_OPTION){
            return null;
        }
        
        File chosenImgFile = fileChooser.getSelectedFile();
        if(chosenImgFile == null){
            JOptionPane.showMessageDialog(this,
                    "Please choose image that you want to add",
                    "No Image is chosen",
                    JOptionPane.ERROR_MESSAGE
            );
            return null;
        }
        return chosenImgFile;
        
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
        jLabel1 = new javax.swing.JLabel();
        tfSearch = new javax.swing.JTextField();
        btClear = new javax.swing.JButton();
        jScrollPane1 = new javax.swing.JScrollPane();
        lstMember = new javax.swing.JList<>();
        jLabel2 = new javax.swing.JLabel();
        jLabel3 = new javax.swing.JLabel();
        jLabel4 = new javax.swing.JLabel();
        jLabel5 = new javax.swing.JLabel();
        jLabel6 = new javax.swing.JLabel();
        lblIdVal = new javax.swing.JLabel();
        tfName = new javax.swing.JTextField();
        tfEmail = new javax.swing.JTextField();
        comboMaxBooks = new javax.swing.JComboBox<>();
        lblPhotoVal = new javax.swing.JLabel();
        btAdd = new javax.swing.JButton();
        btUpdate = new javax.swing.JButton();
        btDelete = new javax.swing.JButton();

        setPreferredSize(new java.awt.Dimension(647, 344));

        jLabel1.setText("Search:");

        tfSearch.addKeyListener(new java.awt.event.KeyAdapter() {
            public void keyReleased(java.awt.event.KeyEvent evt) {
                tfSearchKeyReleased(evt);
            }
        });

        btClear.setText("Clear");
        btClear.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btClearActionPerformed(evt);
            }
        });

        lstMember.setModel(modelMemberList);
        lstMember.setSelectionMode(javax.swing.ListSelectionModel.SINGLE_SELECTION);
        lstMember.addListSelectionListener(new javax.swing.event.ListSelectionListener() {
            public void valueChanged(javax.swing.event.ListSelectionEvent evt) {
                lstMemberValueChanged(evt);
            }
        });
        jScrollPane1.setViewportView(lstMember);

        jLabel2.setText("ID:");

        jLabel3.setText("Name: ");

        jLabel4.setText("Email:");

        jLabel5.setText("Max Books:");

        jLabel6.setText("Photo: ");

        lblIdVal.setHorizontalAlignment(javax.swing.SwingConstants.RIGHT);
        lblIdVal.setText("-");

        comboMaxBooks.setModel(new javax.swing.DefaultComboBoxModel<>(new String[] { "3", "5", "10", "15", "20" }));
        comboMaxBooks.setSelectedIndex(-1);

        lblPhotoVal.setText("150x150 Pixels, Clickable");
        lblPhotoVal.setBorder(javax.swing.BorderFactory.createBevelBorder(javax.swing.border.BevelBorder.RAISED));
        lblPhotoVal.setPreferredSize(new java.awt.Dimension(150, 150));
        lblPhotoVal.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                lblPhotoValMouseClicked(evt);
            }
        });

        btAdd.setText("Add");
        btAdd.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btAddActionPerformed(evt);
            }
        });

        btUpdate.setText("Update");
        btUpdate.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btUpdateActionPerformed(evt);
            }
        });

        btDelete.setText("Delete");
        btDelete.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btDeleteActionPerformed(evt);
            }
        });

        javax.swing.GroupLayout layout = new javax.swing.GroupLayout(this);
        this.setLayout(layout);
        layout.setHorizontalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(layout.createSequentialGroup()
                .addGap(10, 10, 10)
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(layout.createSequentialGroup()
                        .addComponent(jLabel1, javax.swing.GroupLayout.PREFERRED_SIZE, 43, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addGap(4, 4, 4)
                        .addComponent(tfSearch, javax.swing.GroupLayout.PREFERRED_SIZE, 127, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addGap(6, 6, 6)
                        .addComponent(btClear))
                    .addGroup(layout.createSequentialGroup()
                        .addComponent(jScrollPane1, javax.swing.GroupLayout.PREFERRED_SIZE, 372, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addGap(18, 18, 18)
                        .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addGroup(layout.createSequentialGroup()
                                .addComponent(jLabel2, javax.swing.GroupLayout.PREFERRED_SIZE, 43, javax.swing.GroupLayout.PREFERRED_SIZE)
                                .addGap(18, 18, 18)
                                .addComponent(lblIdVal, javax.swing.GroupLayout.PREFERRED_SIZE, 151, javax.swing.GroupLayout.PREFERRED_SIZE))
                            .addGroup(layout.createSequentialGroup()
                                .addComponent(jLabel3)
                                .addGap(27, 27, 27)
                                .addComponent(tfName, javax.swing.GroupLayout.PREFERRED_SIZE, 151, javax.swing.GroupLayout.PREFERRED_SIZE))
                            .addGroup(layout.createSequentialGroup()
                                .addComponent(jLabel4)
                                .addGap(32, 32, 32)
                                .addComponent(tfEmail, javax.swing.GroupLayout.PREFERRED_SIZE, 151, javax.swing.GroupLayout.PREFERRED_SIZE))
                            .addGroup(layout.createSequentialGroup()
                                .addComponent(jLabel5)
                                .addGap(10, 10, 10)
                                .addComponent(comboMaxBooks, javax.swing.GroupLayout.PREFERRED_SIZE, 150, javax.swing.GroupLayout.PREFERRED_SIZE))
                            .addGroup(layout.createSequentialGroup()
                                .addComponent(jLabel6)
                                .addGap(29, 29, 29)
                                .addComponent(lblPhotoVal, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                            .addGroup(layout.createSequentialGroup()
                                .addGap(7, 7, 7)
                                .addComponent(btAdd, javax.swing.GroupLayout.PREFERRED_SIZE, 63, javax.swing.GroupLayout.PREFERRED_SIZE)
                                .addGap(10, 10, 10)
                                .addComponent(btUpdate)
                                .addGap(6, 6, 6)
                                .addComponent(btDelete))))))
        );
        layout.setVerticalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(layout.createSequentialGroup()
                .addGap(10, 10, 10)
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(layout.createSequentialGroup()
                        .addGap(4, 4, 4)
                        .addComponent(jLabel1))
                    .addGroup(layout.createSequentialGroup()
                        .addGap(1, 1, 1)
                        .addComponent(tfSearch, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                    .addComponent(btClear))
                .addGap(6, 6, 6)
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(jScrollPane1, javax.swing.GroupLayout.PREFERRED_SIZE, 297, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addGroup(layout.createSequentialGroup()
                        .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addComponent(jLabel2)
                            .addComponent(lblIdVal))
                        .addGap(10, 10, 10)
                        .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addGroup(layout.createSequentialGroup()
                                .addGap(3, 3, 3)
                                .addComponent(jLabel3))
                            .addComponent(tfName, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                        .addGap(10, 10, 10)
                        .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addGroup(layout.createSequentialGroup()
                                .addGap(3, 3, 3)
                                .addComponent(jLabel4))
                            .addComponent(tfEmail, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                        .addGap(10, 10, 10)
                        .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addGroup(layout.createSequentialGroup()
                                .addGap(3, 3, 3)
                                .addComponent(jLabel5))
                            .addComponent(comboMaxBooks, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                        .addGap(10, 10, 10)
                        .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addComponent(jLabel6)
                            .addComponent(lblPhotoVal, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                        .addGap(16, 16, 16)
                        .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addComponent(btAdd)
                            .addComponent(btUpdate)
                            .addComponent(btDelete)))))
        );
    }// </editor-fold>//GEN-END:initComponents

    private void lblPhotoValMouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_lblPhotoValMouseClicked
        // Choose Image with file chooser
        File chosenImgFile = chooseImageFile();
        if(chosenImgFile == null){ return; }
        
        // Check if file extention is in *.jpg, *.jpeg, *.gif, *.png
        if(!isValidImgFile(chosenImgFile)){ return; }
        
        // Read image file and convert it to 150x150 size
        //( getScaledInstance() method in Image Class with Image.SCALE_SMOOTH ) 
        try{
            BufferedImage originalImg = ImageIO.read(chosenImgFile);
            
            //convert it to 150x150 size with
            BufferedImage convertedImg = convertImgSize(originalImg);
            lblPhotoVal.setIcon(new ImageIcon(convertedImg));
            bufImage = convertedImg; // For saving/ editing member's info
        } catch (IOException ex){
            JOptionPane.showMessageDialog(this,
                    "Cannot open Image file.",
                    "Reading File Error",
                    JOptionPane.ERROR_MESSAGE
            );
        }
    }//GEN-LAST:event_lblPhotoValMouseClicked

    private void tfSearchKeyReleased(java.awt.event.KeyEvent evt) {//GEN-FIRST:event_tfSearchKeyReleased
        String searchStr = tfSearch.getText();
        if(searchStr == null) { return; }
        if(searchStr.contains(" ")){ return; }
        
        modelMemberList.clear();
        for(Member m : memberList){
            if(m.isMatches(searchStr)){
                modelMemberList.addElement(m);
            }
        }
    }//GEN-LAST:event_tfSearchKeyReleased

    private void btClearActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btClearActionPerformed
        tfSearch.setText("");
        modelMemberList.clear();
        modelMemberList.addAll(memberList);
    }//GEN-LAST:event_btClearActionPerformed

    private void lstMemberValueChanged(javax.swing.event.ListSelectionEvent evt) {//GEN-FIRST:event_lstMemberValueChanged
        resetInputFields();
    }//GEN-LAST:event_lstMemberValueChanged

    private void btAddActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btAddActionPerformed
        try{
            String name = tfName.getText();
            String email = tfEmail.getText();

            int maxbooks = Member.MAXBOOK_VALUES[comboMaxBooks.getSelectedIndex()];
            Member member = new Member(0,name,email,bufImage,maxbooks,0);
            db.addMember(member);
            reloadMemberList();
            resetInputFields();
        } catch (InvalidMemberDataException | SQLException ex){
            ex.printStackTrace();
            JOptionPane.showMessageDialog(this,
                    "Invalid Data:"+ex.getMessage(),
                    "Data Validation Error",
                    JOptionPane.ERROR_MESSAGE
            );
        }
    }//GEN-LAST:event_btAddActionPerformed

    private void btUpdateActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btUpdateActionPerformed
        
        Member selMember = lstMember.getSelectedValue();
        if(selMember == null){
            JOptionPane.showMessageDialog(this,
                    "Please select one of member",
                    "Updating Member Error",
                    JOptionPane.ERROR_MESSAGE
            );
            return;
        }
        
        try{
            selMember.setName(tfName.getText());
            selMember.setEmail(tfEmail.getText());
            selMember.setMaxBookAllowed(Member.MAXBOOK_VALUES[comboMaxBooks.getSelectedIndex()]);
            selMember.setPhotoBufImg(bufImage);
            
            db.updateMember(selMember);
            reloadMemberList();
            resetInputFields();
        } catch (SQLException ex){
            JOptionPane.showMessageDialog(this,
                    "Database connection fail",
                    "Updating Member Error",
                    JOptionPane.ERROR_MESSAGE
            );
        } catch (InvalidMemberDataException ex){
            JOptionPane.showMessageDialog(this,
                    "Invalid Input:" + ex.getMessage(),
                    "Update Member Error",
                    JOptionPane.ERROR_MESSAGE
            );
        }
    }//GEN-LAST:event_btUpdateActionPerformed

    private void btDeleteActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btDeleteActionPerformed
        try {
            Member selMember = lstMember.getSelectedValue();
            if(selMember == null){ return; }
            
            db.deleteMember(selMember.getId());
            resetInputFields();
            reloadMemberList();
        } catch (SQLException ex) {
            JOptionPane.showMessageDialog(this,
                    "Database connection fail",
                    "Delete Member Error",
                    JOptionPane.ERROR_MESSAGE
            );
        }
    }//GEN-LAST:event_btDeleteActionPerformed


    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.JButton btAdd;
    private javax.swing.JButton btClear;
    private javax.swing.JButton btDelete;
    private javax.swing.JButton btUpdate;
    private javax.swing.JComboBox<String> comboMaxBooks;
    private javax.swing.JFileChooser fileChooser;
    private javax.swing.JLabel jLabel1;
    private javax.swing.JLabel jLabel2;
    private javax.swing.JLabel jLabel3;
    private javax.swing.JLabel jLabel4;
    private javax.swing.JLabel jLabel5;
    private javax.swing.JLabel jLabel6;
    private javax.swing.JScrollPane jScrollPane1;
    private javax.swing.JLabel lblIdVal;
    private javax.swing.JLabel lblPhotoVal;
    private javax.swing.JList<Member> lstMember;
    private javax.swing.JTextField tfEmail;
    private javax.swing.JTextField tfName;
    private javax.swing.JTextField tfSearch;
    // End of variables declaration//GEN-END:variables
}
