/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package com.gpit.es.cs.emailsender;

import java.io.File;
import org.apache.log4j.Logger;
import org.apache.commons.digester.Digester;

/**
 *
 * @author monjurul.k
 */
public class ConfigurationManager {
    private DriverManagerDataSource ds;
    private SimpleJavaMailer jMailer;
    String sqlQuery,inputFile,outputFile,outputFileName,outputDirectory;
    private boolean isLocal;
    static Logger logger = Logger.getLogger(ConfigurationManager.class);
    
    
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
     * @return Returns the isLocal.
    */
    public boolean getIsLocal() {
            return isLocal;
    }
    /**
     * @param isLocal The isLocal to set.
    */
    public void setIsLocal(boolean isLocal) {
            this.isLocal = isLocal;
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
    
    public ConfigurationManager getConfiguration(File confFile) throws Exception {
        Digester d = new Digester();
        ConfigurationManager cm = null;
        d.setValidating(false);

        logger.info("inside getConfiguration");
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
        
        d.addBeanPropertySetter("Configuration/SqlQuery", "sqlQuery");
        d.addBeanPropertySetter("Configuration/OutputFileName", "outputFileName");
        d.addBeanPropertySetter("Configuration/OutputDirectory", "outputDirectory");
        d.addBeanPropertySetter("Configuration/IsLocal", "isLocal");
        

        cm = (ConfigurationManager) d.parse(confFile);

        return cm;
    }
}
