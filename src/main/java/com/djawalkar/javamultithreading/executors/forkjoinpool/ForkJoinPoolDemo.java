package com.djawalkar.javamultithreading.executors.forkjoinpool;

import java.util.concurrent.ForkJoinPool;

public class ForkJoinPoolDemo {
	
	public static void main(String[] args) {
		ForkJoinPool commonPool = ForkJoinPool.commonPool();
		
		ForkJoinPool forkJoinPool = new ForkJoinPool(4);
		
		ForkJoinPool forkJoinPool2 = new ForkJoinPool();
		
		forkJoinPool2.invoke(new DefaultRecursiveAction(32));

	}

}
