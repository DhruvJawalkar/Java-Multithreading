package com.djawalkar.javamultithreading.locks;

import java.util.concurrent.Executors;
import java.util.concurrent.TimeUnit;
import java.util.concurrent.locks.ReentrantLock;

public class TryLockMethodExample {
	
	private static ReentrantLock lock = new ReentrantLock();
	
	public static void main(String[] args) {
		var es = Executors.newFixedThreadPool(2);
		
		es.submit(TryLockMethodExample::doSomethingLong);
		es.submit(TryLockMethodExample::doSomethingLong);
		
		es.shutdown();
	}

	private static void doSomethingLong() {
		try {
			if (lock.tryLock(2, TimeUnit.SECONDS)) {
				System.out.println("lock is acquired: " + lock.isLocked());
				TimeUnit.SECONDS.sleep(3);
			} else {
				System.out.println("Thread didn't acquired a lock: " 
							+ Thread.currentThread().getName());
			}
		} catch (InterruptedException e) {
			e.printStackTrace();
		} finally {
			lock.unlock();
		}
		
	}

}
