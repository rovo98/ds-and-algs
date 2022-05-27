package com.rovo98.dsandalgs.thread;

import org.junit.jupiter.api.Test;

import java.util.Random;
import java.util.concurrent.locks.ReentrantLock;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertFalse;


public class ThreadStateTest {

    @Test
    public void testThreadBlockedState1() throws Exception {
        Object obj = new Object();
        Thread t1 = new Thread(() -> {
            synchronized (obj) {
                int sum = 0;
                // simulating thread execution
                try {
                    Thread.sleep(5000);
                } catch (InterruptedException e) {
                    e.printStackTrace();
                }
                while (1 == 1) {
                    sum++;
                    if (sum == Integer.MAX_VALUE) break;
                }
                System.out.println(sum);
            }
        });
        t1.start();
        // Make sure thread t1 is already in running state
        Thread.sleep(500);
        Thread t2 = new Thread(() -> {
            synchronized (obj) {
                System.out.println("enter into the synchronized block (obtained obj lock)");
            }
        });
        t2.start();
        // Print out the thread states
        System.out.println("thread 1 state: " + t1.getState());
        System.out.println("thread 2 state: " + t2.getState());
        assertEquals(Thread.State.BLOCKED, t2.getState());
    }

    @Test
    public void testThreadBlocked2() throws Exception {
        Object obj = new Object();
        Thread[] threads = new Thread[2];
        for (int i = 0; i < threads.length; i++) {
            threads[i] = new Thread(() -> {
                synchronized (obj) {
                    try {
                        obj.wait();
                    } catch (InterruptedException e) {
                        e.printStackTrace();
                    }
                    // 模拟后续计算任务
                    while (1 == 1) {
                    }
                }
            });
            threads[i].setName("Thread-" + (i + 1));
            threads[i].start();
        }
        Thread.sleep(1000);
        // notifyAll
        synchronized (obj) {
            obj.notifyAll();
        }
        Thread.sleep(2000);
        System.out.println("Thread 1 state: " + threads[0].getState());
        System.out.println("Thread 2 state: " + threads[1].getState());
    }

    @Test
    public void testThreadWaitingState() throws Exception {
        Thread t1 = new Thread(() -> {
            // 模拟计算
            while (1 == 1) {}
        });
        t1.start();
        Thread t2 = new Thread(() -> {
            try {
                t1.join();
                System.out.println("Thread 2 starts executing...");
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
        });
        t2.start();
        Thread.sleep(1000);
        System.out.println("Thread 2 state: " + t2.getState());
        assertEquals(Thread.State.WAITING, t2.getState());
    }

    @Test
    public void testThreadStop() throws Exception {
        class MyThread extends Thread {
            private int i = 0;
            private int j = 0;

            @Override
            public void run() {
                synchronized (this) {
                    ++i;
                    try {
                        Thread.sleep(10000);
                    } catch (InterruptedException e) {
                        e.printStackTrace();
                    }
                    ++j;
                }
            }

            public void print() {
                System.out.println("i=" + i + ", j=" + j);
            }
        }
        MyThread t1 = new MyThread();
        t1.start();

        Thread.sleep((new Random().nextInt(5) + 1) * 1000);
        t1.stop();

        t1.print();
    }

    @Test
    public void testReleaseLockWhenCallStop() throws Exception {
        ReentrantLock reentrantLock = new ReentrantLock();
        Thread t1 = new Thread(() -> {
            reentrantLock.lock();
            try {
                Thread.sleep(1000_000);
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
        });

        t1.start();
        Thread.sleep(1000);
        System.out.println("Thread 1 state: " + t1.getState());
        t1.stop();
        // make sure t1 stopped
        while (t1.getState() != Thread.State.TERMINATED) {}
        System.out.println("Main thread try acquire the lock...");
        boolean lockStatus = reentrantLock.tryLock();
        if (lockStatus)
            System.out.println("Main thread acquired the lock successfully.");
        assertFalse(lockStatus);
    }

    @Test
    public void testThreadSuspendResumeCauseDeadLock() throws Exception {
        Object lock = new Object();
        Thread t1 = new Thread(() -> {
            synchronized (lock) {
                try {
                    Thread.sleep(1000_000);
                } catch (InterruptedException e) {
                    e.printStackTrace();
                }
            }
        });
        t1.start();
        Thread.sleep(1000);
        t1.suspend();
        System.out.println("Thread 1 suspended.");
        System.out.println("Try to acquire lock from main thread...");
        synchronized (lock) {
            System.out.println("Acquired lock from main thread successfully.");
        }
    }
}
