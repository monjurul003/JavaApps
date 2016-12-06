/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package MyJavaTestLib;

/**
 *
 * @author monjurul.k
 */
public class EmailAddressValidityChecker {

    private String email;

    public void setEmail(String email_id) {
        this.email = email_id;
    }

    public String getEmail() {
        return this.email;
    }

    public boolean checkValidity(String email_id) {

        if (email_id.matches("^[_A-Za-z0-9-]+(\\.[_A-Za-z0-9-]+)*@[A-Za-z0-9]+(\\.[A-Za-z0-9]+)*(\\.[A-Za-z]{2,})$")) {
            return true;
        }
       return false;
    }
}
