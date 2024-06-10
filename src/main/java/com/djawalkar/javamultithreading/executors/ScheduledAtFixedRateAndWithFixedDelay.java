package com.djawalkar.javamultithreading.executors;

import java.util.concurrent.Executors;
import java.util.concurrent.TimeUnit;

public class ScheduledAtFixedRateAndWithFixedDelay {

	public static void main(String[] args) throws InterruptedException {

		var es = Executors.newScheduledThreadPool(4);

		es.scheduleAtFixedRate(() -> {
			System.out.println("Hello");
			try {
				TimeUnit.MILLISECONDS.sleep(500);
			} catch (InterruptedException e) {
				System.out.println(Thread.currentThread().getName() + "got interrupted");
			}
		}, 500, 1000, TimeUnit.MILLISECONDS);

		es.awaitTermination(7, TimeUnit.SECONDS);
		es.shutdownNow();


		/*
		var es2 = Executors.newScheduledThreadPool(4);

		es2.scheduleWithFixedDelay(() -> {
			System.out.println("Hello");
			try {
				TimeUnit.MILLISECONDS.sleep(500);
			} catch (InterruptedException e) {
				System.out.println(Thread.currentThread().getName() + " got interrupted");
			}
		}, 500, 1000, TimeUnit.MILLISECONDS);

		es2.awaitTermination(7, TimeUnit.SECONDS);
		es2.shutdownNow();
		 */
	}

}
