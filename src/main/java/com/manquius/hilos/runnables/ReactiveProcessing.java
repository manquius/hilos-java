package com.manquius.hilos.runnables;

import org.springframework.web.reactive.function.client.WebClient;

public class ReactiveProcessing extends Thread {

    private final String name;
    private final boolean printInternal;
    private final WebClient client;

    public ReactiveProcessing(String name, boolean printInternal) {
        client = WebClient.create("http://localhost:8080");
        this.name = name;
        this.printInternal = printInternal;
    }

    @Override
    public void run() {
        if(printInternal)
            System.out.println("Starting " + name);
        client.get().retrieve().bodyToFlux(String.class);
        if(printInternal)
            System.out.println("Ending " + name);
    }
}
