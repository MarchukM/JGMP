package org.jgmp.concurrency.taskfour;

import org.jgmp.concurrency.taskthree.Message;


import static org.jgmp.concurrency.taskfour.Demo.TO_WAIT;
import static org.jgmp.concurrency.taskthree.Util.generateRandomMessage;

public class Producer implements Runnable {
    BlockingObjectPool<Message> pool;
    private long messageId;

    public Producer(BlockingObjectPool<Message> messageBus) {
        this.pool = messageBus;
    }

    @Override
    public void run() {
        try {
            produce();
        } catch (InterruptedException e) {
            throw new RuntimeException(e);
        }
    }

    private void produce() throws InterruptedException {
        while (pool.isKeepGoing()) {
            Message message = generateMessage();
            if(!pool.isKeepGoing()) {
                break;
            }
            pool.take(message);
        }
        System.out.println("Producer Stopped");
    }

    private Message generateMessage() throws InterruptedException {
        Message message = new Message();
        message.setId(messageId++);
        message.setMessage(generateRandomMessage());
        System.out.printf("[%s] Produced message: %s\n", Thread.currentThread().getName(), message);
        Thread.sleep((long) (Math.random() * TO_WAIT));
        return message;
    }
}
