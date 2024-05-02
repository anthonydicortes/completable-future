package org.meli;

import org.meli.remote.APIOne;
import org.meli.remote.APITwo;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.util.concurrent.CompletableFuture;
import java.util.concurrent.ExecutionException;


public class Main {

    private static Logger log = LoggerFactory.getLogger(Main.class);

    public static void main(String[] args) throws ExecutionException, InterruptedException {

        log.info("MainThreadName={}", Thread.currentThread().getName());
        var mainThreadFutures = CompletableFuture.completedFuture("First successful future")
            .thenCompose(futureResult -> {
                log.info("result={}", futureResult);
//                return CompletableFuture.completedFuture(APIOne.get(10000L));
                return CompletableFuture.supplyAsync(() -> APIOne.get(1));
            });

        log.info("MainThreadName={}", Thread.currentThread().getName());
        System.out.println(mainThreadFutures.get());
    }


    public void secondExample() throws ExecutionException, InterruptedException {
        System.out.println("Hello world!");

        var successfulFuture = CompletableFuture.completedFuture(APIOne.get(1));
        var successfulAsyncFuture = CompletableFuture.runAsync(() -> APIOne.get(1));
        var successfulSupplyAsyncFuture = CompletableFuture.supplyAsync(() -> APITwo.get("Any query param", 1));
//            .thenApply(value -> value);

        var failedFuture = CompletableFuture.failedFuture(new IllegalArgumentException("Failed future"));

        System.out.println(successfulFuture.get());
        System.out.println(successfulAsyncFuture.get());
        System.out.println(successfulSupplyAsyncFuture.get());
        System.out.println(failedFuture.get());
    }


    public void firstExample() {


        // Will be executed in the main thread
        CompletableFuture.completedFuture("A successful future");
        CompletableFuture.failedFuture(new IllegalArgumentException("A failed future"));

        // Will be executed asynchronously in a separate thread
        CompletableFuture.supplyAsync(() -> "A successful asynchronous future");
        CompletableFuture.runAsync(() -> System.out.println("A successful asynchronous runnable future"));


        CompletableFuture<String> successfulFuture = CompletableFuture.completedFuture("A successful future");
        CompletableFuture<String> failedFuture = CompletableFuture.failedFuture(new IllegalArgumentException("A failed future"));


        if (successfulFuture.isDone()) {
            System.out.println("Is Done");
        }
    }
}