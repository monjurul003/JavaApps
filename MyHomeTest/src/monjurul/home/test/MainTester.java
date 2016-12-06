package monjurul.home.test;

import java.io.File;
import java.security.InvalidKeyException;
import java.security.NoSuchAlgorithmException;
import java.util.ArrayList;
import java.util.logging.Level;
import javax.crypto.NoSuchPaddingException;
import monjurul.imageencryptor.MyImageEncryptor;
import org.apache.log4j.Logger;
import org.apache.log4j.xml.DOMConfigurator;

/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */

/**
 *
 * @author Rasel
 */
public class MainTester {

    static Logger logger = Logger.getLogger(MainTester.class);
    /**
     * @param args the command line arguments
     */
    public static void main(String[] args) {
        // TODO code application logic here
         String workingDir = System.getProperty("user.dir");
	   System.out.println("Current working directory : " + workingDir);
           
//        DOMConfigurator.configure("C:/JavaApps/MyHomeTest/config/myhometest-log4jConfig.xml");
        DOMConfigurator.configure(workingDir+"\\config\\myhometest-log4jConfig.xml");
        logger.info(workingDir+"\\config\\myhometest-log4jConfig.xml");
        
        DirectoryProcessor dp = new DirectoryProcessor();
        
        ArrayList<String> fnames = dp.getFilesNamesInDir();
        logger.info("got files name list --" + fnames.size());
        for(int i=0; i<fnames.size(); i++){
            System.out.println(fnames.get(i));
            if(fnames.get(i).contains(".jpg")){
//                dp.readFile(fnames.get(i));
                logger.info("started image encryption");
                try {
                    MyImageEncryptor.encryption();
                } catch (NoSuchAlgorithmException ex) {
                    logger.error( ex);
                } catch (NoSuchPaddingException ex) {
                    logger.error( ex);
                } catch (InvalidKeyException ex) {
                    logger.error( ex);
                }
                logger.info("Ended image encryption");
            }
        }
        
    }
}
