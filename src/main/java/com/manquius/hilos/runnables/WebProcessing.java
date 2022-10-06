package com.manquius.hilos.runnables;

import org.springframework.web.reactive.function.client.WebClient;

public class WebProcessing extends Thread {

    private final String name;
    private final boolean printInternal;
    private final WebClient client;

    public WebProcessing(String name, boolean printInternal) {
        client = WebClient.create("http://localhost:8080");
        this.name = name;
        this.printInternal = printInternal;
    }

    @Override
    public void run() {
        if(printInternal)
            System.out.println("Starting " + name);
        client.get().retrieve().bodyToMono(String.class);
        if(printInternal)
            System.out.println("Ending " + name);
    }
}
