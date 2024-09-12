package ru.nsu.romanov;

import java.util.stream.IntStream;

public class Main {
    public static void main(String[] args) throws InterruptedException {
        new Main().function();
    }

    public void function() throws InterruptedException {
        var thread = new Thread(() -> {
            IntStream.range(0, 10).forEach(i -> System.out.println(i + " text of children"));
            synchronized (this) {
                notify();
            }
        });
        thread.start();
        synchronized (thread) {
            thread.wait();
        }
        IntStream.range(0, 10).forEach(i -> System.out.println(i + " text of parent"));
    }
}