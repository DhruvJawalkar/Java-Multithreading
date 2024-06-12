package com.djawalkar.javamultithreading.completablefuture;

import java.util.concurrent.*;

public class RunAsyncExample {

	public static void main(String[] args) throws InterruptedException, ExecutionException {
		CompletableFuture.runAsync(() -> {
			System.out.println("Thread name: " + Thread.currentThread().getName());
			System.out.println("Http call goes here...");
		});

		/*
		ExecutorService es = Executors.newCachedThreadPool();
		CompletableFuture.runAsync(() -> {
			System.out.println("Thread name: " + Thread.currentThread().getName());
			System.out.println("Http call goes here...");
		}, es);
		
		es.shutdown();
		*/
	}

}
