package com.djawalkar.javamultithreading.completablefuture;

import java.util.concurrent.CompletableFuture;
import java.util.concurrent.ExecutionException;

public class CompletionStageThenAcceptExample {
	public static void main(String[] args) throws InterruptedException, ExecutionException {
		var future = CompletableFuture.supplyAsync(() -> {
			System.out.println("Request User Profile from external API...");
			return "Some String representation of User Profile";
		}).thenAccept((result) -> {
			System.out.println("Result from previous stage: " + result);
			System.out.println("User Profile Received: " + result);
		});
		
		System.out.println(future.get());

		/*
		var es = Executors.newCachedThreadPool();
		CompletableFuture.supplyAsync(() -> {
			System.out.println("Request User Profile from external API...");
			return "Some String representation of User Profile";
		}).thenAcceptAsync((result) -> {
			System.out.println("User Profile Received: " + result);
		}, es);
		
		es.shutdown();
		 */
	}
}
