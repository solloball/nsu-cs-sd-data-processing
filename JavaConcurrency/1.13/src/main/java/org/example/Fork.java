package org.example;

import java.util.concurrent.locks.Condition;
import java.util.concurrent.locks.Lock;
import java.util.concurrent.locks.ReentrantLock;

class Fork {
    public boolean pick() {
        lock.lock();
        try {
            while (!available) {
                condition.await();
            }
            available = false;
            return true;
        } catch (InterruptedException e) {
            return false;
        } finally {
            lock.unlock();
        }
    }

    public void put() {
        lock.lock();
        try {
            available = true;
            condition.signal();
        } finally {
            lock.unlock();
        }
    }
    private final Lock lock = new ReentrantLock();
    private final Condition condition = lock.newCondition();
    private boolean available = true;
}