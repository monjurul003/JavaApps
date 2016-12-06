/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package dbconnectiontest;

import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.util.Properties;
import java.util.logging.Level;
import java.util.logging.Logger;

/**
 *
 * @author Rasel
 */
public class DBConnectionTest {

    /**
     * @param args the command line arguments
     */
    public static void main(String[] args) {
        // TODO code application logic here
        String database = "";
        String username = "";
        String password = "";
        String server = "";
        String port = "";
        Properties myProps = new Properties();
        try {
            FileInputStream fis = new FileInputStream("testPropFile.properties");
            myProps.load(fis);
            database = myProps.getProperty("database");
            username = myProps.getProperty("username");
            password = myProps.getProperty("password");
            server = myProps.getProperty("server");
            port = myProps.getProperty("port");
            ConnectionData conData = new ConnectionData(database, username, password, server, port);
            System.out.println("Hello "+ myProps.getProperty("database"));
        } catch (FileNotFoundException ex) {
            Logger.getLogger(DBConnectionTest.class.getName()).log(Level.SEVERE, null, ex);
        } catch (IOException ex) {
            Logger.getLogger(DBConnectionTest.class.getName()).log(Level.SEVERE, null, ex);
        }
        ContactManager cm = new ContactManager();
        Contacts contact = new Contacts("Andalib", "01711084262");
        cm.addContact(contact);
        cm.printContactList();
//        ConectToMySql cm = new ConectToMySql();
//        cm.verifyData();
    }
}
