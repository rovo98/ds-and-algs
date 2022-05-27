package com.rovo98.dsandalgs.thread;

import org.junit.jupiter.api.Test;

import java.util.ArrayList;
import java.util.List;

public class FindBugAQSTest {

    private static volatile int number = 0;

    @Test
    public void testAcquireLockExceptionHappened() throws Exception {
        List<Thread> threads = new ArrayList<>();
        FindBugAQS aqs = new FindBugAQS();
        Thread t1 = new Thread(() -> {
            aqs.lock();
            try {
                Thread.sleep(5000);
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
            number++;
            aqs.unlock();
        });
        t1.start();
        threads.add(t1);

        Thread.sleep(500);

        for (int i = 0; i < 4; i++) {
            Thread t = new Thread(() -> {
                aqs.lock();
                try {
                    Thread.sleep(500);
                } catch (InterruptedException e) {
                    e.printStackTrace();
                }
                number++;
                aqs.unlock();
            });
            t.start();
            threads.add(t);
        }
        for (Thread thread : threads) {
            thread.join();
        }
        System.out.println("number is " + number);
    }
}
