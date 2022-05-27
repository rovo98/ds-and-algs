package com.rovo98.dsandalgs.thread;

import org.junit.jupiter.api.Test;

import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;
import java.util.concurrent.TimeUnit;
import java.util.concurrent.locks.AbstractQueuedSynchronizer;
import java.util.concurrent.locks.ReentrantLock;

public class AqsCompareTest {
    private class MyReentrantLock extends AbstractQueuedSynchronizer {
        @Override
        protected boolean tryAcquire(int acquires) {
            final Thread current = Thread.currentThread();
            while (true) {
                int c = getState();
                if (c == 0) {
                    if (compareAndSetState(0, acquires)) {
                        setExclusiveOwnerThread(current);
                        return true;
                    }
                }
            }
        }

        @Override
        protected boolean tryRelease(int releases) {
            int c = getState() - releases;
            if (Thread.currentThread() != getExclusiveOwnerThread()) {
                throw new IllegalMonitorStateException();
            }
            boolean free = false;
            if (c == 0) {
                free = true;
                setExclusiveOwnerThread(null);
            }
            setState(c);
            return free;
        }
    }

    @Test
    public void testReentrantLock() throws Exception {
        ReentrantLock reentrantLock = new ReentrantLock(false);
        long begin = System.currentTimeMillis();
        ExecutorService executorService = Executors.newCachedThreadPool();
        for (int i = 0; i < 2; i++) {
            executorService.submit(() -> {
                for (int j = 0; j < 50_000_000; j++) {
                    reentrantLock.lock();
                    doSomething();
                    reentrantLock.unlock();
                }
            });
        }
        executorService.shutdown();
        executorService.awaitTermination(Long.MAX_VALUE, TimeUnit.MILLISECONDS);
        System.out.println("ReentrantLock cost: " + (System.currentTimeMillis() - begin) + " ms.");
    }

    @Test
    public void testKeepAcquireLock() throws Exception {
        MyReentrantLock myReentrantLock = new MyReentrantLock();
        long begin = System.currentTimeMillis();
        ExecutorService executorService = Executors.newCachedThreadPool();
        for (int i = 0; i < 2; i++) {
            executorService.submit(() -> {
                for (int j = 0; j < 50_000_000; j++) {
                    myReentrantLock.tryAcquire(1);
                    doSomething();
                    myReentrantLock.tryRelease(1);
                }
            });
        }
        executorService.shutdown();
        executorService.awaitTermination(Long.MAX_VALUE, TimeUnit.MILLISECONDS);
        System.out.println("MyReentrantLock cost : " + (System.currentTimeMillis() - begin) + " ms.");
    }

    private void doSomething() {
        // empty implementation means thread runs quickly
    }
}
