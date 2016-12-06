package com.gpit.tech.es.cs.barafterduedate.notification;

//import java.sql.*;
import java.io.IOException;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.ResultSetMetaData;
import java.sql.SQLException;
//import java.util.logging.Level;
//import java.util.logging.Logger;
import org.apache.log4j.Logger;

/**
 *
 * @author monjurul islam khan
 */
public class ConnectionToSql {

    static Logger logger = Logger.getLogger(ConnectionToSql.class);
    private String connectionurl;
    private Connection conn;
    private int numColumns = 0;
    public String query;

    public ConnectionToSql() {
        //String Connectionurl = "jdbc:sqlserver://localhost:1433;DatabaseName=YourDBName;user=UserName;Password=YourPassword";
        this.connectionurl = "jdbc:sqlserver://sqldb02\\sql02:1433;DatabaseName=BSI;user=dbfccs;Password=123;";
        try {
            this.conn = DriverManager.getConnection(this.connectionurl);

        } catch (SQLException ex) {
            // handle any errors
            System.out.println("SQLException: " + ex.getMessage());
            System.out.println("SQLState: " + ex.getSQLState());
            System.out.println("VendorError: " + ex.getErrorCode());
        }
        logger.info("ConnectionToSql:: Constructor completed");
    }

    public ConnectionToSql(String connectionurl) {

        this.connectionurl = connectionurl;
        this.conn = null;
        try {
            conn = DriverManager.getConnection(this.connectionurl);
        } catch (SQLException ex) {
            System.out.println("SQLException: " + ex.getMessage());
            System.out.println("SQLState: " + ex.getSQLState());
            System.out.println("VendorError: " + ex.getErrorCode());
        }
        logger.info("ConnectionToSql:: Constructor completed");
    }

    /*
    This function will return  COMPANYNAME, KAM_NAME, KAM_ID, ZONE_NAME, REGION, values
    from MySQL DB BSI. for wriiteReportData
     * Created by monjurul.k
     * 26th, November, 2012
     */
    public String getReportData(String msisdn) throws SQLException, IOException {
        PreparedStatement stm = null;
        ResultSet rs = null;
        String str = "";
        try {
            stm = this.conn.prepareStatement(this.query + msisdn);  // Create a Statement object with a sql query
            rs = stm.executeQuery();           // Get the ResultSet from the query
            boolean hasRecord = false;
            this.numColumns = rs.getMetaData().getColumnCount();

            while (rs.next()) {
                hasRecord = true;
                for (int index = 1; index < this.numColumns + 1; index++) {
                    // System.out.print(rs.getString(index) + " ");
                    if (index == 1) {
                        str = rs.getString(index).replace(",", ";"); //company name contains (,) and csv files separated by (,) so we replaace it
                    } else if (index == 2) {
                        str = str + "," + rs.getString(index).replaceAll("[0-9()+-.^:,]", ""); //Sometimes KAM name contains mobile number so we replace it
                    } else {
                        str = str + "," + rs.getString(index);
                    }
                }
            }
            if (hasRecord == false) {
                for (int index = 1; index < this.numColumns + 1; index++) {
                    if (index == 1) {
                        str = "" + ",";
                    } else {
                        if (index == this.numColumns) {
                            
                        } else {
                            str = str + ",";
                        }
                    }
                }
            }
            rs.close();
            stm.close();
        } catch (SQLException ex) {
//            Logger.getLogger(this.getClass().getName().toString()).log(Level.SEVERE, ex.getMessage());
            logger.error("ConnectionToSql- getReportData():: SQL exception--" + ex.getMessage());
        }
//        System.out.println(str);
        return str;
    }

    /*
    This function will return  COMPANYNAME, KAM_NAME, KAM_ID, ZONE_NAME, REGION, values
    from MySQL DB BSI. for wriiteReportData
     * Created by monjurul.k
     * 26th, November, 2012
     */
    public String getData(String msisdn) throws SQLException, IOException {
        PreparedStatement stm = null;
        ResultSet rs = null;
        String str = "";
        try {
            stm = this.conn.prepareStatement(this.query + msisdn);  // Create a Statement object with a sql query
            rs = stm.executeQuery();           // Get the ResultSet from the query
//            Logger.getLogger(this.getClass().getName().toString()).log(Level.INFO, "Execute query for my sql database " + msisdn);
//            System.out.println(this.query+msisdn);
            boolean hasRecord = false;
            this.numColumns = rs.getMetaData().getColumnCount();
            while (rs.next()) {
                hasRecord = true;
                for (int index = 1; index < this.numColumns + 1; index++) {
                    if (index == 1) {
                        str = rs.getString(index);
                    } else if (index == 2) {
                        //Some name contains (0177)- type character so we have to remove nontext
                        str = str + "+" + rs.getString(index).replaceAll("[0-9()+-.^:,]", "");
                    } else {
                        str = str + "+" + rs.getString(index);
                    }
                }
            }
//            System.out.println(msisdn+" Details-- "+str);

            rs.close();
            stm.close();
        } catch (SQLException ex) {
            logger.error("ConnectionToSql- getReportData():: SQL exception--" + ex.getMessage());
        }
        return str;
    }

//This function will create columne in the output file
    public String createMetadata() throws SQLException {
        String str = "";
        PreparedStatement stm = null;
        ResultSet rs = null;
        ResultSetMetaData rsmd = null;
        try {
            // this query is just for making dummy table with same header
            String dummyQuery = "SELECT TOP 1 [companyName],[name]AS [KAM NAME],[employeeId] AS [KAM ID],[Zone] As [Zone Name],[Region]  FROM [BSI].[dbo].[TBL_FCCS]";
            stm = this.conn.prepareStatement(dummyQuery);
            rs = stm.executeQuery(); // Get the ResultSet from the query
            rsmd = rs.getMetaData(); // Get result set meta data
            this.numColumns = rsmd.getColumnCount();
        } catch (SQLException ex) {
            logger.error("ConnectionToSql- createMetaData():: SQL exception--" + ex.getMessage());
        }
        for (int i = 1; i < this.numColumns + 1; i++) {

            if (i == 1) {
                str = rsmd.getColumnName(i);
            } else {
                str = str + "," + rsmd.getColumnName(i);
            }
        }
        rs.close();
        stm.close();
        logger.info("ConnectionToSql:: Meta data created");
        return str;
    }
}
