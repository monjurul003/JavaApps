/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package db.connection;

import db.entity.ConnectionData;
import db.entity.Contacts;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.util.Properties;
import org.apache.log4j.Logger;
import org.apache.log4j.xml.DOMConfigurator;

/**
 *
 * @author Rasel
 */
public class DBConnectionTest {

    /**
     * @param args the command line arguments
     */
    static Logger logger = Logger.getLogger(DBConnectionTest.class);
    public static String database = "";
    public static String username = "";
    public static String password = "";
    public static String server = "";
    public static String port = "";
    public static String workingDir = "";

    public static void loggerInitialization() {
        workingDir = System.getProperty("user.dir");
        DOMConfigurator.configure(workingDir + "\\config\\log4jConfig.xml");
    }

    public static void getPropertyFromFile() {

        Properties myProps = new Properties();
        try {
            FileInputStream fis = new FileInputStream("testPropFile.properties");
            myProps.load(fis);
            database = myProps.getProperty("database");
            username = myProps.getProperty("username");
            password = myProps.getProperty("password");
            server = myProps.getProperty("server");
            port = myProps.getProperty("port");
            
            System.out.println("Hello " + myProps.getProperty("database"));
            logger.info("Hello " + myProps.getProperty("database"));
        } catch (FileNotFoundException ex) {
            logger.fatal("Fatal Error: " + ex);
        } catch (IOException ex) {
            logger.fatal("Fatal Error: " + ex);
        }
    }

    public static void main(String[] args) {
        // TODO code application logic here

        loggerInitialization();
        getPropertyFromFile();
//        ConnectionData conData = new ConnectionData(database, username, password, server, port);

        System.out.println(workingDir);
        //example of write info log
        logger.info("this is a info message -- " + workingDir + "\\config\\log4jConfig.xml");
        //example of write fatal log
        logger.fatal("This is a fatal message");

        //example of write error log
        logger.error("This is a error message");
        //example of write warn log
        logger.warn("This is a warn message");

//        ContactManager cm = new ContactManager();
//        Contacts contact = new Contacts("Andalib", "01711084262");
//        cm.addContact(contact);
//        cm.printContactList();
//        ConectToMySql cm = new ConectToMySql();
//        cm.verifyData();
    }
}
