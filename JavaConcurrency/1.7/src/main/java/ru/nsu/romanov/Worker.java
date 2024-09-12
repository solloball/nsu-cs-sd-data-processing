package ru.nsu.romanov;

import java.util.concurrent.Phaser;

public class Worker implements Runnable {
    private final Phaser phaser;
    private final int start;
    private final int end;
    private double res = 0;

    public Worker(Phaser phaser, int start, int end) {
        this.end = end;
        this.start = start;
        this.phaser = phaser;
    }

    @Override
    public void run() {
        for (int i = start; i < end; i++) {
            res += iteration(i);
        }
        res *= 4;
        phaser.arriveAndDeregister();
    }

    public double getResult() {
        return res;
    }

    private double iteration(int n) {
        return n % 2 == 0 ?  1.f / (2 * n + 1) :  -1.f / (2 * n + 1);
    }
}
