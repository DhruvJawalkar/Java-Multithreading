package com.djawalkar.javamultithreading.callable;

import java.util.concurrent.*;

public class CallableDemo {
	
	public static void main(String[] args) throws InterruptedException, ExecutionException, TimeoutException {
		
		ExecutorService es = Executors.newCachedThreadPool();
		Future<Integer> future = es.submit(() -> 1 + 1);
		es.shutdown();

		System.out.println(future.get(10, TimeUnit.SECONDS));

	}

}
