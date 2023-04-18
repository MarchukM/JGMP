package org.jgmp.concurrency.taskfour;

import org.jgmp.concurrency.taskthree.Message;

import static org.jgmp.concurrency.taskfour.Demo.TO_WAIT;

public class Consumer implements Runnable {
    BlockingObjectPool<Message> pool;

    public Consumer(BlockingObjectPool<Message> pool) {
        this.pool = pool;
    }

    @Override
    public void run() {
        try {
            consume();
        } catch (InterruptedException e) {
            throw new RuntimeException(e);
        }
    }

    public void consume() throws InterruptedException {
        while (pool.isKeepGoing()) {
            Message message = pool.get();
            consumeMessage(message);
        }
        System.out.println("Consumer Stopped");
    }

    private void consumeMessage(Message message) throws InterruptedException {
        if (message != null) {
            System.out.printf("[%s] Consuming Message: %s\n", Thread.currentThread().getName(), message);
            Thread.sleep((long) (Math.random() * TO_WAIT));
        }
    }
}