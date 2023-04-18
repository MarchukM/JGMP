package org.jgmp.concurrency.taskone;

import java.util.Map;
import java.util.concurrent.ConcurrentHashMap;

public class ConcurrentHashMapExceptionFix {
    private static Map<Integer, Integer> map = new ConcurrentHashMap<>();

    public static void main(String[] args) throws InterruptedException {
        Thread addThread = new Thread(() -> {
            for (int i = 0; i < 1000; i++) {
                map.put(i, i);
                System.out.println("Put: " + i);
            }
        });

        Thread sumThread = new Thread(() -> {
            int sum = 0;
            for (int value : map.values()) {
                sum += value;
            }
            System.out.println("Sum: " + sum);
        });

        addThread.start();
        sumThread.start();

        addThread.join();
        sumThread.join();
    }
}
