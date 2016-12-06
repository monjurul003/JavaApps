/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */

package com.gpit.es.cs.emailsender;

import java.io.File;
import java.io.FileInputStream;
import java.sql.SQLException;
import java.util.Date;
import java.util.Properties;
import org.apache.log4j.Level;
import org.apache.log4j.Logger;
/**
 *
 * @author monjurul.k
 */

public class ScheduleTask {
    
    private ConfigurationManager cm;
    private Properties properties = new Properties();
        
    public ScheduleTask(){
    }
    
    public void execute(String inputFile) throws Exception {
        Logger.getLogger(this.getClass().getName().toString()).log(Level.INFO," Scheduler Started "+new Date(System.currentTimeMillis()));
        try {
            Logger.getLogger(this.getClass().getName().toString()).log(Level.INFO," Get input configuration");
            this.cm = new ConfigurationManager().getConfiguration(new File(inputFile));
            this.cm.setInputFile(inputFile);
            Logger.getLogger(this.getClass().getName().toString()).log(Level.INFO," Prepare SQL query and write data into output file");
            WriteReportData wrData = new WriteReportData(this.cm);
            boolean status = false;
            FileInputStream fis = null;
            try {
                fis = new FileInputStream("config.properties");
                Logger.getLogger(this.getClass().getName().toString()).log(Level.INFO," Load Email Host");
                properties.load(fis);
                String host = properties.getProperty("email_host");
                this.cm.getEmail().setHost(host);
            } catch (Exception ex) {
                ex.printStackTrace(System.err);
            } finally {
                try {
                    fis.close();
                } catch (Exception ex) {
                    ex.printStackTrace(System.err);
                }
            }
            
            if (this.cm.getIsLocal() || this.cm.getEmail().getIsEmail()){
                status = wrData.getReport();
            }
            if (status){
                if (this.cm.getEmail().getIsEmail()){
                    this.cm.getEmail().setAttachedFileName(this.cm.getOutputFileName());
                    this.cm.getEmail().setAttachedFile(this.cm.getOutputDirectory()+this.cm.getOutputFileName());
                    this.cm.getEmail().setIsLocal(this.cm.getIsLocal());
                    this.cm.getEmail().setOutputFile(new File(this.cm.getOutputDirectory()+this.cm.getOutputFileName()));
                    Logger.getLogger(this.getClass().getName().toString()).log(Level.INFO," Calling Email host to send Email");
                    this.cm.getEmail().send();
                }
            }
            
        } catch (SQLException ex) {
            Logger.getLogger(this.getClass().getName().toString()).log(Level.FATAL,ex.getMessage());
            ex.printStackTrace();
        } finally {
            System.gc();
        }
        Logger.getLogger(this.getClass().getName().toString()).log(Level.INFO," Scheduler completed");
    }
}
