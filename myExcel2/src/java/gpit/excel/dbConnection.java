/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */

package gpit.excel;

import java.io.*;
import java.sql.*;
import java.util.Properties;
import java.util.Calendar;
import java.sql.Connection;
/**
 *
 * @author kamal.hossen
 */
public class dbConnection {

Properties properties = new Properties();

private String jdbc_url=null;
private String jdbc_driver=null;
private String jdbc_user=null;
private String jdbc_password=null;

public dbConnection()
    {

    }

public void readDBCredentials()
    {
        try
        {
        properties.load(new FileInputStream("config/systes.properties"));
        }
        catch (IOException e)
        {
            System.out.println("Could not read the properties file"+e.getMessage());
        }

        jdbc_url=properties.getProperty("jdbc.url");
	jdbc_driver=properties.getProperty("jdbc.driver");
	jdbc_user=properties.getProperty("jdbc.username");
	jdbc_password=properties.getProperty("jdbc.password");
    }

    public Connection getDBConnection()
    {
        Connection con=null;

        try
        {
            Class.forName("oracle.jdbc.driver.OracleDriver").newInstance();
            //con=DriverManager.getConnection(jdbc_url,jdbc_user,jdbc_password);
            //con=DriverManager.getConnection("jdbc:oracle:thin:@gprac-scan.grameenphone.com:1521:GPRACDB","ccapps", "ccapps99");
            con=DriverManager.getConnection("jdbc:oracle:thin:@gp-pc-4002876:1521:XE","kamal", "kamal");
        }
        catch(Exception e)
        {
            System.out.println("**** Database Connection Faild .........");
            e.printStackTrace();            
        }
                
       return con;
    }

}
