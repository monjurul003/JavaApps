/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package com.gpit.tech.es.cs.barafterduedate.notification;

import java.io.File;
import java.io.Serializable;
import java.sql.SQLException;
import java.util.Date;
import org.apache.log4j.Level;
import org.apache.log4j.Logger;

/**
 *
 * @author mdmizan
 */
public class ScheduleTask implements Serializable {

    private ConfigurationManager cm;

    public ScheduleTask() {
    }

    public void execute(String inputFile) throws Exception {
        Logger.getLogger(this.getClass().getName().toString()).log(Level.INFO, " Scheduler Started " + new Date(System.currentTimeMillis()));
        try {
            Logger.getLogger(this.getClass().getName().toString()).log(Level.INFO, " Get input configuration of Bar after Due Date Notification");
            this.cm = new ConfigurationManager().getConfiguration(new File(inputFile));
            this.cm.setInputFile(inputFile);
            String reportDay = cm.getReportGenDays();
            String[] tokens = reportDay.split("[,]");

            for (int i = 0; i < tokens.length; i++) {

                cm.setAccording2BarDay(tokens[i]);
                DueDateSmsNotification dueSms = new DueDateSmsNotification(this.cm);
                //System.out.println("Stask --- " + this.cm.getSqlQuery());
                WriteReportData wrData = new WriteReportData(this.cm);
                WriteInDBforFrontEnd writeDB = new WriteInDBforFrontEnd(this.cm);
                Logger.getLogger(this.getClass().getName().toString()).log(Level.INFO, " Initialization completed ");
                boolean status = false;
                System.out.println("b4 if"+ this.cm.getIsPrepareReport() );

                if (this.cm.getIsPrepareReport()) {
                  
//                    wrData.setQueryAccording2BarDay(tokens[i]);
                    status = wrData.getReport();
                    Logger.getLogger(this.getClass().getName().toString()).log(Level.INFO, " Report prepared of Bar after Due Date Notification ");
                }

                if (this.cm.getIsSms()) {
                    dueSms.insertIntoDb();
                }
                if (this.cm.getIsWriteDB()) {
                    writeDB.startInsertion();
                    Logger.getLogger(this.getClass().getName().toString()).log(Level.INFO, " Data inserted in DB-- Bar after Due Date Notification ");
                }
                this.cm = new ConfigurationManager().getConfiguration(new File(inputFile));
                this.cm.setInputFile(inputFile);
            }
            WriteReportData wrData1 = new WriteReportData(this.cm);
            
        } catch (SQLException ex) {
            Logger.getLogger(this.getClass().getName().toString()).log(Level.FATAL, ex.getMessage());
            ex.printStackTrace();
        } finally {
            System.gc();
        }
        Logger.getLogger(this.getClass().getName().toString()).log(Level.INFO, " Scheduler completed");
    }
};
