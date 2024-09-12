package ru.nsu.romanov;

import java.util.concurrent.TimeUnit;

public class Main {
    public static void main(String[] args) {
        var thread = new Thread(() -> {
            while(true) {
                if (Thread.currentThread().isInterrupted()) {
                    System.out.println("Interrupted");
                    break;
                }
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