package org.jgmp.concurrency.taskfour;

import org.jgmp.concurrency.taskthree.Message;

import java.util.ArrayList;
import java.util.List;

import static java.lang.Thread.sleep;

public class Demo {

    public static final int MILLIS_TO_RUN = 5000;
    public static final int TO_WAIT = 1000;
    private static final int NUM_OF_CONSUMERS_PRODUCERS = 3;

    public static void main(String[] args) throws InterruptedException {
        BlockingObjectPool<Message> pool = new BlockingObjectPool<>(4);
        Producer producer = new Producer(pool);
        Consumer consumer = new Consumer(pool);

        List<Thread> threadList = new ArrayList<>();

        for (int i = 0; i < NUM_OF_CONSUMERS_PRODUCERS; i++) {
            Thread producerThread = new Thread(producer);
            Thread consumerThread = new Thread(consumer);
            producerThread.start();
            consumerThread.start();
            threadList.add(producerThread);
            threadList.add(consumerThread);
        }

        sleep(MILLIS_TO_RUN);

        pool.setKeepGoing(false);

        threadList.forEach(Demo::joinThread);
    }

    public static void joinThread(Thread thread) {
        try {
            thread.join();
        } catch (InterruptedException e) {
            throw new RuntimeException(e);
        }
    }

}
