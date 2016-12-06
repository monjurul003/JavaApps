/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */

package com.gpit.ecare.websms.configuration.manager;

import com.gpit.ecare.websms.driver.manager.DriverManagerDataSource;
import java.io.File;
import java.util.Date;
import org.apache.log4j.Logger;
import org.apache.commons.digester.Digester;
import org.apache.log4j.Level;

/**
 *
 * @author mdmizan
 */
public class ConfigurationManager {
    private DriverManagerDataSource ds;
    private String sqlQuery, configFileLocation, ctrlDirectory, sqlQueryMMS;

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
     * @return Returns the sqlQueryMMS.
    */
    public String getSqlQueryMMS() {
            return sqlQueryMMS;
    }
    /**
     * @param sqlQueryMMS The sqlQueryMMS to set.
    */
    public void setSqlQueryMMS(String sqlQueryMMS) {
            this.sqlQueryMMS = sqlQueryMMS;
    }

      /**
     * @return Returns the configFileLocation.
    */
    public String getConfigFileLocation() {
            return configFileLocation;
    }
    /**
     * @param configFileLocation The configFileLocation to set.
    */
    public void setConfigFileLocation(String configFileLocation) {
            this.configFileLocation = configFileLocation;
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

    public String getCtrlDirectory() {
        return ctrlDirectory;
    }

    public void setCtrlDirectory(String ctrlDirectory) {
        this.ctrlDirectory = ctrlDirectory;
    }


    public ConfigurationManager getConfiguration(File confFile) throws Exception {
        try{
            Digester d = new Digester();
            ConfigurationManager cm = null;
            d.setValidating(false);

            Logger.getLogger(this.getClass().getName().toString()).log(Level.INFO," ConfigurationManager:getConfiguration loading start "+new Date(System.currentTimeMillis()));
            d.addObjectCreate("Configuration", ConfigurationManager.class);
            d.addObjectCreate("Configuration/DataSource", DriverManagerDataSource.class);

            d.addBeanPropertySetter("Configuration/DataSource/Driver", "driver");
            d.addBeanPropertySetter("Configuration/DataSource/Url", "url");
            d.addBeanPropertySetter("Configuration/DataSource/User", "username");
            d.addBeanPropertySetter("Configuration/DataSource/Password", "password");
            d.addSetNext("Configuration/DataSource", "setDataSource");

            d.addBeanPropertySetter("Configuration/SqlQuery", "sqlQuery");
            d.addBeanPropertySetter("Configuration/SqlQueryMMS", "sqlQueryMMS");
            d.addBeanPropertySetter("Configuration/ConfigFileLocation", "configFileLocation");
            d.addBeanPropertySetter("Configuration/CtrlDirectory", "ctrlDirectory");

            cm = (ConfigurationManager) d.parse(confFile);

            Logger.getLogger(this.getClass().getName().toString()).log(Level.INFO," ConfigurationManager:getConfiguration loading end "+new Date(System.currentTimeMillis()));

            return cm;
        }
        catch(Exception ex){
            Logger.getLogger(this.getClass().getName().toString()).log(Level.ERROR," ConfigurationManager:getConfiguration loading error "+new Date(System.currentTimeMillis())+" : "+ex.getMessage());
        }
        return null;
    }
}
