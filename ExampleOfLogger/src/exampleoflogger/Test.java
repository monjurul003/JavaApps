/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

package exampleoflogger;

import org.apache.log4j.Logger;

/**
 *
 * @author Rasel
 */
public class Test {
    
    public static Logger logger = Logger.getLogger(Test.class);
    
    public void testing123(){
        
        logger.info("Hello");
        
    }
}
