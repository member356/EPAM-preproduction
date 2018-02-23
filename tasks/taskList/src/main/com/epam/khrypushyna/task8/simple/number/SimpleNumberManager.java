package com.epam.khrypushyna.task8.simple.number;

import java.util.ArrayList;
import java.util.Collections;
import java.util.LinkedList;
import java.util.List;
import java.util.Scanner;
import java.util.concurrent.Callable;
import java.util.concurrent.ExecutionException;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;
import java.util.concurrent.Future;

public class SimpleNumberManager {

    public static void main(String[] args) throws ExecutionException, InterruptedException {
        new SimpleNumberManager().execute();
    }

    private Scanner scanner = new Scanner(System.in);
    private int interval;
    private int lastThreadInterval;
    private int threadsNumber;
    private List<Integer> list;

    public void execute() throws InterruptedException, ExecutionException {
        System.out.println("Enter the start point:");
        int startPoint = scanner.nextInt();

        System.out.println("Enter the final point:");
        int finalPoint = scanner.nextInt();

        System.out.println("Enter the threads number:");
        threadsNumber = scanner.nextInt();

        int workSpace = finalPoint - startPoint;
        interval = workSpace / threadsNumber;
        lastThreadInterval = interval + workSpace % threadsNumber;

        System.out.println("Working time with list in every thread: " + getWorkingTimeWithListInEveryThread(startPoint));
        System.out.println("Working time with common list:" + getWorkingTimeWithCommonList(startPoint));
        System.out.println("Working time with list in every thread using executor:" + getWorkingTimeWithListInEveryThreadUsingExecutor(startPoint));
        System.out.println("Working time with common list using executor:" + getWorkingTimeWithCommonListUsingExecutor(startPoint));
    }

    private List<SimpleNumberThread> getThreadsWithListInEveryThread(int from) {
        List<SimpleNumberThread> result = new ArrayList<>();
        for (int i = 0; i < threadsNumber; ++i) {
            if (i == threadsNumber - 1) {
                result.add(new SimpleNumberThread(from, from + lastThreadInterval));
            } else {
                result.add(new SimpleNumberThread(from, from + interval));
            }
            from += interval;
        }
        return result;
    }

    private List<SimpleNumberThread> getThreadsWithCommonList(int from, List<Integer> list) {
        List<SimpleNumberThread> result = new ArrayList<>();
        for (int i = 0; i < threadsNumber; ++i) {
            if (i == threadsNumber - 1) {
                result.add(new SimpleNumberThread(from, from + lastThreadInterval, list));
            } else {
                result.add(new SimpleNumberThread(from, from + interval, list));
            }
            from += interval;
        }
        return result;
    }

    private void startThreads(List<SimpleNumberThread> threads) throws InterruptedException {
        for (SimpleNumberThread thread : threads) {
            thread.start();
        }
    }

    private void joinThreads(List<SimpleNumberThread> threads) throws InterruptedException {
        for (SimpleNumberThread thread : threads) {
            thread.join();
        }
    }

    private long getWorkingTimeWithListInEveryThread(int startPoint) throws InterruptedException {
        long start = System.nanoTime();
        List<Integer> result = new LinkedList<>();
        List<SimpleNumberThread> threads = getThreadsWithListInEveryThread(startPoint);

        startThreads(threads);
        joinThreads(threads);
        for (SimpleNumberThread thread : threads) {
            result.addAll(thread.getResult());
        }
        System.out.println("Result: " + result);
        return System.nanoTime() - start;
    }

    private long getWorkingTimeWithCommonList(int startPoint) throws InterruptedException {
        long start = System.nanoTime();
        list = Collections.synchronizedList(new LinkedList<>());
        List<SimpleNumberThread> threads = getThreadsWithCommonList(startPoint, list);

        startThreads(threads);
        joinThreads(threads);

        System.out.println("Result: " + list);
        return System.nanoTime() - start;
    }

    private long getWorkingTimeWithListInEveryThreadUsingExecutor(int startPoint) throws ExecutionException, InterruptedException {

        long start = System.nanoTime();
        ExecutorService executor = Executors.newFixedThreadPool(threadsNumber);
        List<Future<List<Integer>>> futures = new ArrayList<>();

        for (int i = 0; i < threadsNumber; ++i) {
            Callable<List<Integer>> call;
            if (i == threadsNumber - 1) {
                call = new SimpleNumberThread(startPoint, startPoint + lastThreadInterval);
            } else {
                call = new SimpleNumberThread(startPoint, startPoint + interval);
            }
            Future<List<Integer>> submit = executor.submit(call);
            futures.add(submit);
            startPoint += interval;
        }
        List<Integer> result = new LinkedList<>();
        boolean stop = false;
        while (!stop) {
            for (int i = futures.size() - 1; i >= 0; --i) {
                Future<List<Integer>> f = futures.get(i);
                if (f.isDone()) {
                    result.addAll(f.get());
                    futures.remove(i);
                }
            }
            stop = futures.isEmpty();
        }
        executor.shutdown();
        Collections.sort(result);
        System.out.println("Result: " + result);
        return System.nanoTime() - start;
    }

    private long getWorkingTimeWithCommonListUsingExecutor(int startPoint) throws InterruptedException, ExecutionException {
        long start = System.nanoTime();
        ExecutorService executor = Executors.newFixedThreadPool(threadsNumber);
        List<Future<List<Integer>>> futures = new ArrayList<>();

        list = Collections.synchronizedList(new LinkedList<Integer>());

        for (int i = 0; i < threadsNumber; ++i) {
            Callable<List<Integer>> call;
            if (i == threadsNumber - 1) {
                call = new SimpleNumberThread(startPoint, startPoint + lastThreadInterval, list);
            } else {
                call = new SimpleNumberThread(startPoint, startPoint + interval, list);
            }
            Future<List<Integer>> submit = executor.submit(call);
            futures.add(submit);
            startPoint += interval;
        }
        boolean stop = false;
        while (!stop) {
            for (int i = futures.size() - 1; i >= 0; --i) {
                Future<List<Integer>> f = futures.get(i);
                if (f.isDone()) {
                    futures.remove(i);
                }
            }
            stop = futures.isEmpty();
        }
        executor.shutdown();
        Collections.sort(list);
        System.out.println("Result: "+list);
        return System.nanoTime() - start;
    }

}
