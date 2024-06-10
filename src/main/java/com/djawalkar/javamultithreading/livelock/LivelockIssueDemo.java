package com.djawalkar.javamultithreading.livelock;

import java.util.concurrent.TimeUnit;
import java.util.concurrent.locks.Lock;
import java.util.concurrent.locks.ReentrantLock;

public class LivelockIssueDemo{
	private static final Lock l1 = new ReentrantLock();
	private static final Lock l2 = new ReentrantLock();

	public static void main(String[] args) {
		// Thread1
		new Thread(() -> {
			while (true) {
				try {
					if (!l1.tryLock(1, TimeUnit.SECONDS)) {
						System.out.println("Thread 1: Unable to capture lock on l1, retrying...");
						continue;
					}
					else
						System.out.println("Thread 1: Captured lock on l1");

					if (!l2.tryLock(1, TimeUnit.SECONDS)) {
						System.out.println("Thread 1: Unable to capture lock on l2");
						System.out.println("Thread 1: releasing lock on l1");
						l1.unlock();
						continue;
					}
					else
						System.out.println("Thread 1: Captured lock on l2");

					// do some work

					l2.unlock();
					l1.unlock();
				} catch (InterruptedException e) {
					Thread.currentThread().interrupt();
				}
			}
		}).start();

		// Thread2
		new Thread(() -> {
			while (true) {
				try {
					if (!l2.tryLock(1, TimeUnit.SECONDS)) {
						System.out.println("Thread 2: Unable to capture lock on l2, retrying...");
						continue;
					}
					else
						System.out.println("Thread 2: Captured lock on l2");

					if (!l1.tryLock(1, TimeUnit.SECONDS)) {
						System.out.println("Thread 2: Unable to capture lock on l1");
						System.out.println("Thread 2: releasing lock on l2");
						l2.unlock();
						continue;
					}
					else
						System.out.println("Thread 2: Captured lock on l1");

					// do some work

					l1.unlock();
					l2.unlock();
				} catch (InterruptedException e) {
					Thread.currentThread().interrupt();
				}
			}
		}).start();
	}
}