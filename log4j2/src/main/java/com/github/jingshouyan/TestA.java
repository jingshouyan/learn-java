package com.github.jingshouyan;

import lombok.extern.slf4j.Slf4j;
import org.apache.logging.log4j.core.Logger;
import org.apache.logging.log4j.core.LoggerContext;

import java.util.Collection;

/**
 * @author jingshouyan
 * 2022-12-16 15:46
 */
@Slf4j
public class TestA {

    public void call() {
        LoggerContext ctx0 = LoggerContext.getContext(false);
        Collection<Logger> loggers = ctx0.getLoggers();

        log.error("test a {}", loggers);
    }
}
