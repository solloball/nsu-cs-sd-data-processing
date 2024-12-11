package ru.nsu.romanov;

import java.util.concurrent.ThreadLocalRandom;
import java.util.concurrent.TimeUnit;

public class Main {
    public static void main(String[] args) {
        var thread = new Thread(() -> {
            while(!Thread.currentThread().isInterrupted()) {
                System.out.println("Some text!");
            }
        });

        thread.start();
        try {
            TimeUnit.SECONDS.sleep(2);
        } catch (InterruptedException ignored) {
        }
        thread.interrupt();
    }
}
