package org.meli;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.util.concurrent.CompletableFuture;
import java.util.concurrent.ExecutionException;

public class ParallelExecution {

    private static Logger log = LoggerFactory.getLogger(ParallelExecution.class);

    public static void main(String[] args) throws ExecutionException, InterruptedException {
        CompletableFuture<String> firstFiveNumbersFuture1 = CompletableFuture.supplyAsync(() -> logFirstNumbers(5));
        CompletableFuture<String> firstFiveNumbersFuture2 = CompletableFuture.supplyAsync(() -> logFirstNumbers(5));
        CompletableFuture<String> firstFiveNumbersFuture3 = CompletableFuture.supplyAsync(() -> logFirstNumbers(5));
//        CompletableFuture<String> combinedFuture = firstFiveNumbersFuture1
//            .thenCombine(firstFiveNumbersFuture2, (s1, s2) -> s1 + s2);

//        CompletableFuture<String> firstFiveNumbersFuture1 = CompletableFuture.completedFuture(logFirstNumbers(5));
//        CompletableFuture<String> firstFiveNumbersFuture2 = CompletableFuture.completedFuture(logFirstNumbers(5));
//        CompletableFuture<String> combinedFuture = firstFiveNumbersFuture1
//            .thenCombine(firstFiveNumbersFuture2, (s1, s2) -> s1 + s2);


        var voidCompletableFuture = CompletableFuture.anyOf(firstFiveNumbersFuture1, firstFiveNumbersFuture2, firstFiveNumbersFuture3);
//            .thenApply(() -> System.out.println("Hola"));
        log.info("result={}", voidCompletableFuture.get());

//        log.info("result={}", combinedFuture.get());
    }

    public static String logFirstNumbers(Integer n) {
        for (int i = 0; i < n; i++) {
            log.info("{}", i + 1);
        }
        return "Done";
    }

}
