package org.example;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;
import java.util.concurrent.Semaphore;
import java.util.stream.IntStream;

public class Main {
    public static void main(String[] args) {
        int count = 5;
        List<Semaphore> forks = new ArrayList<>(count);
        List<Philosopher> philosophers = new ArrayList<>(count);
        IntStream.range(0, count).forEach(idx -> forks.add(new Semaphore(1)));
        IntStream.range(0, count).forEach(idx ->
                philosophers.add(new Philosopher(forks.get(idx), forks.get((idx + 1) % count), idx)));

        philosophers.forEach(philosopher -> new Thread(philosopher).start());
    }
}