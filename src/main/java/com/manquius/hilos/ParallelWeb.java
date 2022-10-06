package com.manquius.hilos;

import com.manquius.hilos.runnables.ReactiveProcessing;
import com.manquius.hilos.runnables.WebProcessing;

import java.util.List;
import java.util.stream.Collectors;
import java.util.stream.IntStream;

public class ParallelWeb {

    private static int numThreads = 1000;
    private static boolean printInternals = false;
    private static List<Integer> intList = IntStream.rangeClosed(1, numThreads).boxed().collect(Collectors.toList());
    private static List<Thread> heavyList = intList.stream().map(x -> new Thread(new WebProcessing("web" + x, printInternals))).collect(Collectors.toList());
    private static List<Thread> sleepList = intList.stream().map(x -> new Thread(new ReactiveProcessing("reactive" + x, printInternals))).collect(Collectors.toList());

    public static void main(String[] args) {
        System.out.println("=== Starting heavy ===");
        lambdas(heavyList);

        System.out.println("Starting sleep");
        lambdas(sleepList);
    }

    private static void lambdas(List<Thread> list) {
        long startTime = System.currentTimeMillis();
        list.parallelStream().peek(x -> x.start()).collect(Collectors.toList());
        System.out.println("== continue ==");
        waitForAll(list);
        long endTime = System.currentTimeMillis();
        System.out.println("=== Took: " + (endTime-startTime) + " ===");
    }

    private static void waitForAll(List<Thread> list) {
        list.forEach(x -> {
            try {
                x.join();
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
        });
    }
}
