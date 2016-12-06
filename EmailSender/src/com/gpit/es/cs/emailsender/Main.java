/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */

package com.gpit.es.cs.emailsender;

import it.sauronsoftware.cron4j.Scheduler;
import java.io.File;
import java.util.Date;
import org.apache.log4j.Level;
import org.apache.log4j.Logger;

/**
 *
 * @author monjurul.k
 */
public class Main {
    /**
     * @param args the command line arguments
     */
    public static void main(String[] args) throws Exception {      
        Logger.getLogger(Main.class.getName().toString()).log(Level.INFO," Application Started "+new Date(System.currentTimeMillis()));
        File scheduleFile = new File("schedule");
        // Creates the scheduler.
        Scheduler scheduler = new Scheduler();
        scheduler.scheduleFile(scheduleFile);
        // Starts the scheduler.
        scheduler.start();
    }
    public static void executeTask(String[] args){
        // Prepares the task.
        ScheduleTask sTask = new ScheduleTask();
        try {
            sTask.execute(args[0]);
        } catch (Exception ex) {
            Logger.getLogger(Main.class.getName().toString()).log(Level.FATAL,ex.getMessage());
            ex.printStackTrace();
        }
    }

}
