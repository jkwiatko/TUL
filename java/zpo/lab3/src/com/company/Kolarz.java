package com.company;

import java.io.IOException;
import java.util.logging.FileHandler;
import java.util.logging.Logger;
import java.util.logging.SimpleFormatter;

public class Kolarz implements Runnable, Comparable<Kolarz> {

   String name;
   Long timeLeftToGoal;
   Logger logger = Logger.getLogger("kolarzLogger");
   FileHandler fh;
    public static final int FILE_SIZE = 1024 * 1024 * 100;

    public Kolarz(String name, long timeLeftToGoal) {
        this.name = name;
        this.timeLeftToGoal = timeLeftToGoal;

        try {

            // This block configure the logger with handler and formatter
            fh = new FileHandler("./MyLogFile.log",Integer.MAX_VALUE,1, true);
            logger.addHandler(fh);
            SimpleFormatter formatter = new SimpleFormatter();
            fh.setFormatter(formatter);

            // the following statement is used to log any messages
            logger.info("My first log");

        } catch (SecurityException e) {
            e.printStackTrace();
        } catch (IOException e) {
            e.printStackTrace();
        }

    }

    @Override
    public void run()
    {
        if(timeLeftToGoal > 1) {
            timeLeftToGoal--;
        }

        logger.info("name: "+ name + " seconds left to goal: " + timeLeftToGoal);
    }


    public int compareTo(Kolarz o) {
        return timeLeftToGoal.compareTo(o.timeLeftToGoal);
    }

    @Override
    public String toString() {
        return name + " time left:" + timeLeftToGoal;
    }
}
