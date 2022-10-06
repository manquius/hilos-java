package com.manquius.hilos.runnables;

public class SleepProcessing extends Thread {

    private final long millis;
    private final String name;
    private final boolean printInternal;

    public SleepProcessing(String name, long millis, boolean printInternal) {
        this.millis = millis;
        this.name = name;
        this.printInternal = printInternal;
    }

    @Override
    public void run() {
        if(printInternal)
            System.out.println("Starting " + name);
        try {
            Thread.sleep(millis);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
        if(printInternal)
            System.out.println("Ending " + name);
    }
}
