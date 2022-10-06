package com.manquius.hilos.runnables;

public class WaitProcessing extends Thread {

    private final long millis;
    private final String name;
    private final boolean printInternal;

    public WaitProcessing(String name, long millis, boolean printInternal) {
        this.millis = millis;
        this.name = name;
        this.printInternal = printInternal;
    }

    @Override
    public void run() {
        if(printInternal)
            System.out.println("Starting " + name);
        try {
            wait(millis);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
        if(printInternal)
            System.out.println("Ending " + name);
    }
}
