/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package com.gpit.tech.es.cs.barafterduedate.notification;

import java.io.File;
import org.apache.log4j.Logger;
import org.apache.commons.digester.Digester;

/**
 *
 * @author mdmizan
 */
public class ConfigurationManager {

    private DriverManagerDataSource ds;
    private SimpleJavaMailer jMailer;
    String query, sqlQuery, inputFile, outputFile, outputFileName, outputDirectory;
    private String ctrlDirectory, sqlQuery1, sms, query1, reportGenDays, logQuery, deleteQuery, truncQuery, updateTable;
    private boolean isPrepareReport, isSms, isWriteDB;
    private String connectionUrlSqldb01, connectionUrlSqldb02;
    private String smsTable;
    static Logger logger = Logger.getLogger(ConfigurationManager.class);
    private int startHour;

    public String getUpdateTable() {
        return updateTable;
    }

    public void setUpdateTable(String updateTable) {
        this.updateTable = updateTable;
    }

    public String getSmsTable() {
        return smsTable;
    }

    public void setSmsTable(String smsTable) {
        this.smsTable = smsTable;
    }

    public String getCtrlDirectory() {
        return ctrlDirectory;
    }

    public void setCtrlDirectory(String ctrlDirectory) {
        this.ctrlDirectory = ctrlDirectory;
    }

    /**
     * @return Returns the sqlQuery.
     */
    public String getSqlQuery() {
        return sqlQuery;
    }

    /**
     * @param sqlQuery The sqlQuery to set.
     */
    public void setSqlQuery(String sqlQuery) {
        this.sqlQuery = sqlQuery;
    }

    /**
     * @return Returns the sqlQuery.
     */
    public String getDeleteQuery() {
        return deleteQuery;
    }

    /**
     * @param sqlQuery The sqlQuery to set.
     */
    public void setdeleteQuery(String deleteQuery) {
        this.deleteQuery = deleteQuery;
    }

    /**
     * @return Returns the logQuery.
     */
    public String getlogQuery() {
        return logQuery;
    }

    /**
     * @param logQuery The logQuery to set.
     */
    public void setLogQuery(String logQuery) {
        this.logQuery = logQuery;
    }

    /**
     * @return Returns the truncQuery.
     */
    public String getTruncQuery() {
        return truncQuery;
    }

    /**
     * @param truncQuery The truncQuery to set.
     */
    public void setTruncQuery(String truncQuery) {
        this.truncQuery = truncQuery;
    }

 
    public String getReportGenDays() {
        return reportGenDays;
    }

    /**
     * @param sqlQuery The sqlQuery to set.
     */
    public void setReportGenDays(String reportGenDays) {
        this.reportGenDays = reportGenDays;
    }

    /**
     * @return Returns the sqlQuery.
     */
    public String getSqlQuery1() {
        return sqlQuery1;
    }

    /**
     * @param sqlQuery The sqlQuery to set.
     */
    public void setSqlQuery1(String sqlQuery1) {
        this.sqlQuery1 = sqlQuery1;
    }

    public String getQuery() {
        return query;
    }

    public void setQuery(String query) {
        this.query = query;
    }

    public String getQuery1() {
        return query1;
    }

    public void setQuery1(String query1) {
        this.query1 = query1;
    }

    public String getSms() {
        return sms;
    }

    public void setSms(String sms) {
        this.sms = sms;
    }

    /**
     * @return Returns the inputFile.
     */
    public String getInputFile() {
        return inputFile;
    }

    /**
     * @param outputFile The outputFile to set.
     */
    public void setInputFile(String inputFile) {
        this.inputFile = inputFile;
    }

    /**
     * @return Returns the outputFile.
     */
    public String getOutputFile() {
        return outputFile;
    }

    /**
     * @param outputFile The outputFile to set.
     */
    public void setOutputFile(String outputFile) {
        this.outputFile = outputFile;
    }

    /**
     * @return Returns the outputFileName.
     */
    public String getOutputFileName() {
        return outputFileName;
    }

    /**
     * @param outputFileName The outputFileName to set.
     */
    public void setOutputFileName(String outputFileName) {
        this.outputFileName = outputFileName;
    }

    /**
     * @return Returns the outputDirectory.
     */
    public String getOutputDirectory() {
        return outputDirectory;
    }

    /**
     * @param outputDirectory The outputDirectory to set.
     */
    public void setOutputDirectory(String outputDirectory) {
        this.outputDirectory = outputDirectory;
    }

    /**
     * @return Returns the isPrepareReport.
     */
    public boolean getIsPrepareReport() {
        return this.isPrepareReport;
    }

    /**
     * @param isPrepareReport The isPrepareReport to set.
     */
    public void setIsPrepareReport(boolean isPrepareReport) {
        this.isPrepareReport = isPrepareReport;
    }

    /**
     * @return Returns the isSms.
     */
    public boolean getIsSms() {
        return isSms;
    }

