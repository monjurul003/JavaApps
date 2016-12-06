/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package com.gpit.tech.es.cs.barafterduedate.notification;

import java.io.File;
import java.util.Date;
import org.apache.log4j.Level;
import org.apache.log4j.Logger;

/**
 *
 * @author mdmizan
 */
public class Main {
    static Logger logger=Logger.getLogger(Main.class);
    /**
     * @param args the command line arguments
     */
    public static void main(String[] args) throws Exception {
//        Logger.getLogger(Main.class.getName().toString()).log(Level.INFO, "Bar after Due Date Notification Application Started " + new Date(System.currentTimeMillis()));
        logger.info("Bar after Due Date Notification Application Started " + new Date(System.currentTimeMillis()));

//        File scheduleFile = new File("schedule");
//        // Creates the scheduler.schedule
//        Scheduler scheduler = new Scheduler();
//        scheduler.scheduleFile(scheduleFile);
//        // Starts the scheduler.
//        scheduler.start();
//        DOMConfigurator.configure("logconfig.xml");
        DueDateBarAppController app = new DueDateBarAppController(new File("ddbnconf.xml"));
        app.run();

        logger.info("Bar after Due Date Notification Application Exit");

    }

    public static void executeTask(String[] args) {
        // Prepares the task.
        ScheduleTask sTask = new ScheduleTask();
        try {
            sTask.execute(args[0]);

        } catch (Exception ex) {
            Logger.getLogger(Main.class.getName().toString()).log(Level.FATAL, ex.getMessage());
            ex.printStackTrace();
        }
    }
}
