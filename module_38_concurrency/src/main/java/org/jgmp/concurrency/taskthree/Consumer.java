package org.jgmp.concurrency.taskthree;

public class Consumer implements Runnable {
    private final MessageBus messageBus;

    public Consumer(MessageBus messageBus) {
        this.messageBus = messageBus;
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
        while (messageBus.keepGoing()) {
            if (messageBus.isEmpty()) {
                System.out.printf("[%s] Bus is empty. Waiting...\n", Thread.currentThread().getName());
                messageBus.waitOnEmpty();
            }
            if (!messageBus.keepGoing()) {
                break;
            }
            Message message = messageBus.remove();
            messageBus.notifyAllSomeFreeSpace();
            consumeMessage(message);
        }
        System.out.println("Consumer Stopped");
    }

    private void consumeMessage(Message message) throws InterruptedException {
        if (message != null) {
            System.out.printf("[%s] Consuming Message: %s\n", Thread.currentThread().getName(), message);
            Thread.sleep((long) (Math.random() * 1500));
        }
    }

    public void stop() {
        messageBus.setRunFlag(false);
        messageBus.notifyAllSomeDataAdded();
    }
}
