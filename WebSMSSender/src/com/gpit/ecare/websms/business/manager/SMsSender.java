/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package com.gpit.ecare.websms.business.manager;

import java.io.File;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.Date;
import org.apache.log4j.Level;
import org.apache.log4j.Logger;
import com.gpit.ecare.websms.configuration.manager.ConfigurationManager;

/**
 *
 * @author monjurul.k
 */
public class SMsSender {

    static Logger logger = Logger.getLogger(SMsSender.class);
    private ConfigurationManager cm;
    private Connection conn;
    private PreparedStatement stmQuery;
    
    private ResultSet rsQuery;
    private String sqlQuery;
    private PreparedStatement stmUpdate;
    private File ctrlFile;

    public SMsSender() {
    }

    public SMsSender(File inputFile) {
        try {
            this.cm = new ConfigurationManager().getConfiguration(inputFile);
            this.conn = this.cm.getDataSource().getConnection();
            ctrlFile = new File(cm.getCtrlDirectory() + "\\MMSC.CTRL");
            logger.info(cm.getCtrlDirectory() + "\\MMSC.CTRL");
            ctrlFile.createNewFile();
            this.executeSMSSchedule();
        } catch (Exception ex) {
            logger.fatal(ex);
        }
    }

    public void run() throws Exception {

        while (ctrlFile.exists()) {
            this.conn = this.cm.getDataSource().getConnection();
            this.executeSMSSchedule();
            this.initQueryDataSOurce();

            int i = 0;
            while (this.rsQuery.next()) {
                updateRequestStatus(rsQuery.getString("ID"));

                logger.info("WebSMSSender:run():: sms while-prepare xml " + new Date(System.currentTimeMillis()));
                String sendXMLMsg = "<?xml version=\"1.0\" encoding=\"utf-8\"?>" +
                        "<service_request>" +
                        "<user_id>ecare</user_id>" +
                        "<password>e@care121</password>" +
                        "<msisdn>" + this.rsQuery.getString("MSISDN") + "</msisdn>" +
                        "<service>ecaresms</service>" +
                        "<action_string>send</action_string>" +
                        "<origin_host_name>ecare</origin_host_name>" +
                        "<message_content>" + this.rsQuery.getString("SMS_CONTENT") + "</message_content>" +
                        "<sender_address>" + this.rsQuery.getString("sender_msisdn") + "</sender_address>" +
                        "<charge_status>1</charge_status>" +
                        "<charged_party>" + this.rsQuery.getString("USER_ID") + "</charged_party>" +
                        "<sessionid>" + this.rsQuery.getString("ID") + "</sessionid>" +
                        "<opid>wsc</opid>" +
                        "</service_request>";

                logger.info(" WebSMSSender:run inside sms while- recipient-- " + this.rsQuery.getString("MSISDN") + " sender-- " + this.rsQuery.getString("sender_msisdn"));
                HTTPRequestOverXML httpRequest = new HTTPRequestOverXML();
                String[] httpResponse = httpRequest.executeHTTPRequest(sendXMLMsg);
                String sqlUpdateQuery = "update wsc_sms_mms_log set request_status='" + httpResponse[1] + "', request_remarks='" + httpResponse[2] + "', request_date=sysdate where id=" + this.rsQuery.getString("ID");
                logger.info(" WebSMSSender:run inside while sms- response -" + httpResponse[1]);
                this.executeUpdateDataSOurce(sqlUpdateQuery);
                this.closeUpdateDataSOurce();
            }
            this.closeQueryDataSOurce();
            System.gc();
            System.out.println("Going to sleep for 1min!");
            logger.info("WebSMSSender: SMS sending complete, Going to sleep for 1 min " + new Date(System.currentTimeMillis()));
            long startTime = System.currentTimeMillis();
            while ((System.currentTimeMillis() - startTime) < 1 * 60 * 1000) {
                // do stuff
            }

            logger.info("WebSMSSender: Wake up after 1 min at " + new Date(System.currentTimeMillis()));
        }
    }

    public void initQueryDataSOurce() {
        try {
            logger.info(" WebSMSSender:initQueryDataSOurce Started " + new Date(System.currentTimeMillis()));

            this.sqlQuery = this.cm.getSqlQuery();
           
            this.stmQuery = this.conn.prepareStatement(this.sqlQuery);
           

            this.rsQuery = this.stmQuery.executeQuery();
          
            int smsCount = 0;
            try {
                rsQuery.last();
                smsCount = rsQuery.getRow();
                rsQuery.first();
            } catch (Exception ex) {
                logger.fatal("Sms count error -- " + ex);
            }
          
            logger.info("Sms count-- " + smsCount);

            logger.info(" WebSMSSender:initQueryDataSOurce completed " + new Date(System.currentTimeMillis()));

        } catch (SQLException ex) {
            Logger.getLogger(this.getClass().getName().toString()).log(Level.FATAL, ex.getMessage());
            ex.printStackTrace();
        } catch (Exception ex) {
            Logger.getLogger(this.getClass().getName().toString()).log(Level.FATAL, ex.getMessage());
            ex.printStackTrace();
        } finally {
            System.gc();
        }
    }

    public void closeQueryDataSOurce() {
        try {
            logger.info(" WebSMSSender:closeQueryDataSOurce Started " + new Date(System.currentTimeMillis()));
            this.sqlQuery = null;
           
            this.rsQuery.close();
          
            this.rsQuery = null;
           
            this.stmQuery.close();
            
            this.stmQuery = null;
           
            this.conn.close();
            this.conn = null;
            logger.info(" WebSMSSender:closeQueryDataSOurce completed " + new Date(System.currentTimeMillis()));

        } catch (SQLException ex) {
            Logger.getLogger(this.getClass().getName().toString()).log(Level.FATAL, ex.getMessage());
            ex.printStackTrace();
        } catch (Exception ex) {
            Logger.getLogger(this.getClass().getName().toString()).log(Level.FATAL, ex.getMessage());
            ex.printStackTrace();
        } finally {
            System.gc();
        }
    }

