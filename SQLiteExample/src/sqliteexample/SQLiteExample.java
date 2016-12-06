/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package sqliteexample;

import java.io.File;
import java.io.IOException;
import java.util.ArrayList;
import java.util.logging.Level;
import java.util.logging.Logger;

/**
 *
 * @author Daffodil PC
 */
public class SQLiteExample {

    /**
     * @param args the command line arguments
     */
    public static void main(String[] args) {
        // TODO code application logic here
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
            java.util.logging.Logger.getLogger(GUI.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (InstantiationException ex) {
            java.util.logging.Logger.getLogger(GUI.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (IllegalAccessException ex) {
            java.util.logging.Logger.getLogger(GUI.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (javax.swing.UnsupportedLookAndFeelException ex) {
            java.util.logging.Logger.getLogger(GUI.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        }
        //</editor-fold>

        String workingDir = System.getProperty("user.dir");
        File dir = new File(workingDir + File.separator + "check.dat");

        // Tests whether the directory denoted by this abstract pathname exists.
        boolean exists = dir.exists();
        if(!exists){
            try {
                dir.createNewFile();
            } catch (IOException ex) {
                Logger.getLogger(SQLiteExample.class.getName()).log(Level.SEVERE, null, ex);
            }
        }
       

        SqLiteConnectionManager sqlMng = new SqLiteConnectionManager();
        sqlMng.createTableExample();
        sqlMng.selectExample();
        if (exists == false) {
            sqlMng.insertExample();
        }
        ArrayList<Contact> list = sqlMng.selectAndReturnAsAList();
        /* Create and display the form */
        final GUI guiObj = new GUI(list);
        java.awt.EventQueue.invokeLater(new Runnable() {
            public void run() {
                guiObj.setVisible(true);
            }
        });
    }

}
