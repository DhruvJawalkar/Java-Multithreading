package com.djawalkar.javamultithreading.completablefuture;

import java.util.concurrent.CompletableFuture;
import java.util.concurrent.ExecutionException;

public class CompletionStageThenRunExample {
	public static void main(String[] args) throws InterruptedException, ExecutionException {
		var future = CompletableFuture.runAsync(() -> {
			System.out.println("Saving User Profile via external API...");
		}).thenRun(() -> {
			System.out.println("User Profile Saved");
		});
		
		/*
		System.out.println(future.get());
		var es = Executors.newCachedThreadPool();
		CompletableFuture.runAsync(() -> {
			System.out.println("Saving User Profile via external API...");
		}).thenRunAsync(() -> {
			System.out.println("User Profile Saved");
		}, es);
		
		es.shutdown();
		 */
	}

}
