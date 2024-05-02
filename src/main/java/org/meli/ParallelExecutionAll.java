package org.meli;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.util.concurrent.CompletableFuture;
import java.util.concurrent.ExecutionException;

public class ParallelExecutionAll {

    private static Logger log = LoggerFactory.getLogger(ParallelExecutionAll.class);

    public static void main(String[] args) throws ExecutionException, InterruptedException {
        CompletableFuture<String> firstFiveNumbersFuture1 = CompletableFuture.supplyAsync(() -> logFirstNumbers(5));
        CompletableFuture<String> firstFiveNumbersFuture2 = CompletableFuture.supplyAsync(() -> logFirstNumbers(5));
        CompletableFuture<String> firstFiveNumbersFuture3 = CompletableFuture.supplyAsync(() -> logFirstNumbers(5));

        var voidCompletableFuture = CompletableFuture.allOf(firstFiveNumbersFuture1,
            firstFiveNumbersFuture2,
            firstFiveNumbersFuture3);
        log.info("result={}", voidCompletableFuture.get());
    }

    public static String logFirstNumbers(Integer n) {
        for (int i = 0; i < n; i++) {
            log.info("{}", i + 1);
        }
        return "Done";
    }
}
