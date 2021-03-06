/**
 * Program : Book and Member in library Management program
 * Purpose : Manage Books in library( title, author, copies, status, ...)
 *           Manage Member information (name, e-mail, number of books can loan, ... )
 *           Manage loans (loan date, due date, return date, ... )
 * 
 * Function : Manage members - Add, Edit, and Delete member's information. filter list by keyword
 *            Manage books - Add, Edit, and Delete book's information. filter list by keyword
 *            Checkout book - User can checkout available books if user didn't have overdue books or loan max amount books
 *            Return book - Return book
 *            View books rented - show list of rented books by orders and categories
 * 
 * Script by : Donghyeok Seo
 * Date : 2020.02.21
 */

package pkgLibrary;

public class LibraryManager extends javax.swing.JFrame {
    
    public LibraryManager() {
        initComponents();

    }

    /**
     * This method is called from within the constructor to initialize the form.
     * WARNING: Do NOT modify this code. The content of this method is always
     * regenerated by the Form Editor.
     */
    @SuppressWarnings("unchecked")
    // <editor-fold defaultstate="collapsed" desc="Generated Code">//GEN-BEGIN:initComponents
    private void initComponents() {

        dlgMember = new javax.swing.JDialog();
        penalDlgMember1 = new pkgLibrary.PenalDlgMember();
        dlgBook = new javax.swing.JDialog();
        penalDlgBook1 = new pkgLibrary.PenalDlgBook();
        dlgCheckout = new javax.swing.JDialog();
        penalDlgCheckout2 = new pkgLibrary.PenalDlgCheckout();
        dlgRentedBook = new javax.swing.JDialog();
        penalDlgRentedBook1 = new pkgLibrary.PenalDlgRentedBook();
        btManageMembers = new javax.swing.JButton();
        brCheckout = new javax.swing.JButton();
        btViewBooks = new javax.swing.JButton();
        btManageBooks = new javax.swing.JButton();
        btReturnBook = new javax.swing.JButton();
        jMenuBar1 = new javax.swing.JMenuBar();
        jMenu1 = new javax.swing.JMenu();

        javax.swing.GroupLayout dlgMemberLayout = new javax.swing.GroupLayout(dlgMember.getContentPane());
        dlgMember.getContentPane().setLayout(dlgMemberLayout);
        dlgMemberLayout.setHorizontalGroup(
            dlgMemberLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(dlgMemberLayout.createSequentialGroup()
                .addContainerGap()
                .addComponent(penalDlgMember1, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
        );
        dlgMemberLayout.setVerticalGroup(
            dlgMemberLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(dlgMemberLayout.createSequentialGroup()
                .addContainerGap()
                .addComponent(penalDlgMember1, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                .addContainerGap())
        );

        javax.swing.GroupLayout dlgBookLayout = new javax.swing.GroupLayout(dlgBook.getContentPane());
        dlgBook.getContentPane().setLayout(dlgBookLayout);
        dlgBookLayout.setHorizontalGroup(
            dlgBookLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(dlgBookLayout.createSequentialGroup()
                .addContainerGap()
                .addComponent(penalDlgBook1, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
        );
        dlgBookLayout.setVerticalGroup(
            dlgBookLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(dlgBookLayout.createSequentialGroup()
                .addContainerGap()
                .addComponent(penalDlgBook1, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
        );

        dlgCheckout.getContentPane().setLayout(new java.awt.CardLayout());
        dlgCheckout.getContentPane().add(penalDlgCheckout2, "card2");

        dlgRentedBook.setResizable(false);

        javax.swing.GroupLayout dlgRentedBookLayout = new javax.swing.GroupLayout(dlgRentedBook.getContentPane());
        dlgRentedBook.getContentPane().setLayout(dlgRentedBookLayout);
        dlgRentedBookLayout.setHorizontalGroup(
            dlgRentedBookLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(dlgRentedBookLayout.createSequentialGroup()
                .addContainerGap()
                .addComponent(penalDlgRentedBook1, javax.swing.GroupLayout.DEFAULT_SIZE, 689, Short.MAX_VALUE)
                .addContainerGap())
        );
        dlgRentedBookLayout.setVerticalGroup(
            dlgRentedBookLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, dlgRentedBookLayout.createSequentialGroup()
                .addContainerGap(23, Short.MAX_VALUE)
                .addComponent(penalDlgRentedBook1, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addContainerGap())
        );

        setDefaultCloseOperation(javax.swing.WindowConstants.EXIT_ON_CLOSE);

        btManageMembers.setText("Manage members");
        btManageMembers.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btManageMembersActionPerformed(evt);
            }
        });

        brCheckout.setText("Checkout book");
        brCheckout.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                brCheckoutActionPerformed(evt);
            }
        });

        btViewBooks.setText("View books rented");
        btViewBooks.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btViewBooksActionPerformed(evt);
            }
        });

        btManageBooks.setText("Manage books");
        btManageBooks.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btManageBooksActionPerformed(evt);
            }
        });

        btReturnBook.setText("Return book");
        btReturnBook.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btReturnBookActionPerformed(evt);
            }
        });

        jMenu1.setText("File");
        jMenuBar1.add(jMenu1);

        setJMenuBar(jMenuBar1);

        javax.swing.GroupLayout layout = new javax.swing.GroupLayout(getContentPane());
        getContentPane().setLayout(layout);
        layout.setHorizontalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(layout.createSequentialGroup()
                .addContainerGap()
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(layout.createSequentialGroup()
                        .addComponent(btManageMembers, javax.swing.GroupLayout.PREFERRED_SIZE, 124, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addGap(18, 18, 18)
                        .addComponent(brCheckout, javax.swing.GroupLayout.PREFERRED_SIZE, 124, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addGap(18, 18, 18)
                        .addComponent(btViewBooks, javax.swing.GroupLayout.PREFERRED_SIZE, 124, javax.swing.GroupLayout.PREFERRED_SIZE))
                    .addGroup(layout.createSequentialGroup()
                        .addComponent(btManageBooks, javax.swing.GroupLayout.PREFERRED_SIZE, 124, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addGap(18, 18, 18)
                        .addComponent(btReturnBook, javax.swing.GroupLayout.PREFERRED_SIZE, 124, javax.swing.GroupLayout.PREFERRED_SIZE)))
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
        );
        layout.setVerticalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(layout.createSequentialGroup()
                .addContainerGap()
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(btManageMembers)
                    .addComponent(brCheckout)
                    .addComponent(btViewBooks))
                .addGap(18, 18, 18)
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(btManageBooks)
                    .addComponent(btReturnBook))
                .addContainerGap(19, Short.MAX_VALUE))
        );

        pack();
    }// </editor-fold>//GEN-END:initComponents

    private void btManageMembersActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btManageMembersActionPerformed
        penalDlgMember1.reloadMemberList(); //reload dlgMember's Member list
        dlgMember.pack();
        dlgMember.setVisible(true);
    }//GEN-LAST:event_btManageMembersActionPerformed

    private void brCheckoutActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_brCheckoutActionPerformed
        penalDlgCheckout2.dlgFor = Button.Checkout;
        penalDlgCheckout2.reloadMemberList();
        penalDlgCheckout2.initDlgCheckoutTaps();
        dlgCheckout.pack();
        dlgCheckout.setVisible(true);
    }//GEN-LAST:event_brCheckoutActionPerformed

    private void btManageBooksActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btManageBooksActionPerformed
        penalDlgBook1.reloadBookList();
        dlgBook.pack();
        dlgBook.setVisible(true);
        
    }//GEN-LAST:event_btManageBooksActionPerformed

    private void btReturnBookActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btReturnBookActionPerformed
        penalDlgCheckout2.dlgFor = Button.Return;
        penalDlgCheckout2.reloadMemberList();
        penalDlgCheckout2.initDlgCheckoutTaps();
        dlgCheckout.pack();
        dlgCheckout.setVisible(true);
    }//GEN-LAST:event_btReturnBookActionPerformed

    private void btViewBooksActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btViewBooksActionPerformed
        dlgRentedBook.pack();
        penalDlgRentedBook1.reloadLoanList();
        dlgRentedBook.setVisible(true);
    }//GEN-LAST:event_btViewBooksActionPerformed

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
            java.util.logging.Logger.getLogger(LibraryManager.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (InstantiationException ex) {
            java.util.logging.Logger.getLogger(LibraryManager.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (IllegalAccessException ex) {
            java.util.logging.Logger.getLogger(LibraryManager.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (javax.swing.UnsupportedLookAndFeelException ex) {
            java.util.logging.Logger.getLogger(LibraryManager.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        }
        //</editor-fold>
        //</editor-fold>
        //</editor-fold>
        //</editor-fold>

        /* Create and display the form */
        java.awt.EventQueue.invokeLater(new Runnable() {
            public void run() {
                new LibraryManager().setVisible(true);
            }
        });
    }

    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.JButton brCheckout;
    private javax.swing.JButton btManageBooks;
    private javax.swing.JButton btManageMembers;
    private javax.swing.JButton btReturnBook;
    private javax.swing.JButton btViewBooks;
    private javax.swing.JDialog dlgBook;
    private javax.swing.JDialog dlgCheckout;
    private javax.swing.JDialog dlgMember;
    private javax.swing.JDialog dlgRentedBook;
    private javax.swing.JMenu jMenu1;
    private javax.swing.JMenuBar jMenuBar1;
    private pkgLibrary.PenalDlgBook penalDlgBook1;
    private pkgLibrary.PenalDlgCheckout penalDlgCheckout2;
    private pkgLibrary.PenalDlgMember penalDlgMember1;
    private pkgLibrary.PenalDlgRentedBook penalDlgRentedBook1;
    // End of variables declaration//GEN-END:variables
}