    /**
     * @param isSms The isSms to set.
     */
    public void setIsSms(boolean isSms) {
        this.isSms = isSms;
    }

    /**
     * @return Returns the isWriteDB.
     */
    public boolean getIsWriteDB() {
        return isWriteDB;
    }

    /**
     * @param isWriteDB The isWriteDB to set.
     */
    public void setIsWriteDB(boolean isWriteDB) {
        this.isWriteDB = isWriteDB;
    }
   

    /**
     * @return Returns the DataSource.
     */
    public DriverManagerDataSource getDataSource() {
        return ds;
    }

    /**
     * @param DS, The DataSource to set.
     */
    public void setDataSource(DriverManagerDataSource ds) {
        this.ds = ds;
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

    public int getStartHour() {
        return startHour;
    }

    public void setStartHour(int maxSleep) {
        this.startHour = maxSleep;
    }

    /**
     * @return Returns the DataSource.
     */
    public SimpleJavaMailer getEmail() {
        return jMailer;
    }

    /**
     * @param DS, The DataSource to set.
     */
    public void setEmail(SimpleJavaMailer jMailer) {
        this.jMailer = jMailer;
    }
    /*
    this function will prepare Configuration manager according to bar days
     */

    public void setAccording2BarDay(String day) {
        this.sqlQuery = this.sqlQuery.replaceAll("[?]", day);
        this.sqlQuery1 = this.sqlQuery1.replaceAll("[?]", day);
        this.outputFileName = this.outputFileName.replaceAll("[?]", day);
        this.sms = this.sms.replaceAll("[?]", day);
        this.query1 = this.query1.replaceAll("[?]", day);
        logger.info("Configuration Manager:: Complete setAccording2BarDay() for--" + day);
        // System.out.println(day+this.sqlQuery);
    }

    /**
     * @return Returns the DataSource.
     */
    public ConfigurationManager getConfiguration(File confFile) throws Exception {
        Digester d = new Digester();
        ConfigurationManager cm = null;
        d.setValidating(false);

        logger.info("Configuration Manager::inside getConfiguration");

        d.addObjectCreate("Configuration", ConfigurationManager.class);
        d.addObjectCreate("Configuration/DataSource", DriverManagerDataSource.class);
        d.addObjectCreate("Configuration/Email", SimpleJavaMailer.class);

        d.addBeanPropertySetter("Configuration/DataSource/Driver", "driver");
        d.addBeanPropertySetter("Configuration/DataSource/Url", "url");
        d.addBeanPropertySetter("Configuration/DataSource/User", "username");
        d.addBeanPropertySetter("Configuration/DataSource/Password", "password");
        d.addSetNext("Configuration/DataSource", "setDataSource");

        d.addBeanPropertySetter("Configuration/Email/Subject", "subject");
        d.addBeanPropertySetter("Configuration/Email/From", "from");
        d.addBeanPropertySetter("Configuration/Email/Body", "body");
        d.addBeanPropertySetter("Configuration/Email/To", "to");
        d.addBeanPropertySetter("Configuration/Email/CC", "cc");
        d.addBeanPropertySetter("Configuration/Email/IsEmail", "isEmail");
        d.addSetNext("Configuration/Email", "setEmail");

        d.addBeanPropertySetter("Configuration/CtrlDirectory", "ctrlDirectory");
        d.addBeanPropertySetter("Configuration/OutputFileName", "outputFileName");
        d.addBeanPropertySetter("Configuration/OutputDirectory", "outputDirectory");
        d.addBeanPropertySetter("Configuration/StartHour", "startHour");
        d.addBeanPropertySetter("Configuration/IsPrepareReport", "isPrepareReport");
        d.addBeanPropertySetter("Configuration/IsSms", "isSms");
        d.addBeanPropertySetter("Configuration/IsWriteDB", "isWriteDB");
        d.addBeanPropertySetter("Configuration/Sms", "sms");

        d.addBeanPropertySetter("Configuration/ConnectionUrlSqldb01", "connectionUrlSqldb01");
        d.addBeanPropertySetter("Configuration/ConnectionUrlSqldb02", "connectionUrlSqldb02");
        d.addBeanPropertySetter("Configuration/SqlQuery", "sqlQuery");
        d.addBeanPropertySetter("Configuration/Query", "query");
        d.addBeanPropertySetter("Configuration/Query1", "query1");
        d.addBeanPropertySetter("Configuration/SqlQuery1", "sqlQuery1");
        d.addBeanPropertySetter("Configuration/LogQuery", "logQuery");
        d.addBeanPropertySetter("Configuration/DeleteQuery", "deleteQuery");
        d.addBeanPropertySetter("Configuration/TruncQuery", "truncQuery");
        d.addBeanPropertySetter("Configuration/SmsTable", "smsTable");
        d.addBeanPropertySetter("Configuration/UpdateTable", "updateTable");
        
        cm = (ConfigurationManager) d.parse(confFile);
        logger.info("Configuration Manager:: parsing complete");

        return cm;
    }
}
