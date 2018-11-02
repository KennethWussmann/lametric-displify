package de.ketrwu.lametric;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.google.common.collect.ImmutableMap;
import org.apache.logging.log4j.Level;
import org.apache.logging.log4j.LogManager;

import java.util.Map;

public class Logger {

    private org.apache.logging.log4j.Logger logger;

    public Logger(Class clazz) {
        this.logger = LogManager.getLogger(clazz);
    }

    private void log(Level level, String message, Object obj) {
        Map<String, Object> map = ImmutableMap.of("level", level.name(), "message", message, "data", obj);
        try {
            logger.log(level, JacksonUtils.getObjectMapper().writeValueAsString(map));
        } catch (JsonProcessingException irgnored) {
            logger.log(level, message, obj);
        }
    }

    public void info(String message, Object obj) {
        log(Level.INFO, message, obj);
    }

    public void warn(String message, Object obj) {
        log(Level.WARN, message, obj);
    }

    public void error(String message, Object obj) {
        log(Level.ERROR, message, obj);
    }
}
