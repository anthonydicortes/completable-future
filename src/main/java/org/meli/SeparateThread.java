package org.meli;

import org.meli.remote.APIOne;
import org.meli.remote.APITwo;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.util.concurrent.*;

public class SeparateThread {

    private static Logger log = LoggerFactory.getLogger(SeparateThread.class);

    private static final Executor T_POOL_1 = new ThreadPoolExecutor(1, 1, 0L, TimeUnit.MILLISECONDS,
        new LinkedBlockingQueue<>());
    private static final Executor T_POOL_2 = new ThreadPoolExecutor(1, 1, 0L, TimeUnit.MILLISECONDS,
        new LinkedBlockingQueue<>());

    public static void main(String[] args) throws ExecutionException, InterruptedException {

        log.info("First log ThreadName={}", Thread.currentThread().getName());
        var separateThreadFuture = CompletableFuture.supplyAsync(() -> APIOne.get(2))
            .thenApplyAsync(getResult -> {
                log.info("result={}", getResult);
                return String.format("{\"body\": \"%s\"}", getResult);
            }, T_POOL_1).thenAcceptAsync(body -> {
                log.info("result={}", body);
                APITwo.post(body, 3);
            }, T_POOL_2);

        log.info("Second log ThreadName={}", Thread.currentThread().getName());
        log.info("separateThreadFuture={}", separateThreadFuture.get());
    }
}
