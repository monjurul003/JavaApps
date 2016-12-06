/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package mik.phonebook.gui;

import mik.phonebook.processor.FileProcessor;
import mik.phonebook.entity.User;
import java.util.List;
import javax.swing.JPasswordField;
import javax.swing.JTextField;

/**
 *
 * @author Monjurul Islam Khan
 */
public class LogInForm extends javax.swing.JFrame {

    private List<User> userList;

    public JPasswordField getjPassword() {
        return jPassword;
    }

    public JTextField getTxtName() {
        return txtName;
    }

    /**
     * Creates new form LogInForm
     */
    public LogInForm() {
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
            java.util.logging.Logger.getLogger(LogInForm.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (InstantiationException ex) {
            java.util.logging.Logger.getLogger(LogInForm.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (IllegalAccessException ex) {
            java.util.logging.Logger.getLogger(LogInForm.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (javax.swing.UnsupportedLookAndFeelException ex) {
            java.util.logging.Logger.getLogger(LogInForm.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        }
        //</editor-fold>

        initComponents();
        this.lblErrorMsg.setVisible(false);
       
        
        FileProcessor fp = new FileProcessor();
        this.userList = fp.getUserList();

    }

    /**
     * This method is called from within the constructor to initialize the form.
     * WARNING: Do NOT modify this code. The content of this method is always
     * regenerated by the Form Editor.
     */
    @SuppressWarnings("unchecked")
    // <editor-fold defaultstate="collapsed" desc="Generated Code">//GEN-BEGIN:initComponents
    private void initComponents() {

        jPanelLogIn = new javax.swing.JPanel();
        lblName = new javax.swing.JLabel();
        txtName = new javax.swing.JTextField();
        lblPassword = new javax.swing.JLabel();
        jPassword = new javax.swing.JPasswordField();
        btnLogIn = new javax.swing.JButton();
        btnCancel = new javax.swing.JButton();
        lblErrorMsg = new javax.swing.JLabel();
        lblSignUp = new javax.swing.JLabel();
        jMenu = new javax.swing.JMenuBar();
        jMenuFile = new javax.swing.JMenu();
        jMenuItem1 = new javax.swing.JMenuItem();
        jMenuItem2 = new javax.swing.JMenuItem();
        jMenu2 = new javax.swing.JMenu();

        setDefaultCloseOperation(javax.swing.WindowConstants.EXIT_ON_CLOSE);
        setTitle("Phonebook");
        setBackground(new java.awt.Color(255, 255, 255));

        jPanelLogIn.setBackground(new java.awt.Color(240, 240, 248));
        jPanelLogIn.setBorder(javax.swing.BorderFactory.createTitledBorder(javax.swing.BorderFactory.createLineBorder(new java.awt.Color(0, 0, 0)), "Log In", javax.swing.border.TitledBorder.DEFAULT_JUSTIFICATION, javax.swing.border.TitledBorder.DEFAULT_POSITION, new java.awt.Font("Cambria", 1, 14), new java.awt.Color(0, 0, 153))); // NOI18N

        lblName.setFont(new java.awt.Font("Tahoma", 1, 12)); // NOI18N
        lblName.setText("User Name: ");

        lblPassword.setFont(new java.awt.Font("Tahoma", 1, 12)); // NOI18N
        lblPassword.setText("Password: ");

        btnLogIn.setFont(new java.awt.Font("Tahoma", 1, 12)); // NOI18N
        btnLogIn.setText("Sign In");
        btnLogIn.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnLogInActionPerformed(evt);
            }
        });

        btnCancel.setFont(new java.awt.Font("Tahoma", 1, 12)); // NOI18N
        btnCancel.setText("Cancel");
        btnCancel.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnCancelActionPerformed(evt);
            }
        });

        lblErrorMsg.setFont(new java.awt.Font("Tahoma", 1, 11)); // NOI18N
        lblErrorMsg.setForeground(new java.awt.Color(255, 0, 0));
        lblErrorMsg.setText("Log in error! ");

        lblSignUp.setFont(new java.awt.Font("Tahoma", 1, 11)); // NOI18N
        lblSignUp.setForeground(new java.awt.Color(0, 0, 255));
        lblSignUp.setText("Sign up");
        lblSignUp.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                lblSignUpMouseClicked(evt);
            }
        });

        javax.swing.GroupLayout jPanelLogInLayout = new javax.swing.GroupLayout(jPanelLogIn);
        jPanelLogIn.setLayout(jPanelLogInLayout);
        jPanelLogInLayout.setHorizontalGroup(
            jPanelLogInLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanelLogInLayout.createSequentialGroup()
                .addGap(34, 34, 34)
                .addGroup(jPanelLogInLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                    .addComponent(lblName, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                    .addComponent(lblPassword, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                .addGroup(jPanelLogInLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(jPanelLogInLayout.createSequentialGroup()
                        .addComponent(lblSignUp)
                        .addGap(0, 0, Short.MAX_VALUE))
                    .addGroup(jPanelLogInLayout.createSequentialGroup()
                        .addGroup(jPanelLogInLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addComponent(txtName)
                            .addComponent(jPassword))
                        .addGap(36, 36, 36))
                    .addGroup(jPanelLogInLayout.createSequentialGroup()
                        .addGroup(jPanelLogInLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addGroup(jPanelLogInLayout.createSequentialGroup()
                                .addComponent(btnLogIn)
                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                                .addComponent(btnCancel))
                            .addGroup(jPanelLogInLayout.createSequentialGroup()
                                .addGap(20, 20, 20)
                                .addComponent(lblErrorMsg)))
                        .addContainerGap(24, Short.MAX_VALUE))))
        );

        jPanelLogInLayout.linkSize(javax.swing.SwingConstants.HORIZONTAL, new java.awt.Component[] {btnCancel, btnLogIn});

        jPanelLogInLayout.setVerticalGroup(
            jPanelLogInLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanelLogInLayout.createSequentialGroup()
                .addComponent(lblErrorMsg)
                .addGap(14, 14, 14)
                .addGroup(jPanelLogInLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(lblName)
                    .addComponent(txtName))
                .addGap(18, 18, 18)
                .addGroup(jPanelLogInLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(lblPassword)
                    .addComponent(jPassword, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(lblSignUp)
                .addGap(7, 7, 7)
                .addGroup(jPanelLogInLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(btnLogIn)
                    .addComponent(btnCancel))
                .addGap(47, 47, 47))
        );

        jMenuFile.setText("File");

        jMenuItem1.setText("Options");
        jMenuFile.add(jMenuItem1);

        jMenuItem2.setText("Exit");
        jMenuItem2.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jMenuItem2ActionPerformed(evt);
            }
        });
        jMenuFile.add(jMenuItem2);

        jMenu.add(jMenuFile);

        jMenu2.setText("Edit");
        jMenu.add(jMenu2);

        setJMenuBar(jMenu);

        javax.swing.GroupLayout layout = new javax.swing.GroupLayout(getContentPane());
        getContentPane().setLayout(layout);
        layout.setHorizontalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(layout.createSequentialGroup()
                .addContainerGap()
                .addComponent(jPanelLogIn, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
        );
        layout.setVerticalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(layout.createSequentialGroup()
                .addContainerGap()
                .addComponent(jPanelLogIn, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
        );

        pack();
    }// </editor-fold>//GEN-END:initComponents

    private void jMenuItem2ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jMenuItem2ActionPerformed
        // TODO add your handling code here:
        this.dispose();
        System.exit(0);
    }//GEN-LAST:event_jMenuItem2ActionPerformed

    private void btnCancelActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnCancelActionPerformed
        // TODO add your handling code here:
        this.txtName.setText("");
        this.jPassword.setText("");
        this.lblErrorMsg.setVisible(false);
    }//GEN-LAST:event_btnCancelActionPerformed

    private void btnLogInActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnLogInActionPerformed
        // TODO add your handling code here:
        String userName = this.txtName.getText().toString();
        
        char[] pass = this.jPassword.getPassword();
        String password = new String(pass);
        boolean logInDone = false;
        for (int i = 0; i < this.userList.size(); i++) {
            if (userName.equals(this.userList.get(i).getUser_name()) && password.equals(this.userList.get(i).getPaasword())) {
                logInDone = true;
               
                break;
            }
        }
        
        if (logInDone) {
            this.txtName.setText("");
            this.jPassword.setText("");
            this.setVisible(false);
            final LogInDone loIndone = new LogInDone(userName);
            
//                    loIndone.setWelcomeMsg();
            loIndone.setLogInForm(this);
            /* Create and display the form */
            java.awt.EventQueue.invokeLater(new Runnable() {
                public void run() {

                    loIndone.setVisible(true);
                }
            });
        }

    }//GEN-LAST:event_btnLogInActionPerformed

    private void lblSignUpMouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_lblSignUpMouseClicked
        // TODO add your handling code here:
        
        final SignUpGui signUpGui = new SignUpGui();
        signUpGui.setLogInObj(this);
        this.setVisible(false);
        java.awt.EventQueue.invokeLater(new Runnable() {
            public void run() {
                signUpGui.setVisible(true);
            }
        });
    }//GEN-LAST:event_lblSignUpMouseClicked
//<editor-fold defaultstate="collapsed" desc=" Variables">
    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.JButton btnCancel;
    private javax.swing.JButton btnLogIn;
    private javax.swing.JMenuBar jMenu;
    private javax.swing.JMenu jMenu2;
    private javax.swing.JMenu jMenuFile;
    private javax.swing.JMenuItem jMenuItem1;
    private javax.swing.JMenuItem jMenuItem2;
    private javax.swing.JPanel jPanelLogIn;
    private javax.swing.JPasswordField jPassword;
    private javax.swing.JLabel lblErrorMsg;
    private javax.swing.JLabel lblName;
    private javax.swing.JLabel lblPassword;
    private javax.swing.JLabel lblSignUp;
    private javax.swing.JTextField txtName;
    // End of variables declaration//GEN-END:variables
    //</editor-fold>
    
}