    public void executeUpdateDataSOurce(String sqlUpdate) {
        try {
            logger.info(" WebSMSSender:executeUpdateDataSOurce Started " + new Date(System.currentTimeMillis()));

            this.stmUpdate = this.conn.prepareStatement(sqlUpdate);

            this.stmUpdate.executeUpdate();

            logger.info(" WebSMSSender:executeUpdateDataSOurce completed " + new Date(System.currentTimeMillis()));

        } catch (SQLException ex) {
            Logger.getLogger(this.getClass().getName().toString()).log(Level.FATAL, ex.getMessage());
            ex.printStackTrace();
        } catch (Exception ex) {
            Logger.getLogger(this.getClass().getName().toString()).log(Level.FATAL, ex.getMessage());
            ex.printStackTrace();
        } finally {
            System.gc();
        }
    }

    public void closeUpdateDataSOurce() {
        try {
            logger.info(" WebSMSSender:closeUpdateDataSOurce Started " + new Date(System.currentTimeMillis()));
            this.stmUpdate.close();
            this.stmUpdate = null;
            logger.info(" WebSMSSender:closeUpdateDataSOurce completed " + new Date(System.currentTimeMillis()));

        } catch (SQLException ex) {
            Logger.getLogger(this.getClass().getName().toString()).log(Level.FATAL, ex.getMessage());
            ex.printStackTrace();
        } catch (Exception ex) {
            Logger.getLogger(this.getClass().getName().toString()).log(Level.FATAL, ex.getMessage());
            ex.printStackTrace();
        } finally {
            System.gc();
        }
    }

    private void executeSMSSchedule() throws Exception {
        PreparedStatement stmSelectQuery;
        PreparedStatement stmUpdateQuery;
        PreparedStatement stmInsertQuery;
        ResultSet rsSelectQuery;
//        String sqlSelectQuery = "select * from WSC.WSC_SMS_MMS_SCHEDULING where scheduling_date<=trunc(sysdate) and scheduling_time<=to_char(sysdate,'HH24:mi') and status is null"; //and REQUEST_TYPE = 'MMS'

        String sqlSelectQuery = "select * from WSC.WSC_SMS_MMS_SCHEDULING where scheduling_date<=trunc(sysdate) and scheduling_time<=to_char(sysdate,'HH24:mi') and status is null";
        try {

            logger.info(" WebSMSSender:updateSchedule Started " + new Date(System.currentTimeMillis()));
            stmSelectQuery = this.conn.prepareStatement(sqlSelectQuery);
            rsSelectQuery = stmSelectQuery.executeQuery();
            while (rsSelectQuery.next()) {

                String sqlUpdateQuery = "update WSC.WSC_SMS_MMS_SCHEDULING set status='Y' where id=" + rsSelectQuery.getString("ID");
                stmUpdateQuery = this.conn.prepareStatement(sqlUpdateQuery);
                stmUpdateQuery.executeUpdate();
                //stmUpdateQuery = null;
                stmUpdateQuery.close();

                String sqlInsertQuery = "INSERT INTO WSC_SMS_MMS_LOG (USER_ID, MSISDN, REQUEST_TYPE, SMS_CONTENT, MMS_SUBJECT, MMS_FILE_NAME, MMS_FILE_CONTENT, SCHEDULED) SELECT USER_ID, MSISDN, REQUEST_TYPE, SMS_CONTENT, MMS_SUBJECT, MMS_FILE_NAME, MMS_FILE_CONTENT, 'Y' FROM WSC_SMS_MMS_SCHEDULING WHERE WSC_SMS_MMS_SCHEDULING.ID =" + rsSelectQuery.getString("ID");
                stmInsertQuery = this.conn.prepareStatement(sqlInsertQuery);
                stmInsertQuery.execute();
                //stmInsertQuery = null;
                stmInsertQuery.close();
            }
            //rsSelectQuery = null;
            rsSelectQuery.close();
            //stmSelectQuery = null;
            stmSelectQuery.close();
            logger.info(" WebSMSSender:updateSchedule completed " + new Date(System.currentTimeMillis()));

        } catch (Exception ex) {
            //this.conn.rollback();
            Logger.getLogger(this.getClass().getName().toString()).log(Level.FATAL, ex.getMessage());
            ex.printStackTrace();
        } finally {
            System.gc();
        }

    }

    public void updateRequestStatus(String ID) {
        try {
            logger.info(" WebSMSSender:updateRequestStatus Started " + new Date(System.currentTimeMillis()));

            String qry = "update WSC_SMS_MMS_LOG set request_status=1 where request_status is null and ID='" + ID + "'";

            logger.info("WebSMSSender:updateRequestStatus update query--" + qry);
            PreparedStatement ps = this.conn.prepareStatement(qry);


            int res = ps.executeUpdate();

            logger.info(" WebSMSSender:updateRequestStatus completed " + new Date(System.currentTimeMillis()));

        } catch (SQLException ex) {
            Logger.getLogger(this.getClass().getName().toString()).log(Level.FATAL, ex.getMessage());
            ex.printStackTrace();
        } catch (Exception ex) {
            Logger.getLogger(this.getClass().getName().toString()).log(Level.FATAL, ex.getMessage());
            ex.printStackTrace();
        } finally {
            System.gc();
        }
    }
}
