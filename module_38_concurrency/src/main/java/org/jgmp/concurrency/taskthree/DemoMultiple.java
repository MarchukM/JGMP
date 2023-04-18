package org.jgmp.concurrency.taskthree;

import java.util.ArrayList;
import java.util.List;

import static java.lang.Thread.sleep;

public class DemoMultiple {
    public static final int MILLIS_TO_RUN = 20000;
    private static final int NUM_OF_CONSUMERS_PRODUCERS = 3;

    public static void main(String[] args) throws InterruptedException {
        MessageBus messageBus = new MessageBus(5);

        Producer producer = new Producer(messageBus);
        Consumer consumer = new Consumer(messageBus);

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

        producer.stop();
        consumer.stop();

        threadList.forEach(DemoMultiple::joinThread);
    }

    public static void joinThread(Thread thread) {
        try {
            thread.join();
        } catch (InterruptedException e) {
            throw new RuntimeException(e);
        }
    }
}
