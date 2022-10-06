package com.manquius.hilos;

import com.manquius.hilos.runnables.HeavyProcessing;
import com.manquius.hilos.runnables.SleepProcessing;

import java.util.List;
import java.util.stream.Collectors;
import java.util.stream.IntStream;

public class SimpleThreads {

    private static int numThreads = 10;
    private static boolean printInternals = false;
    private static List<Integer> intList = IntStream.rangeClosed(1, numThreads).boxed().collect(Collectors.toList());
    private static List<Thread> heavyList = intList.stream().map(x -> new Thread(new HeavyProcessing("heavy" + x, 1000, printInternals))).collect(Collectors.toList());
    private static List<Thread> sleepList = intList.stream().map(x -> new Thread(new SleepProcessing("sleep" + x, 1000, printInternals))).collect(Collectors.toList());


    public static void main(String[] args) throws InterruptedException {
        System.out.println("=== Starting heavy ===");
        simpleThreads(heavyList);

        System.out.println("Starting sleep");
        simpleThreads(sleepList);
    }



    private static void simpleThreads(List<Thread> list) {
        long startTime = System.currentTimeMillis();
        list.forEach(x -> x.start());
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
