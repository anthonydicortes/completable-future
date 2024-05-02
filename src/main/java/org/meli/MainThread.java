package org.meli;

import org.meli.remote.APIOne;
import org.meli.remote.APITwo;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.util.concurrent.CompletableFuture;
import java.util.concurrent.ExecutionException;

public class MainThread {

    private static Logger log = LoggerFactory.getLogger(MainThread.class);

    public static void main(String[] args) throws ExecutionException, InterruptedException {

        log.info("First log ThreadName={}", Thread.currentThread().getName());
        var mainThreadFuture = CompletableFuture.completedFuture(APIOne.get(2))
            .thenApply(getResult -> {
                log.info("result={}", getResult);
                return String.format("{\"body\": \"%s\"}", getResult);
            }).thenAccept(body -> {
                log.info("result={}", body);
                APITwo.post(body, 3);
            });

        log.info("Second log ThreadName={}", Thread.currentThread().getName());
        log.info("mainThreadFuture={}", mainThreadFuture.get());
    }
}
