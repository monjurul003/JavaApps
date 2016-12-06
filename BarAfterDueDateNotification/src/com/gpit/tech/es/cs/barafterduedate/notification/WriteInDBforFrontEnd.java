package com.gpit.tech.es.cs.barafterduedate.notification;

import java.io.IOException;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import org.apache.log4j.Level;
import org.apache.log4j.Logger;

/**
 *
 * @author monjurul.k
 * Date: 22nd, November, 2012
 */
public class WriteInDBforFrontEnd {

    static Logger logger = Logger.getLogger(WriteInDBforFrontEnd.class);
    private Connection conn;
    public PreparedStatement stm;
    public PreparedStatement stm1, stm2, stm3;
    public ResultSet rs;
    public ConnectionToSql conn2sql;
    public String query;
    public String query1;
    public String sqlQuery1;
    public String updateTable;

    public WriteInDBforFrontEnd() {
    }

    public WriteInDBforFrontEnd(ConfigurationManager cm) throws SQLException {

        this.conn = cm.getDataSource().getConnection();
        this.conn2sql = new ConnectionToSql();
        this.conn2sql.query = cm.getQuery();
        this.query1 = cm.getQuery1();
        this.sqlQuery1 = cm.getSqlQuery1();
        this.updateTable =cm.getUpdateTable();
//        System.out.println("WriteInDBforFrontEnd constructor completed");
    }

    public void setAccording2BarDay(String day) {
        this.sqlQuery1 = this.sqlQuery1.replaceAll("[?]", day);
        this.query1 = this.query1.replaceAll("[?]", day);
        logger.info("WriteInDBforFrontEnd:: Complete setAccording2BarDay() for--" + day);
        System.out.println("WriteInDBforFrontEnd:: Query-"+this.query1 );
         System.out.println("WriteInDBforFrontEnd:: SQLQuery-"+this.sqlQuery1 );

    }

    public void startInsertion() {
        try {
            insertDB(this.query1, this.sqlQuery1);
        } catch (IOException ex) {
//            java.util.logging.Logger.getLogger(WriteInDBforFrontEnd.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
            logger.error("WriteInDBforFrontEnd:: startInsertion()--" + ex);
        }
        try {
            this.conn.close();
        } catch (SQLException ex) {
//            java.util.logging.Logger.getLogger(WriteInDBforFrontEnd.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
            logger.error("WriteInDBforFrontEnd:: startInsertion()--" + ex);
        }
        logger.info("WriteInDBforFrontEnd:: startInsertion()- Close connection object");
    }

    public boolean insertDB(String qry, String sqlQry) throws IOException {
        try {
            try {
                this.stm1 = this.conn.prepareStatement(qry); // Create a Statement object with a sql query
                int isWrite = this.stm1.executeUpdate();    // insert data to Db
                this.stm1.close();
                this.stm = this.conn.prepareStatement(sqlQry); // Create a Statement object with a sql query
                this.rs = this.stm.executeQuery(); // Get the ResultSet from the query
                logger.info("WriteInDBforFrontEnd::  Execute Query for BSCODE & MSISDN in WriteInDBforFrontEnd");

            } catch (SQLException ex) {
                logger.error("WriteInDBforFrontEnd:: " + ex);
            }
            logger.info("WriteInDBforFrontEnd::  Before fetching data from SQL server");
            while (this.rs.next()) {
                String msisdn = this.rs.getString(2);
//                System.out.print(msisdn+", ");
                String str = "";
                str = this.conn2sql.getData(msisdn);
                String delims = "[+]+";
                String[] tokens = str.split(delims);
                this.stm2 = this.conn.prepareStatement(createQuery(tokens, msisdn));
                int isWrite = this.stm2.executeUpdate();
                this.stm2.close();
//                System.out.println("after update " + String.valueOf(isWrite));
            }
            logger.info("WriteInDBforFrontEnd:: Insertion complete in database WriteInDBforFrontEnd");
//            System.out.println("after while");
            this.rs.close();
//            Logger.getLogger(this.getClass().getName().toString()).log(Level.INFO, " Close recordset object in WriteInDBforFrontEnd");
            this.stm.close();
//            Logger.getLogger(this.getClass().getName().toString()).log(Level.INFO, " Close statement object WriteInDBforFrontEnd");
        } catch (SQLException ex) {
            logger.error("WriteInDBforFrontEnd:: " + ex);
        }
        return true;
    }

    public String createQuery(String[] tokens, String msisdn) {
        String str = "";

        if (tokens.length > 1) {
            str = "Update "+ this.updateTable +" SET COMPANYNAME = '" + tokens[0] + "', KAM_NAME = '" + tokens[1] + "', KAM_ID = '" + tokens[2] + "', ZONE_NAME = '" + tokens[3] + "', REGION = '" + tokens[4] + " ' where MSISDN=" + msisdn;
        } else {
            str = "Update "+ this.updateTable +" SET COMPANYNAME  = 'NA',KAM_NAME = 'NA',KAM_ID = 'NA',ZONE_NAME = 'NA',REGION = 'NA'" + " where MSISDN =" + msisdn;
        }
//        System.out.println("Query--" +str);
        return str;
    }

    public boolean logTableUpdate(ConfigurationManager cm) throws SQLException {
        boolean isSuucess = false;
        this.stm3 = this.conn.prepareStatement(cm.getDeleteQuery()); // Delete data older than 30 days
        int isWrite = this.stm3.executeUpdate();
        logger.info("WriteInDBforFrontEnd:: logTableUpdate():: Deletetion completed of 30 days old data from table DUE_DATE_FINAL_NOTIFY_fntlog");
        this.stm3 = this.conn.prepareStatement(cm.getlogQuery()); // Insert everyday data in the DUE_DATE_FINAL_Notify_fntlog
        isWrite = this.stm3.executeUpdate();
        logger.info("WriteInDBforFrontEnd:: logTableUpdate():: Insertion completed to Log table DUE_DATE_FINAL_NOTIFY_fntlog");
        this.stm3 = this.conn.prepareStatement(cm.getTruncQuery()); // Truncate table DUE_DATE_FINAL_REPORT_frontend
        isWrite = this.stm3.executeUpdate();
        logger.info("WriteInDBforFrontEnd:: logTableUpdate():: Truncate table DUE_DATE_FINAL_NOTIFY_frontend ");
        this.stm3.close();
        if (isWrite != 0) {
            isSuucess = true;
        }
        return isSuucess;
    }
}
