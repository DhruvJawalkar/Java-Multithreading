package com.djawalkar.javamultithreading.completablefuture;

import java.util.concurrent.CompletableFuture;
import java.util.concurrent.ExecutionException;

public class CompletionStageThenAcceptBothExample {
	public static void main(String[] args) throws InterruptedException, ExecutionException {
		CompletableFuture<Void> future = CompletableFuture.supplyAsync(() -> "First")
				.thenAcceptBoth(CompletableFuture
						.supplyAsync(() -> " Second"), (s1, s2) -> System.out.println(s1 + s2));
	}

}
