package org.meli.remote;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

public class APIOne extends API {

    private static Logger log = LoggerFactory.getLogger(APIOne.class);

    public static String get(Integer seconds) {
        wait(Long.valueOf(seconds * 1000));
        log.info("Any value from the GET API one [delay={}s]", seconds);
        return "Any value from the GET API one";
    }

}
