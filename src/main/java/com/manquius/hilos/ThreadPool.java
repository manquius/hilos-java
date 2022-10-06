package com.manquius.hilos;

import com.manquius.hilos.runnables.HeavyProcessing;
import com.manquius.hilos.runnables.SleepProcessing;

import java.util.List;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;
import java.util.concurrent.TimeUnit;
import java.util.stream.Collectors;
import java.util.stream.IntStream;

public class ThreadPool {


    private static int numThreads = 10;
    private static boolean printInternals = false;
    private static List<Integer> intList = IntStream.rangeClosed(1, numThreads).boxed().collect(Collectors.toList());
    private static List<Runnable> heavyList = intList.stream().map(x -> new HeavyProcessing("heavy" + x, 1000, printInternals)).collect(Collectors.toList());
    private static List<Runnable> sleepList = intList.stream().map(x -> new SleepProcessing("sleep" + x, 1000, printInternals)).collect(Collectors.toList());

    public static void main(String[] args) throws InterruptedException {
        System.out.println("=== Starting heavy ===");
        treadPool(heavyList);
        System.out.println("=== Starting sleep ===");
        treadPool(sleepList);
    }

    private static void treadPool(List<Runnable> list) throws InterruptedException {
        ExecutorService executor = Executors.newFixedThreadPool(2);
        long startTime = System.currentTimeMillis();
        list.forEach(x -> executor.submit(x));
        System.out.println("== continue ==");
        executor.shutdown();
        executor.awaitTermination(10, TimeUnit.SECONDS);
        long endTime = System.currentTimeMillis();
        System.out.println("=== Took: " + (endTime-startTime) + " ===");
    }
}
