/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */

package phonebook;

import swe.oop.gui.LogIn;

/**
 *
 * @author Administrator
 */
public class Main {

    /**
     * @param args the command line arguments
     */
public static void main(String args[]) {
    final LogIn logInObj = new LogIn();
        java.awt.EventQueue.invokeLater(new Runnable() {
            public void run() {
                logInObj.setVisible(true);
            }
        });
    }

}
