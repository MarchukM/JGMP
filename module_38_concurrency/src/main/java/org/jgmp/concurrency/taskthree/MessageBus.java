package org.jgmp.concurrency.taskthree;

import java.util.ArrayDeque;
import java.util.Queue;

public class MessageBus {
    private final Object full = new Object();
    private final Object empty = new Object();
    private final Queue<Message> queue= new ArrayDeque<>();
    private final int busSize;
    private boolean runFlag = true;

    public MessageBus(int busSize) {
        this.busSize = busSize;
    }

    public void add(Message message) {
        synchronized (queue) {
            queue.add(message);
        }
    }

    public Message remove() {
        synchronized (queue) {
            return queue.poll();
        }
    }

    public boolean isEmpty() {
        return queue.isEmpty();
    }

    public boolean isFull() {
        return queue.size() == busSize;
    }

    public boolean keepGoing() {
        return runFlag;
    }

    public void setRunFlag(boolean runFlag) {
        this.runFlag = runFlag;
    }

    public void waitOnFull() throws InterruptedException {
        synchronized (full) {
            full.wait();
        }
    }

    public void waitOnEmpty() throws InterruptedException {
        synchronized (empty) {
            empty.wait();
        }
    }

    public void notifyAllSomeFreeSpace() {
        synchronized (full) {
            full.notifyAll();
        }
    }

    public void notifyAllSomeDataAdded() {
        synchronized (empty) {
            empty.notifyAll();
        }
    }
}
