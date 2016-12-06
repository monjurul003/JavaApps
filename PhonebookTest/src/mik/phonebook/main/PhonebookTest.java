/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package mik.phonebook.main;

import mik.phonebook.gui.LogInForm;

/**
 *
 * @author Monjurul Islam Khan
 */
public class PhonebookTest {

    /**
     * @param args the command line arguments
     */
    public static void main(String[] args) {
        // TODO code application logic here

        
        final LogInForm logInform = new LogInForm();

        /* Create and display the form */
        java.awt.EventQueue.invokeLater(new Runnable() {
            public void run() {
                logInform.setVisible(true);
            }
        });

    }
}
