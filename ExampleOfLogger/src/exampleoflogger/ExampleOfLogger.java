/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package exampleoflogger;

import org.apache.log4j.Logger;
import org.apache.log4j.xml.DOMConfigurator;

/**
 *
 * @author Rasel
 */
public class ExampleOfLogger {

    public static Logger logger = Logger.getLogger(ExampleOfLogger.class);

    /**
     * @param args the command line arguments
     */
    public static void main(String[] args) {
        // TODO code application logic here
        String userDir = System.getProperty("user.dir");
        
        DOMConfigurator.configure(userDir + "/conf/ExampleOfLogger_log4jConfig.xml");
        
        logger.info("test");
        logger.error("errror hoise ");
        logger.fatal("fatse");
        logger.debug("debug korar sms");
        
        Test obje = new Test();
        obje.testing123();

    }

}
