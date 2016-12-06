/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package sqlitedbconnectiontest;

import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.sql.Connection;
import java.sql.DriverManager;
import java.util.Properties;
import java.util.logging.Level;
import java.util.logging.Logger;

/**
 *
 * @author Daffodil PC
 */
public class SqLiteDBConnectionTest {

     public void loadProperties() {

        Properties myProps = new Properties();
        try {
            FileInputStream fis = new FileInputStream("myProperties.properties");
            myProps.load(fis);
            String name = myProps.getProperty("name");
            System.out.println(name);
        } catch (FileNotFoundException ex) {
            Logger.getLogger(SqLiteDBConnectionTest.class.getName()).log(Level.SEVERE, null, ex);
        } catch (IOException ex) {
            Logger.getLogger(SqLiteDBConnectionTest.class.getName()).log(Level.SEVERE, null, ex);
        }
    }
    /**
     * @param args the command line arguments
     */
    public static void main(String[] args) {
        // TODO code application logic here

        Connection c = null;
        SqLiteConnectionManager sqlMng = new SqLiteConnectionManager();
//        sqlMng.createTableExample();
//        sqlMng.insertExample();
        sqlMng.selectExample();
                
        
    }

}
