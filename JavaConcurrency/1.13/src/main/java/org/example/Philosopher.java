package org.example;

import java.util.concurrent.ThreadLocalRandom;
import java.util.concurrent.TimeUnit;
import java.util.concurrent.locks.Condition;
import java.util.concurrent.locks.Lock;
import java.util.concurrent.locks.ReentrantLock;

public class Philosopher implements Runnable {
    private final Fork leftFork;
    private final Fork rightFork;
    private final Lock lock = new ReentrantLock();
    private final Condition condition = lock.newCondition();
    private final int id;
    private final int minTime = 1;
    private final int maxTime = 10;

    Philosopher(Fork leftFork, Fork rightFork, int id) {
        this.id = id;
        this.leftFork = leftFork;
        this.rightFork = rightFork;
    }

    @Override
    public void run() {
        while (true) {
            try {
                System.out.println("Philosopher " + id + " started thinking");
                TimeUnit.SECONDS.sleep(ThreadLocalRandom.current().nextInt(minTime, maxTime));
                System.out.println("Philosopher " + id + " finished thinking");

                lock.lock();

                while (!leftFork.pick() && !rightFork.pick()) {
                    if (leftFork.pick()) {
                        leftFork.put();
                    }
                    if (leftFork.pick()) {
                        rightFork.put();
                    }
                }

                System.out.println("Philosopher " + id + " start eating");
                TimeUnit.SECONDS.sleep(ThreadLocalRandom.current().nextInt(minTime, maxTime));
                System.out.println("Philosopher " + id + " finished eating");
            }  catch (InterruptedException e) {
                throw new RuntimeException(e);
            } finally {
                rightFork.put();
                System.out.println("Philosopher " + id + " released right fork");
                leftFork.put();
                System.out.println("Philosopher " + id + " released left fork");
                condition.signalAll();
                lock.unlock();
            }
        }
    }
}