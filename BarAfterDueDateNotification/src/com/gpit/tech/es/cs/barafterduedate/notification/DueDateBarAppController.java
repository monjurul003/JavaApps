/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package com.gpit.tech.es.cs.barafterduedate.notification;

import java.io.File;
import java.io.FileInputStream;
import java.io.IOException;
import java.sql.SQLException;
import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.Date;
import java.util.Properties;
import org.apache.log4j.Logger;

/**
 *
 * @author monjurul.k
 */
public class DueDateBarAppController {

    private ConfigurationManager cm;
    private Properties properties = new Properties();
    private File ctrlFile, confFile;
    private SleepTime st;
    static Logger logger = Logger.getLogger(DueDateBarAppController.class);

    public DueDateBarAppController(File confFile) throws IOException, Exception {

        this.confFile = confFile;
        this.cm = new ConfigurationManager().getConfiguration(confFile);
        ctrlFile = new File(cm.getCtrlDirectory() + "\\DDBN.CTRL");
        logger.info(cm.getCtrlDirectory() + "\\DDBN.CTRL");
        ctrlFile.createNewFile();
        logger.info("DueDateBarAppController Constructor completed");
    }

    public void sendEmail(String outputFilename) throws Exception {
        FileInputStream fis = null;
        try {
            fis = new FileInputStream("config.properties");
            logger.info(" Load Email Host");
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

        if (this.cm.getEmail().getIsEmail()) {
            this.cm.getEmail().setAttachedFileName(outputFilename);
            this.cm.getEmail().setAttachedFile(this.cm.getOutputDirectory() + outputFilename);
            this.cm.getEmail().setIsPrepareReport(this.cm.getIsPrepareReport());
            this.cm.getEmail().setOutputFile(new File(this.cm.getOutputDirectory() + outputFilename));
            logger.info(" Calling Email host to send Email");
            this.cm.getEmail().send();
        }

    }

    public void run() throws Exception {
        boolean poolSuccess = false, isTimeToRun;
        while (ctrlFile.exists()) {
            try {
//            Logger.getLogger(this.getClass().getName().toString()).log(Level.INFO, " Get input configuration of Bar after Due Date Notification");
                Calendar cal = Calendar.getInstance();
                int timeOfDay = cal.get(Calendar.HOUR_OF_DAY);
                this.cm = new ConfigurationManager().getConfiguration(this.confFile);
                logger.info("DueDateAppController-Run()::Get input configuration ");
                
                if (timeOfDay == cm.getStartHour()) {
                    WriteInDBforFrontEnd writeDB1 = new WriteInDBforFrontEnd(this.cm);
                    writeDB1.logTableUpdate(this.cm);
                    logger.info("DueDateAppController-Run():: LogTable updated and frontEnd table truncated");

                    boolean status = false;
//                    cm.setAccording2BarDay(tokens[i]);
                    DueDateSmsNotification dueSms = new DueDateSmsNotification(this.cm);
                    WriteReportData wrData = new WriteReportData(this.cm);
                    WriteInDBforFrontEnd writeDB = new WriteInDBforFrontEnd(this.cm);
                    logger.info("DueDateAppController-Run():: Initialization completed inside run()s for loop");

                    if (this.cm.getIsPrepareReport()) {
                        status = wrData.getReport();
                        logger.info("DueDateAppController-Run():: Report prepared and file name--" + cm.getOutputFileName());
                    }

                    if (this.cm.getIsSms()) {
                        dueSms.insertIntoDb();
                        logger.info(" DueDateAppController-Run():: Sms inserted complete");
                    }
                    if (this.cm.getIsWriteDB()) {
                        writeDB.startInsertion();
                        logger.info(" DueDateAppController-Run():: Data inserted in  DUE_DATE_FINAL_REPORT_FRONTEND  DB ");
                    }

//                    this.cm = new ConfigurationManager().getConfiguration(this.confFile);
                    poolSuccess = true;
//                        wrData = null;
                    writeDB = null;
                    if (status && cm.getEmail().getIsEmail()) {
                        logger.info("DueDateAppController-Run():: email send with file name" + wrData.getOutputFileName());
                        sendEmail(wrData.getOutputFileName());
                    }
                    logger.info("DueDateAppController-Run()::Before emalil send");

                    SimpleDateFormat sdf = new SimpleDateFormat("ddMMyyyyHHmmss");
                    Date d = new Date();

                } else {
                    try {
                        st = new SleepTime(cm.getStartHour());
                        logger.info("Going to sleep until " + st.getNextResumeTime());
                        st.waitUntilStartHour();
                    } catch (Exception ex) {
                        logger.error(ex);
                    }
                }
                if (poolSuccess) {
                    try {
                        st = new SleepTime(cm.getStartHour());
                        logger.info("Going to sleep until " + st.getNextResumeTime());
                        st.waitUntilStartHour();
                    } catch (Exception ex) {
                        logger.error(ex);
                    }
                }
                logger.info(" DueDateAppController-Run():: End of while. ");

            } catch (SQLException ex) {
                logger.fatal(ex.getMessage());
                ex.printStackTrace();
            } finally {
                System.gc();
            }
        }
    }
};
