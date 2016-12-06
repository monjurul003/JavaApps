/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */

package com.gpit.es.cs.emailsender;

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
 * @author monjurul.k
 */
public class WriteReportData {
    private SimpleJavaMailer jMailer;
    private Connection conn;
    private PreparedStatement stm;
    private ResultSet rs;
    private ResultSetMetaData rsmd;
    private int numColumns; 
    private String sqlQuery;
    private String inputFile;
    public File outputFile;
    private String outputDirectory;
    private String outputFileName;
    private boolean isLocal;
    private BufferedWriter buffWriter = null;
    public File tmpOutputFile;
    
    public WriteReportData(){}
    
    public WriteReportData(ConfigurationManager cm)  throws SQLException{
        SimpleDateFormat sdf = new SimpleDateFormat("ddMMyyyyHHmmss");
        Date d = new Date();
        cm.setOutputFileName(cm.getOutputFileName()+"_"+sdf.format(d)+".csv");
        this.conn = cm.getDataSource().getConnection();
        this.jMailer = cm.getEmail();
        this.sqlQuery = cm.getSqlQuery();
        this.inputFile = cm.getInputFile();
        this.outputFileName = cm.getOutputFileName();
        this.outputDirectory = cm.getOutputDirectory();
        this.outputFile = new File(this.outputDirectory+this.outputFileName);
        this.tmpOutputFile = new File(this.outputFileName);
        this.isLocal = cm.getIsLocal();
        System.out.println("WriteReportData:: output file name--"+ this.outputFileName);
        this.stm = this.conn.prepareStatement(this.sqlQuery);
        this.rs = this.stm.executeQuery();
        // Get result set meta data 
        this.rsmd = rs.getMetaData(); 
        this.numColumns = rsmd.getColumnCount(); 
    }
    
    public boolean getReport(){
        Logger.getLogger(this.getClass().getName().toString()).log(Level.INFO," Report generatation Started "+new Date(System.currentTimeMillis()));
        try {
            try {
                this.buffWriter = new BufferedWriter(new FileWriter(this.outputFile));
                for (int i=1; i<this.numColumns+1; i++) { 
                    if (i==1){
                        this.buffWriter.write(rsmd.getColumnName(i));
                    }
                    else{
                        this.buffWriter.write(","+rsmd.getColumnName(i));
                    }
                }
                this.buffWriter.newLine();
            } catch (IOException ex) {
                Logger.getLogger(this.getClass().getName().toString()).log(Level.FATAL,ex.getMessage());
                ex.printStackTrace();
            }
            while (this.rs.next()) {
                for (int index=1; index<this.numColumns+1; index++) {
                    if (index==1){
                        try {
                            this.buffWriter.write(this.rs.getString(index));
                        } catch (IOException ex) {
                            Logger.getLogger(this.getClass().getName().toString()).log(Level.FATAL,ex.getMessage());
                            ex.printStackTrace();
                        }
                    }
                    else{
                        try {
                            this.buffWriter.write("," + this.rs.getString(index));
                        } catch (IOException ex) {
                            Logger.getLogger(this.getClass().getName().toString()).log(Level.FATAL,ex.getMessage());
                            ex.printStackTrace();
                        }
                    }
                }
                try {
                    this.buffWriter.newLine();
                } catch (IOException ex) {
                    Logger.getLogger(this.getClass().getName().toString()).log(Level.FATAL,ex.getMessage());
                    ex.printStackTrace();
                }
            }
            try {
                this.buffWriter.close();
            } catch (IOException ex) {
                Logger.getLogger(this.getClass().getName().toString()).log(Level.FATAL,ex.getMessage());
                ex.printStackTrace();
            }
            this.rs.close();
            Logger.getLogger(this.getClass().getName().toString()).log(Level.INFO," Close recordset object");
            this.stm.cancel();
            Logger.getLogger(this.getClass().getName().toString()).log(Level.INFO," Close statement object");
            this.conn.close();
            Logger.getLogger(this.getClass().getName().toString()).log(Level.INFO," Close connection object");
        } catch (SQLException ex) {
            Logger.getLogger(this.getClass().getName().toString()).log(Level.FATAL,ex.getMessage());
            ex.printStackTrace();
        }
        Logger.getLogger(this.getClass().getName().toString()).log(Level.INFO," Report Genaration completed");
        return true;
    }
}
