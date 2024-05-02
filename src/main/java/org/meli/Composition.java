package org.meli;

import org.meli.remote.APIOne;
import org.meli.remote.APITwo;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.util.concurrent.CompletableFuture;
import java.util.concurrent.ExecutionException;

public class Composition {

    private static Logger log = LoggerFactory.getLogger(Composition.class);

    public static void main(String[] args) throws ExecutionException, InterruptedException {

        log.info("First log ThreadName={}", Thread.currentThread().getName());
        var mainThreadFuture = CompletableFuture.completedFuture(APIOne.get(2))
            .thenCompose(getResult -> {
                log.info("result={}", getResult);
                return CompletableFuture.completedFuture(String.format("\"id\"=\"%s\"}", getResult));
            }).thenComposeAsync(idPathParam -> {
                log.info("result={}", idPathParam);
                return CompletableFuture.completedFuture(APITwo.get(idPathParam, 3));
            });

        log.info("Second log ThreadName={}", Thread.currentThread().getName());
        log.info("mainThreadFuture={}", mainThreadFuture.get());
    }


}
