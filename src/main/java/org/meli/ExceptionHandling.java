package org.meli;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.util.Optional;
import java.util.concurrent.CompletableFuture;
import java.util.concurrent.ExecutionException;

import static jdk.dynalink.linker.support.Guards.isNotNull;

public class ExceptionHandling {

    private static Logger log = LoggerFactory.getLogger(ExceptionHandling.class);

    public static void main(String[] args) throws ExecutionException, InterruptedException {
//        var failedCompletableFuture = CompletableFuture.failedFuture(new IllegalArgumentException("A failed future"))
        var failedCompletableFuture = CompletableFuture.completedFuture("All ok")
            .handle((a, ex) -> {
                return Optional.ofNullable(ex)
                    .map()
                log.error("An error occurred [error={}]", ex.getMessage(), ex);
                    return "An error occurred";
            });

        log.info("result={}", failedCompletableFuture.get());
    }
}
