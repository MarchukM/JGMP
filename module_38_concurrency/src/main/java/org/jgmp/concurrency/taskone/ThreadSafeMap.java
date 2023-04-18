package org.jgmp.concurrency.taskone;

import java.util.HashMap;

public class ThreadSafeMap<K, V> extends HashMap<K, V> {
    private final Object lock = new Object();

    public ThreadSafeMap() {
        super();
    }

    @Override
    public V put(K key, V value) {
        synchronized (lock) {
            return super.put(key, value);
        }
    }

    @Override
    public V get(Object key) {
        synchronized (lock) {
            return super.get(key);
        }
    }
}
