package com.rovo98.dsandalgs.thread;

import org.junit.jupiter.api.Test;

import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;
import java.util.concurrent.TimeUnit;
import java.util.concurrent.locks.ReentrantLock;

public class SynchronizedVersusReentrantLockTest {
    private static final int THREAD_NUM = 5;
    private static final int EXECUTE_COUNT = 30_000_000;

    @Test
    public void testReentrantLock() throws Exception {
        ReentrantLock reentrantLock = new ReentrantLock();
        long begin = System.currentTimeMillis();
        ExecutorService es = Executors.newCachedThreadPool();
        for (int i = 0; i < THREAD_NUM; i++) {
            es.submit(() -> {
                for (int j = 0; j < EXECUTE_COUNT; j++) {
                    reentrantLock.lock();
                    doSomething();
                    reentrantLock.unlock();
                }
            });
        }
        es.shutdown();
        es.awaitTermination(Long.MAX_VALUE, TimeUnit.MILLISECONDS);
        System.out.println("ReentrantLock cost: " + (System.currentTimeMillis() - begin) + " ms.");
    }

    public void doSomething() {}

    @Test
    public void testSynchronized() throws Exception {
        long begin = System.currentTimeMillis();
        ExecutorService es = Executors.newCachedThreadPool();
        for (int i = 0; i < THREAD_NUM; i++) {
            es.submit(() -> {
                for (int j = 0; j < EXECUTE_COUNT; j++) {
                    synchronized (SynchronizedVersusReentrantLockTest.class) {
                        doSomething();
                    }
                }
            });
        }
        es.shutdown();
        es.awaitTermination(Long.MAX_VALUE, TimeUnit.MILLISECONDS);
        System.out.println("synchronized cost: " + (System.currentTimeMillis() - begin) + " ms.");
    }
}
