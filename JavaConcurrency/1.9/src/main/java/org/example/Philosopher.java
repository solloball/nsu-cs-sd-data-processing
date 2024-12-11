package org.example;

import java.util.concurrent.Semaphore;
import java.util.concurrent.ThreadLocalRandom;
import java.util.concurrent.TimeUnit;

public class Philosopher implements Runnable {
    private final Semaphore leftFork;
    private final Semaphore rightFork;
    private final int id;
    private final int minTime = 1;
    private final int maxTime = 10;

    Philosopher(Semaphore leftFork, Semaphore rightFork, int id) {
        this.id = id;
        this.leftFork = leftFork;
        this.rightFork = rightFork;
    }

    @Override
    public void run() {
        try {
            while (true) {
                System.out.println("Philosopher " + id + " started thinking");
                TimeUnit.SECONDS.sleep(ThreadLocalRandom.current().nextInt(minTime, maxTime));
                System.out.println("Philosopher " + id + " finished thinking");

                leftFork.acquire();
                System.out.println("Philosopher " + id + " took left fork");
                rightFork.acquire();
                System.out.println("Philosopher " + id + " took right fork");

                System.out.println("Philosopher " + id + " start eating");
                TimeUnit.SECONDS.sleep(ThreadLocalRandom.current().nextInt(minTime, maxTime));
                System.out.println("Philosopher " + id + " finished eating");

                rightFork.release();
                System.out.println("Philosopher " + id + " released right fork");
                leftFork.release();
                System.out.println("Philosopher " + id + " released left fork");
            }
        } catch (InterruptedException e) {
            throw new RuntimeException(e);
        }
    }
}
