package de.ketrwu.lametric;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.google.common.collect.ImmutableMap;
import de.ketrwu.lametric.entity.lametric.LaMetricRequest;
import org.apache.logging.log4j.Level;
import org.apache.logging.log4j.LogManager;

import java.util.Map;

public class Logger {

    private org.apache.logging.log4j.Logger logger;

    public Logger(Class clazz) {
        this.logger = LogManager.getLogger(clazz);
    }

    private void log(LaMetricRequest request, Level level, String message, Object obj) {
        Map<String, Object> map = ImmutableMap.of(
                "request", request,
                "level", level.name(),
                "message", message,
                "data", obj
        );
        try {
            logger.log(level, JacksonUtils.getObjectMapper().writeValueAsString(map));
        } catch (JsonProcessingException irgnored) {
            logger.log(level, message, obj);
        }
    }

    public void info(LaMetricRequest request, String message, Object obj) {
        log(request, Level.INFO, message, obj);
    }

    public void warn(LaMetricRequest request, String message, Object obj) {
        log(request, Level.WARN, message, obj);
    }

    public void error(LaMetricRequest request, String message, Object obj) {
        log(request, Level.ERROR, message, obj);
    }
}
