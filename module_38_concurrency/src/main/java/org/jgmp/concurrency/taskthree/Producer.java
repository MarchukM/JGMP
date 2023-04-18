package org.jgmp.concurrency.taskthree;

import static org.jgmp.concurrency.taskthree.Util.generateRandomMessage;

public class Producer implements Runnable {

    private final MessageBus messageBus;
    private long messageId;

    public Producer(MessageBus messageBus) {
        this.messageBus = messageBus;
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
        while (messageBus.keepGoing()) {
            if (messageBus.isFull()) {
                System.out.printf("[%s] Bus is full. Waiting...\n", Thread.currentThread().getName());
                messageBus.waitOnFull();
            }
            if (!messageBus.keepGoing()) {
                break;
            }
            Message message = generateMessage();
            messageBus.add(message);
            System.out.printf("[%s] Message added to bus: %s\n", Thread.currentThread().getName(), message);
            messageBus.notifyAllSomeDataAdded();
        }
        System.out.println("Producer Stopped");
    }

    private Message generateMessage() throws InterruptedException {
        Message message = new Message();
        message.setId(messageId++);
        message.setMessage(generateRandomMessage());
        Thread.sleep((long) (Math.random() * 1500));
        return message;
    }

    public void stop() {
        messageBus.setRunFlag(false);
        messageBus.notifyAllSomeFreeSpace();
    }
}
