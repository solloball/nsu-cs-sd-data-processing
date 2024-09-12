package ru.nsu.romanov;

import java.util.ArrayList;
import java.util.List;
import java.util.concurrent.Phaser;

public class PiFinder {
    private final Phaser phaser = new Phaser();
    List<Worker> workers;

    public PiFinder(int countIterations, int numberThreads) {
        workers = new ArrayList<>(numberThreads);

        int range = countIterations / numberThreads;
        for (int i = 0; i < numberThreads; i++) {
            workers.add(i, new Worker(phaser, i * range, (i + 1) * range));
        }
    }

    public double find() {
        phaser.register();
        for (Worker worker : workers) {
            phaser.register();
            new Thread(worker).start();
        }

        phaser.arriveAndAwaitAdvance();

        return workers.stream()
                .map(Worker::getResult)
                .reduce(Double::sum)
                .orElse(Double.NaN);
    }


}
