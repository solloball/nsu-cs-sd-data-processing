package org.example;

public class Worker implements Runnable {
    private final long countWorkers;
    private long idx;
    private double res = 0;

    public Worker(long countWorkers, long idx) {
        this.countWorkers = countWorkers;
        this.idx = idx;
    }

    @Override
    public void run() {
        while (!Thread.currentThread().isInterrupted()) {
            res += iteration(idx);
            idx += countWorkers;
        }
    }

    public void finish(long lastIdx) {
        while (idx < lastIdx) {
            res += iteration(idx);
            idx += countWorkers;
        }
    }

    public long getIdx() {
        return idx;
    }

    public double getResult() {
        return res;
    }

    private double iteration(long n) {
        return n % 2 == 0 ?  4.f / (2 * n + 1) :  -4.f / (2 * n + 1);
    }
}