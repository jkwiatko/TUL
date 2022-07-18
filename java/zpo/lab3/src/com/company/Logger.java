package com.company;

import java.util.Iterator;
import java.util.PriorityQueue;
import java.util.concurrent.ScheduledExecutorService;

public class Logger implements Runnable {

    PriorityQueue<Kolarz> najlepsi;
    ScheduledExecutorService scheduledPool;

    public Logger(PriorityQueue<Kolarz> najlepsi, ScheduledExecutorService scheduledPool) {
        this.najlepsi = najlepsi;
        this.scheduledPool = scheduledPool;
    }

    @Override
    public void run() {
        while(!scheduledPool.isShutdown()) {
            Iterator<Kolarz> iterator = najlepsi.iterator();
            if(iterator.hasNext()) {
                System.out.println("-----\n"+iterator.next().toString());
                if(iterator.hasNext()) {
                    System.out.println(iterator.next().toString());
                    if(iterator.hasNext()) {
                        Kolarz kolarz = iterator.next();
                        if(kolarz.timeLeftToGoal < 1) {
                            scheduledPool.shutdown();
                        }
                        System.out.println(kolarz.toString()+"\n-----");
                    }
                }
            }
            try {
                Thread.sleep(500);

            } catch (InterruptedException e) {
                System.out.println("exception");
            }
        }

    }
}
