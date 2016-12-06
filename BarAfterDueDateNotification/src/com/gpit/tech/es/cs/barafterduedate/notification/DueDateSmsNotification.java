/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package com.gpit.tech.es.cs.barafterduedate.notification;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.ResultSetMetaData;
import java.sql.SQLException;
import java.sql.Statement;
import java.text.SimpleDateFormat;
import java.util.Date;
import org.apache.log4j.Logger;

/**
 *
 * @author monjurul.k
 */
public class DueDateSmsNotification {

    private Connection conn;
    private PreparedStatement stm;
    private Statement stm1;
    private ResultSet rs;
    private ResultSetMetaData rsmd;
    private String sqlQuery;
    public String sms, smsTable;
    static Logger logger = Logger.getLogger(DueDateSmsNotification.class);

    public DueDateSmsNotification(ConfigurationManager cm) throws SQLException {

        this.conn = cm.getDataSource().getConnection();

        this.sqlQuery = cm.getSqlQuery();

        this.sms = cm.getSms();

        this.smsTable =cm.getSmsTable();

        logger.info("DueDateSmsNotification:: construction completed");

    }

    public void setSmsAccording2BarDay(String day) {
        this.sqlQuery = this.sqlQuery.replaceAll("[?]", day);
        this.sms = this.sms.replaceAll("[?]", day);
        logger.info("DueDateSmsNotification:: Complete setSmsAccording2BarDay() for--" + day+" sms-- "+ this.sms);

    }

    public void insertIntoDb() throws SQLException {
        SimpleDateFormat sdf = new SimpleDateFormat("dd-MMM-yy");
        Date d = new Date();

        this.stm = this.conn.prepareStatement(this.sqlQuery); // Create a Statement object with a sql query

        this.stm1 = conn.createStatement();

        this.rs = this.stm.executeQuery();           // Get the ResultSet from the query

        this.rsmd = rs.getMetaData();
        // Get result set meta data
        while (this.rs.next()) {
            String sms_text =this.sms.replaceAll("[?]", rs.getString(14));
            stm1.executeUpdate("insert into "+ this.smsTable +" (DIRECTORY_NUMBER,SMS_TEXT,TO_BE_SEND_TIME) values('" + rs.getString(2) + "','" +sms_text + "','" + sdf.format(d) + "')");

        }
        logger.info("DueDateSmsNotification:: insertIntoDB() completed");
    }
}
