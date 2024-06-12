package com.djawalkar.javamultithreading.completablefuture;

import java.util.concurrent.CompletableFuture;
import java.util.concurrent.ExecutionException;

public class SupplyAsyncExample {
	public static void main(String[] args) throws InterruptedException, ExecutionException {
		CompletableFuture<String> futureResult = CompletableFuture.supplyAsync(() -> "Hello");
		System.out.println(futureResult.get());
	}

}
