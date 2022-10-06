package com.manquius.hilos.runnables;

public class HeavyProcessing extends Thread {

    private final long millis;
    private final String name;
    private final boolean printInternal;

    public HeavyProcessing(String name, long millis, boolean printInternal) {
        this.millis = millis;
        this.name = name;
        this.printInternal = printInternal;
    }

    @Override
    public void run() {
        if(printInternal)
            System.out.println("Starting " + name);
        boolean done = false;
        long startTime = System.currentTimeMillis();
        while(!done) {
            long currentTime = System.currentTimeMillis();
            if(currentTime-millis > startTime)
                done = true;
        }
        if(printInternal)
            System.out.println("Ending " + name);
    }
}
