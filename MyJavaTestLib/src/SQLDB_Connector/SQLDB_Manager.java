/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package SQLDB_Connector;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

/**
 *
 * @author monjurul.k
 */
public class SQLDB_Manager {

     public void getPinpuk() {
//        String imsi ="470010630013767";
        String imsi ="470010000013164";
        String connectionurl = "jdbc:sqlserver://GPSQL1\\DB01:1433;DatabaseName=CPM;user=ecare_cpm;Password=ecare_cpm121;";
        Connection conn = null;
        PreparedStatement stmt = null;
        ResultSet rs = null;

        try {
            conn = DriverManager.getConnection(connectionurl);

        } catch (SQLException ex) {
            // handle any errors
           System.out.println("SQLException: " + ex.getMessage());
           System.out.println("SQLState: " + ex.getSQLState());
           System.out.println("VendorError: " + ex.getErrorCode());
        }
        try {
            stmt = conn.prepareStatement("Select * from VU_ECARE_PIN_PUK where IMSI_NO ='" + imsi + "'");
//            stmt = conn.prepareStatement("Select * from VU_ECARE_PIN_PUK where pin1 is null");
            rs = stmt.executeQuery();
            if(!rs.isBeforeFirst()){
                System.out.println("There is no data in SQL DB for IMSI_NO --" + imsi );
               
            }

            while (rs.next()) {
//                 System.out.println("There is no data in SQL DB for IMSI_NO --" + rs.getString("IMSI_NO") );
                System.out.println(rs.getString("PIN1"));
                System.out.println(rs.getString("PIN2"));
                System.out.println(rs.getString("PUK1"));
                System.out.println(rs.getString("PUK2"));
              System.out.println("Pin puk data has acquired from SQL DB! ");
            }
        } catch (SQLException ex) {
            System.out.println("Error in SQLDB_Mng while executing qry -"+ex.toString());
        }

    }
}
