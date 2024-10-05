package org.example;

import java.util.concurrent.Semaphore;
import java.util.concurrent.TimeUnit;

public class Main {
    public static void main(String[] args) {
        var aThread = new Thread(() -> {
            while (true) {
                try {
                    Thread.sleep(aSleepTime);
                    TimeUnit.SECONDS.sleep(aSleepTime);
                    System.out.println("detail a is ready");
                    aSem.release();
                } catch (InterruptedException e) {
                    Thread.currentThread().interrupt();
                }
            }
        });

        var bThread = new Thread(() -> {
            while (true) {
                try {
                    TimeUnit.SECONDS.sleep(bSleepTime);
                    System.out.println("detail b is ready");
                    bSem.release();
                } catch (InterruptedException e) {
                    Thread.currentThread().interrupt();
                }
            }
        });

        var cThread = new Thread(() -> {
            while (true) {
                try {
                    TimeUnit.SECONDS.sleep(cSleepTime);
                    System.out.println("detail c is ready");
                    cSem.release();
                } catch (InterruptedException e) {
                    Thread.currentThread().interrupt();
                }
            }
        });

        var assembler = new Thread(() -> {
            while (true) {
                try {
                    aSem.acquire();
                    bSem.acquire();
                    cSem.acquire();
                    System.out.println("Widget is ready");
                } catch (InterruptedException e) {
                    Thread.currentThread().interrupt();
                }
            }
        });

        aThread.start();
        bThread.start();
        cThread.start();
        assembler.start();
    }

    private static final int aSleepTime = 1;
    private static final int bSleepTime = 2;
    private static final int cSleepTime = 3;
    private static final Semaphore aSem = new Semaphore(0);
    private static final Semaphore bSem = new Semaphore(0);
    private static final Semaphore cSem = new Semaphore(0);
}