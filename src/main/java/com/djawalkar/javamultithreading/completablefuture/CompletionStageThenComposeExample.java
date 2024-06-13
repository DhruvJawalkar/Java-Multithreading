package com.djawalkar.javamultithreading.completablefuture;

import java.util.concurrent.CompletableFuture;
import java.util.concurrent.ExecutionException;

public class CompletionStageThenComposeExample {
	public static void main(String[] args) throws InterruptedException, ExecutionException {
		CompletableFuture<Double> result = getUserDetailById(125).thenCompose(CompletionStageThenComposeExample::getCreditRating);
		System.out.println(result.get());

		//CompletableFuture<CompletableFuture<Double>> result = getUserDetailById(125).thenApply(Demo7::getCreditRating);
	}
	
	private static CompletableFuture<String> getUserDetailById(int userId) {
		return CompletableFuture.supplyAsync(() -> {
			return "user details string";
		});	
	}

	private static CompletableFuture<Double> getCreditRating(String userDetails) {
		return CompletableFuture.supplyAsync(() -> {
			return 110.98;
		});
	}
}
