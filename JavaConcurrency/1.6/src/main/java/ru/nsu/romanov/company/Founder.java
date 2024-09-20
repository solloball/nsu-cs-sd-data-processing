package ru.nsu.romanov.company;

import java.util.ArrayList;
import java.util.List;
import java.util.concurrent.Phaser;
import java.util.stream.IntStream;

public final class Founder {
    private final List<Runnable> workers;
    private final Company company;
    private final Phaser phaser = new Phaser();

    public Founder(final Company company) {
        this.company = company;
        this.workers = new ArrayList<>(company.getDepartmentsCount());
        IntStream.range(0, company.getDepartmentsCount()).forEach(i -> {
            workers.add(i, () -> {
                company.getFreeDepartment(i).performCalculations();
                phaser.arriveAndDeregister();
            });
        });
    }

    public void start() {
        for (final Runnable worker : workers) {
            phaser.register();
            new Thread(worker).start();
        }

        phaser.arriveAndAwaitAdvance();
        company.showCollaborativeResult();
    }
}