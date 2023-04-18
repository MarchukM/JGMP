package org.jgmp.concurrency.taskone;

import java.util.Collections;
import java.util.HashMap;
import java.util.Map;
import java.util.concurrent.ConcurrentHashMap;

/**
 * Since synchronizedMap still can throw exception we need to synchronize outside iteration.
 * According to <a href="https://docs.oracle.com/javase/7/docs/api/java/util/Collections.html#synchronizedMap(java.util.Map)">...</a>
 */
public class SynchronizedMapExceptionFix {
    private static final Map<Integer, Integer> map = Collections.synchronizedMap(new HashMap<>());

    public static void main(String[] args) throws InterruptedException {
        Thread addThread = new Thread(() -> {
            for (int i = 0; i < 1000; i++) {
                map.put(i, i);
                System.out.println("Put: " + i);
            }
        });

        Thread sumThread = new Thread(() -> {
            int sum = 0;
            synchronized (map) {
                for (int value : map.values()) {
                    sum += value;
                }
            }
            System.out.println("Sum: " + sum);
        });

        addThread.start();
        sumThread.start();

        addThread.join();
        sumThread.join();
    }
}
