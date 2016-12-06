/**
 *	Created on 22 October 2012
 * 	@author monjurul.k
 * 	@description for generating sleep time to wait until the next day up to specified start time
 */
package com.gpit.tech.es.cs.barafterduedate.notification;

import java.util.Calendar;
import java.util.Date;
import java.util.GregorianCalendar;
import java.util.Timer;
import java.util.TimerTask;
import org.apache.log4j.Logger;

public class SleepTime {

    private int startHour;
    private Timer timer;
    static Logger logger = Logger.getLogger(DueDateBarAppController.class);

    public SleepTime(int startHour) {
        this.startHour = startHour;
    }

    public int getStartHour() {
        return startHour;
    }

    public void setStartHour(int startHour) {
        this.startHour = startHour;
    }

    public Date getNextResumeTime() {
        GregorianCalendar gc = new GregorianCalendar();
        int currentHour = gc.get(Calendar.HOUR_OF_DAY);

        if (currentHour >= startHour) {
            gc.add(Calendar.DAY_OF_MONTH, 1);
        }
        gc.set(Calendar.HOUR_OF_DAY, startHour);
        gc.set(Calendar.MINUTE, 0);
        gc.set(Calendar.SECOND, 0);
        gc.set(Calendar.MILLISECOND, 0);
        logger.info("SleepTime- getNextResumeTime():: " + gc.getTime().toString() );
        return gc.getTime();
    }

    public void waitUntilStartHour() {
        timer = new Timer(true);
        final Object o = new Object();
        TimerTask tt = new TimerTask() {

            @Override
            public void run() {
                synchronized (o) {
                    o.notify();
                }
            }
        };

        timer.schedule(tt, getNextResumeTime());
        synchronized (o) {
            try {
                o.wait();
            } catch (InterruptedException ie) {
            }
        }

        timer.cancel();
        timer.purge();
    }
}
