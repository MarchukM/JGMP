package org.jgmp.concurrency.taskfour;

import java.util.LinkedList;

public class BlockingObjectPool<T> {

    private final LinkedList<T> pool = new LinkedList<>();
    private final int maxSize;
    private boolean keepGoing = true;


    /**
     * Creates filled pool of passed size
     *
     * @param size of pool
     */
    public BlockingObjectPool(int size) {
        this.maxSize = size;
    }

    /**
     * Gets object from pool or blocks if pool is empty
     *
     * @return object from pool
     */
    public synchronized T get() throws InterruptedException {
        while (pool.isEmpty()) {
            wait(1000);
            if(!keepGoing) {
                return null;
            }
        }
        T object;
        if (pool.size() == maxSize) {
            object = pool.remove();
            notifyAll();
        } else {
            object = pool.remove();
        }
        System.out.println("Pool size: " + pool.size());
        return object;
    }

    /**
     * Puts object to pool or blocks if pool is full
     *
     * @param object to be taken back to pool
     */
    public synchronized void take(T object) throws InterruptedException {
        while (pool.size() == maxSize) {
            wait(1000);
            if(!keepGoing) {
                return;
            }
        }
        if (pool.isEmpty()) {
            pool.add(object);
            notifyAll();
        } else {
            pool.add(object);
        }
        System.out.println("Pool size: " + pool.size());
    }

    public boolean isKeepGoing() {
        return keepGoing;
    }

    public void setKeepGoing(boolean keepGoing) {
        this.keepGoing = keepGoing;
    }
}
