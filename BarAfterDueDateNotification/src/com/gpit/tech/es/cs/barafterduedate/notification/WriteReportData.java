/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package com.gpit.tech.es.cs.barafterduedate.notification;

import java.io.BufferedWriter;
import java.io.File;
import java.io.FileWriter;
import java.io.IOException;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.ResultSetMetaData;
import java.sql.SQLException;
import java.text.SimpleDateFormat;
import java.util.Date;
import org.apache.log4j.Level;
import org.apache.log4j.Logger;

/**
 *
 * @author mdmizan
 */
public class WriteReportData {

    static Logger logger = Logger.getLogger(WriteReportData.class);
    private Connection conn;
    private PreparedStatement stm;
    private ResultSet rs;
    private ResultSetMetaData rsmd;
    private int numColumns;
    private String sqlQuery, query, connectionUrlSqldb01, connectionUrlSqldb02, outputFileName, outputDirectory, inputFile, day;
    public File outputFile, tmpOutputFile;
    private boolean isPrepareReport;
    private BufferedWriter buffWriter = null;
    public ConnectionToSql conn2sql;

    public WriteReportData() {
    }

    public WriteReportData(ConfigurationManager cm) throws SQLException {
        SimpleDateFormat sdf = new SimpleDateFormat("ddMMyyyyHHmmss");
        Date d = new Date();
//        cm.setOutputFileName(cm.getOutputFileName() + "_" + sdf.format(d) + ".csv");
        this.conn = cm.getDataSource().getConnection();
        this.sqlQuery = cm.getSqlQuery();
        this.inputFile = cm.getInputFile();
        logger.info("WriteReportData:: Constructor()== cm.getOutputFileName()-" + cm.getOutputFileName());
        this.outputFileName = cm.getOutputFileName() + "_" + sdf.format(d) + ".csv";
        this.outputDirectory = cm.getOutputDirectory();
//        this.outputFile = new File(this.outputDirectory + this.outputFileName);
//        this.tmpOutputFile = new File(this.outputFileName);
        this.isPrepareReport = cm.getIsPrepareReport();
        this.connectionUrlSqldb01 = cm.getConnectionUrlSqldb01();
        this.connectionUrlSqldb02 = cm.getConnectionUrlSqldb02();
//        System.out.println( "Inside constructor"+this.outputFileName );
//        this.conn2sql[0] = new ConnectionToSql(this.connectionUrlSqldb01);
//        this.conn2sql[1] = new ConnectionToSql(this.connectionUrlSqldb02);
//        System.out.println("inside constructor of " +sqlQuery);

        this.conn2sql = new ConnectionToSql(this.connectionUrlSqldb02);
        this.conn2sql.query = cm.query;
        logger.info("WriteReportData:: Construstor completed");

//        this.stm = this.conn.prepareStatement(this.sqlQuery); // Create a Statement object with a sql query
//        logger.info("WriteReportData:: Constructor--" + this.sqlQuery);
//        this.rs = this.stm.executeQuery();           // Get the ResultSet from the query
//        this.rsmd = rs.getMetaData();                // Get result set meta data
//        this.numColumns = rsmd.getColumnCount();    // Find number of columns in the table
//        logger.info("WriteReportData:: Construstor completed");
    }

    public boolean getReport() throws IOException, SQLException {
        this.outputFile = new File(this.outputDirectory + this.outputFileName);
        this.tmpOutputFile = new File(this.outputFileName);
        logger.info("WriteReportData:: getReport()--" + this.sqlQuery);
        if (this.sqlQuery.contains("?")) {
            this.sqlQuery = this.sqlQuery.replaceAll("[?]", day);
        }

        this.stm = this.conn.prepareStatement(this.sqlQuery); // Create a Statement object with a sql query
        this.rs = this.stm.executeQuery();           // Get the ResultSet from the query
        this.rsmd = rs.getMetaData();                // Get result set meta data
        this.numColumns = rsmd.getColumnCount();    // Find number of columns in the table


        String str = "";
        String strData = "";
        logger.info("WriteReportData:: Report generatation Started " + new Date(System.currentTimeMillis()));
        try {
            try {
                this.buffWriter = new BufferedWriter(new FileWriter(this.outputFile));
                for (int i = 1; i < this.numColumns + 1; i++) {
                    if (i == 1) {
                        str = rsmd.getColumnName(i);

                    } else {
                        if (i == 3) {
                            str = str + "," + this.conn2sql.createMetadata();
                            str = str + "," + rsmd.getColumnName(i);
                        } else {
                            str = str + "," + rsmd.getColumnName(i);
                        }
                    }
                }
                this.buffWriter.write(str);
                this.buffWriter.newLine();
                logger.info("WriteReportData:: Column header- " + str);
            } catch (IOException ex) {
                logger.fatal("WriteReportData:: " + ex.getMessage());
                ex.printStackTrace();
            }
            while (this.rs.next()) {
                str = "";
                for (int index = 1; index < this.numColumns + 1; index++) {
                    if (index == 1) {
                        str = this.rs.getString(index);
                    } else {
                        if (index == 3) {
                            str = str + "," + this.conn2sql.getReportData(this.rs.getString(2));
                            str = str + "," + this.rs.getString(index);

                        } else {
                            str = str + "," + this.rs.getString(index);
                        }
                    }
                }
                try {
                    this.buffWriter.write(str);
                    this.buffWriter.newLine();
                } catch (IOException ex) {
                    logger.fatal("WriteReportData:: " + ex.getMessage());
                }
            }
            try {
                this.buffWriter.close();
            } catch (IOException ex) {
                logger.fatal("WriteReportData::  " + ex.getMessage());
            }
            this.rs.close();
            logger.info("WriteReportData:: Close recordset object");
            this.stm.cancel();
            logger.info("WriteReportData::Close statement object");
            this.conn.close();
            logger.info("WriteReportData::Close connection object");
        } catch (SQLException ex) {
            logger.fatal("WriteReportData:: " + ex.getMessage());
        }
        logger.info("WriteReportData:: Report Genaration completed");
        return true;
    }

//    public void setQueryAccording2BarDay(String day) {
//        this.sqlQuery = this.sqlQuery.replaceAll("[?]", day);
//        this.outputFileName = this.outputFileName.replaceAll("[?]", day);
//
//        //System.out.println(day+this.sqlQuery);
//    }
    public void setQueryAccording2BarDay(String day) {
        this.day = day;
        this.sqlQuery = this.sqlQuery.replaceAll("[?]", day);
        this.outputFileName = this.outputFileName.replaceAll("[?]", day);
        logger.info("WriteReportData:: Complete setAccording2BarDay() for--" + this.sqlQuery);
    }

     public String getsqlQuery() {
        return this.sqlQuery;
    }

    public void setsqlQuery(String sqlQuery) {
        this.sqlQuery = sqlQuery;
    }

      public String getOutputFileName() {
        return this.outputFileName;
    }

    public void setOutputFileName(String outputFileName) {
        this.outputFileName = outputFileName;
    }
    public String getConnectionUrlSqldb01() {
        return this.connectionUrlSqldb01;
    }

    public void setConnectionUrlSqldb01(String connectionUrlSqldb01) {
        this.connectionUrlSqldb01 = connectionUrlSqldb01;
    }

    public String getConnectionUrlSqldb02() {
        return this.connectionUrlSqldb02;
    }

    public void setConnectionUrlSqldb02(String connectionUrlSqldb02) {
        this.connectionUrlSqldb02 = connectionUrlSqldb02;
    }
}
