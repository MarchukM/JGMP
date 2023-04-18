package org.jgmp.concurrency.taskthree;


import static java.lang.Thread.sleep;

public class DemoSingle {

    public static final int MILLIS_TO_RUN = 20000;

    public static void main(String[] args) throws InterruptedException {
        MessageBus messageBus = new MessageBus(5);

        Producer producer = new Producer(messageBus);
        Thread producerThread = new Thread(producer);
        Consumer consumer = new Consumer(messageBus);
        Thread consumerThread = new Thread(consumer);

        producerThread.start();
        consumerThread.start();

        sleep(MILLIS_TO_RUN);

        producer.stop();
        consumer.stop();

        producerThread.join();
        consumerThread.join();
    }
}
