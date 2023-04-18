package org.jgmp.concurrency.tasktwo;

import java.util.ArrayList;
import java.util.List;

/**
 * Create three threads:
 * •	1st thread is infinitely writing random number to the collection;
 * •	2nd thread is printing sum of the numbers in the collection;
 * •	3rd is printing square root of sum of squares of all numbers in the collection.
 * Make these calculations thread-safe using synchronization block. Fix the possible deadlock.
 *
 * NOTE: Not sure how it is supposed to get deadlock in this task. Since we have only one sync object, and I'm not sure why could we have more.
 */
public class DeadLock {
    public static final List<Long> collection = new ArrayList<>();
    public static final int MAX_SIZE = 10_000;

    public static void main(String[] args) throws InterruptedException {
        Thread addThread = new Thread(() -> {
            for (long i = 0; i < MAX_SIZE; i++) {
                synchronized (collection) {
                    collection.add(i);
                }
                try {
                    Thread.sleep(200);
                } catch (InterruptedException e) {
                    throw new RuntimeException(e);
                }
                System.out.println("Put: " + i);
            }
        });

        Thread sumThread = new Thread(() -> {
            while (collection.size() < MAX_SIZE) {
                long sum = 0;
                synchronized (collection) {
                    for (long value : collection) {
                        sum += value;
                    }
                    collection.add(sum);
                }
                System.out.println("Sum: " + sum);
                try {
                    Thread.sleep(200);
                } catch (InterruptedException e) {
                    throw new RuntimeException(e);
                }
            }
        });

        Thread squareThread = new Thread(() -> {
            while (collection.size() < MAX_SIZE) {
                synchronized (collection) {
                    int sumOfSquares = 0;
                    for (long value : collection) {
                        sumOfSquares += value * value;
                    }
                    System.out.println("Sqrt of sum of sqares: " + Math.sqrt(sumOfSquares));
                }
                try {
                    Thread.sleep(200);
                } catch (InterruptedException e) {
                    throw new RuntimeException(e);
                }
            }
        });

        addThread.start();
        sumThread.start();
        squareThread.start();

        addThread.join();
        sumThread.join();
        squareThread.join();
    }
}
