package com.djawalkar.javamultithreading.locks;

import java.util.*;
import java.util.concurrent.locks.Lock;
import java.util.concurrent.locks.ReadWriteLock;
import java.util.concurrent.locks.ReentrantReadWriteLock;

class CachedData {
    Object data;
    boolean cacheValid;
    final ReentrantReadWriteLock rwl = new ReentrantReadWriteLock();

    void processCachedData() {
        rwl.readLock().lock();
        if (!cacheValid) {
            // Must release read lock before acquiring write lock
            rwl.readLock().unlock();
            rwl.writeLock().lock();
            try {
                // Recheck state because another thread might have
                // acquired write lock and changed state before we did.
                if (!cacheValid) {
                    data = "...";
                    cacheValid = true;
                }
                // Downgrade by acquiring read lock before releasing write lock
                rwl.readLock().lock();
            } finally {
                rwl.writeLock().unlock(); // Unlock write, still hold read
            }
        }

        try {
            //use(data);
            System.out.println(data);
        } finally {
            rwl.readLock().unlock();
        }
    }
}
//ReentrantReadWriteLocks can be used to improve concurrency in some uses of some kinds of Collections.
//This is typically worthwhile only when the collections are expected to be large, accessed by more reader threads than writer threads, and entail operations with overhead that outweighs synchronization overhead.
class RWDictionary {
    private final Map<String, Integer> m = new TreeMap<>();
    private final ReentrantReadWriteLock rwl = new ReentrantReadWriteLock();
    private final Lock r = rwl.readLock();
    private final Lock w = rwl.writeLock();

    public Integer get(String key) {
        r.lock();
        try { return m.get(key); }
        finally { r.unlock(); }
    }
    public List<String> allKeys() {
        r.lock();
        try { return new ArrayList<>(m.keySet()); }
        finally { r.unlock(); }
    }
    public Integer put(String key, Integer value) {
        w.lock();
        try { return m.put(key, value); }
        finally { w.unlock(); }
    }
    public void clear() {
        w.lock();
        try { m.clear(); }
        finally { w.unlock(); }
    }
}

public class ReentrantReadWriteLockExample {
    public static void main(String[] args) {
        RWDictionary dict = new RWDictionary();
        dict.put("1", 1);
        dict.get("1");
        dict.clear();
    }
}
