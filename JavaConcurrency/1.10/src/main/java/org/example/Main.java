package org.example;

import java.util.concurrent.Semaphore;

public class Main {
    public static void main(String[] args) throws InterruptedException {
        Semaphore mutex = new Semaphore(1);

        var thread = new Thread(() -> {
           int i = 0;
           while (i < 10) {
               if (mutex.tryAcquire()) {
                   System.out.println("Child thread " + i);
                   i++;
                   mutex.release();
               }
           }
        });

        thread.start();

        int i = 0;
        while (i < 10) {
            if (mutex.tryAcquire()) {
                System.out.println("Parent thread " + i);
                i++;
                mutex.release();
            }
        }

        thread.join();
    }
}