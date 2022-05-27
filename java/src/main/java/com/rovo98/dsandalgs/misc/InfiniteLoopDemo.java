package com.rovo98.dsandalgs.misc;

public class InfiniteLoopDemo {
    public static void main(String[] args) {
        Thread t =
                new Thread(
                        () -> {
                            for (; ; ) {
                                System.out.println("current count: " + count);
                                try {
                                    Thread.sleep(5 * 1000);
                                } catch (InterruptedException e) {
                                    e.printStackTrace();
                                }
                            }
                        });
        t.setName("printCountWorker");
        t.start();

        // main thread enter infinite loop
        infiniteLoop();
    }

    private static long count = 0L;

    private static void infiniteLoop() {
        int threshold = 0;
        while (true) {
            threshold++;
            count++;
            if (threshold > 100) {
                count /= 1000;
                threshold = 0;
            }
        }
    }
}
