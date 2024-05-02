package org.meli.remote;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

public class APITwo extends API {

    private static Logger log = LoggerFactory.getLogger(APITwo.class);

    public static String get(String idPathParam, Integer seconds) {
        wait(Long.valueOf(seconds * 1000));
        log.info("Any value from the GET API two [delay={}s]", seconds);
        return "Any value from the GET API two";
    }
    public static void post(String body, Integer seconds) {
        wait(Long.valueOf(seconds * 1000));
        log.info("POST API two [body={},delay={}s]", body, seconds);
    }
}
