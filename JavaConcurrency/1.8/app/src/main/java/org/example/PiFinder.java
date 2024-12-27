package org.example;

import java.util.ArrayList;
import java.util.List;
import java.util.stream.IntStream;

public class PiFinder {
    private final List<Thread> threads = new ArrayList<>();
    private final List<Worker> workers = new ArrayList<>();

    public PiFinder(int numberThreads) {
        IntStream.range(0, numberThreads)
            .forEach(i -> {
                workers.add(new Worker(numberThreads, i));
                threads.add(new Thread(workers.get(i)));
            });
    }

    public void find() {
        threads.forEach(Thread::start);
    }

    public void finish() {
        threads.forEach(Thread::interrupt);

        long maxIdx = workers.stream()
                .map(Worker::getIdx)
                .reduce(Long::max)
                .orElse(0L);

        workers.forEach(worker -> worker.finish(maxIdx));
    }

    public double printRes() {
        return workers.stream()
                .map(Worker::getResult)
                .reduce(Double::sum)
                .orElse(Double.NaN);
    }
}