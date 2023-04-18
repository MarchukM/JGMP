package org.jgmp.concurrency.taskone;

import java.util.HashMap;
import java.util.Map;

/**
 * Sometimes throws:
 * Exception in thread "Thread-1" java.util.ConcurrentModificationException
 * at java.util.HashMap$HashIterator.nextNode(HashMap.java:1469)
 * at java.util.HashMap$ValueIterator.next(HashMap.java:1498)
 * at org.jgmp.concurrency.taskone.HashMapExceptionTest.lambda$main$1(HashMapExceptionTest.java:19)
 */
public class HashMapExceptionTest {
    private static Map<Integer, Integer> map = new HashMap<>();

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
