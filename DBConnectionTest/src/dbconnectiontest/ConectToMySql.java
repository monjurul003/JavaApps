/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package dbconnectiontest;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.ResultSetMetaData;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;
import java.util.logging.Level;
import java.util.logging.Logger;

/**
 *
 * @author Rasel
 */
public class ConectToMySql {

    private String connectionurl;
    private Connection conn;
    private PreparedStatement stm;
    private ResultSet rs;
    private ResultSetMetaData rsmd;

    public ConectToMySql() {
        //jdbc:sqlserver://[serverName[\instanceName][:portNumber]][;property=value[;property=value]]
        //String Connectionurl = "jdbc:sqlserver://localhost:1433;DatabaseName=YourDBName;user=UserName;Password=YourPassword";
        try {
            Class.forName("com.mysql.jdbc.Driver");
        } catch (ClassNotFoundException e) {
            System.out.println("Where is your MySQL JDBC Driver?");
            e.printStackTrace();
            return;
        }

        /*
         connection = DriverManager.getConnection("jdbc:mysql://localhost:3306/DataBaseName","user_name", "password");
         */
        try {
            this.conn = DriverManager.getConnection("jdbc:mysql://localhost:3306/PHONEBOOK", "mikhan", "mikhan");

        } catch (SQLException ex) {
            // handle any errors
            System.out.println("SQLException: " + ex.getMessage());
            System.out.println("SQLState: " + ex.getSQLState());
            System.out.println("VendorError: " + ex.getErrorCode());
        }
    }

    public ConectToMySql(String serverAddress, String port, String DBName, String userName, String password) {
        //jdbc:sqlserver://[serverName[\instanceName][:portNumber]][;property=value[;property=value]]
        //String Connectionurl = "jdbc:sqlserver://localhost:1433;DatabaseName=YourDBName;user=UserName;Password=YourPassword";
        try {
            Class.forName("com.mysql.jdbc.Driver");
        } catch (ClassNotFoundException e) {
            System.out.println("Where is your MySQL JDBC Driver?");
            e.printStackTrace();
            return;
        }

        /*
         connection = DriverManager.getConnection("jdbc:mysql://localhost:3306/DataBaseName","user_name", "password");
         */
        String connUrl = "jdbc:mysql://" + serverAddress + ":" + port + "/" + DBName;
        try {
            this.conn = DriverManager.getConnection(connUrl, userName, password);

        } catch (SQLException ex) {
            // handle any errors
            System.out.println("SQLException: " + ex.getMessage());
            System.out.println("SQLState: " + ex.getSQLState());
            System.out.println("VendorError: " + ex.getErrorCode());
        }
    }

    public ConectToMySql(String connectionurl) {

        this.connectionurl = connectionurl;
        this.conn = null;
        try {
            conn = DriverManager.getConnection(this.connectionurl);
        } catch (SQLException ex) {
            System.out.println("SQLException: " + ex.getMessage());
            System.out.println("SQLState: " + ex.getSQLState());
            System.out.println("VendorError: " + ex.getErrorCode());
        }
    }

    public void setConnectionUrl(String connectionurl) {
        this.connectionurl = connectionurl;
    }

    public String getConnectionUrl() {
        return this.connectionurl;
    }

    public Connection getConnection() {
        return this.conn;
    }

    public void setConnection(Connection conn) {
        this.conn = conn;
    }

    public void verifyData() {
        try {
            // Do something with the Connection
            List<Contacts> myList = new ArrayList<Contacts>();
            this.stm = this.conn.prepareStatement("SELECT * FROM CONTACTS"); // Create a Statement object with a sql query
            this.rs = this.stm.executeQuery();           // Get the ResultSet from the query
            this.rsmd = this.rs.getMetaData();                // Get result set meta data
            int numColumns = this.rsmd.getColumnCount();
            System.out.println(numColumns);

            for (int i = 1; i < numColumns + 1; i++) {
                if (i == 1) {
                    System.out.print(rsmd.getColumnName(i));
                } else {
                    System.out.print("," + rsmd.getColumnName(i));
                }
            }
            System.out.println("");


            while (rs.next()) {

                String test = "";
                for (int index = 1; index < numColumns + 1; index++) {
                    if (index == 1) {
                        System.out.print(rs.getString(index));
                    } else {
                        test +=rs.getString(index)+",";
                        System.out.print("," + rs.getString(index));
                    }
                }
                String[] str = test.split(",");
                Contacts cntact =new Contacts(str[0],str[1],str[2],str[3],str[4],str[5]);
                myList.add(cntact);
                System.out.println("");
            }
            System.out.println("Printing the arraylist");
             for (int i = 0; i < myList.size(); i++) {
                 myList.get(i).printContact();
             }


        } catch (SQLException ex) {
            Logger.getLogger(ConectToMySql.class.getName()).log(Level.SEVERE, null, ex);
        }
    }
}
