/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package com.gpit.ecare.websms;

import com.gpit.ecare.websms.business.manager.SMsSender;
import com.gpit.ecare.websms.configuration.manager.ConfigurationManager;
import java.io.File;
import java.util.Date;
import org.apache.log4j.Logger;
import org.apache.log4j.xml.DOMConfigurator;

/**
 *
 * @author monjurul.k
 */
public class Main {

    static Logger logger = Logger.getLogger(Main.class);

    /**
     * @param args the command line arguments
     */
    public static void main(String[] args) {

        // TODO code application logic here
        try {
         ConfigurationManager cm = new ConfigurationManager().getConfiguration(new File("/webapp01/wscuser/apache-tomcat-6.0.33/webapps/WebSMSSender/input/input.xml"));

//            ConfigurationManager cm = new ConfigurationManager().getConfiguration(new File("C:\\JavaPrograms\\WebSMSSender\\input\\input.xml"));

            DOMConfigurator.configure(cm.getConfigFileLocation());
            logger.info(" WebSMS Sender Started " + new Date(System.currentTimeMillis()));

            /******************************************** MMS part using control file technique************************************************************/

          SMsSender mmsSender = new SMsSender(new File("/webapp01/wscuser/apache-tomcat-6.0.33/webapps/WebSMSSender/input/input.xml"));

//            SMsSender mmsSender = new SMsSender(new File("C:\\JavaPrograms\\WebSMSSender\\input\\input.xml"));

            try {
//           
                logger.info("WebSMSSender:: calling run()" + new Date(System.currentTimeMillis()));

                mmsSender.run();
            } catch (Exception ex) {
                logger.fatal(ex);
            }

        } catch (Exception ex) {
            ex.printStackTrace();
        }
    }
}
