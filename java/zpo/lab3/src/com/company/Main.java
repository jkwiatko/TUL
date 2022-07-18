package com.company;

import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.net.URL;
import java.util.*;
import java.util.concurrent.*;


public class Main {

    public static void main(String[] args) throws Exception {

        Set<String> kolarze = new HashSet<>();
        PriorityQueue<Kolarz> najlepsi = new PriorityQueue<>(Kolarz::compareTo);
        URL url = new URL("http://szgrabowski.kis.p.lodz.pl/zpo18/nazwiska.txt");
        BufferedReader read = new BufferedReader(new InputStreamReader(url.openStream()));
        Random random = new Random();
        String i;
        boolean loggerOn = false;
        while ((kolarze.size() < 15)) {
            i = read.readLine();
            if(random.nextInt() % 5 == 0) {
                kolarze.add(i);
            }
        }
        read.close();

        ScheduledExecutorService scheduledPool = Executors.newScheduledThreadPool(4);
        for (String kolarz : kolarze) {
            Kolarz nowyKolarz = new Kolarz(kolarz, random.nextInt(121)+250);
            scheduledPool.scheduleWithFixedDelay(nowyKolarz, 2400, 500, TimeUnit.MILLISECONDS);
            najlepsi.add(nowyKolarz);
            if(!loggerOn ) {
                new Thread(new Logger(najlepsi, scheduledPool)).start();
                loggerOn = true;
            }
            Thread.sleep(2400);
        }
        }
    }


