package de.ketrwu.lametric;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.google.common.collect.ImmutableMap;
import de.ketrwu.lametric.entity.lametric.LaMetricRequest;
import org.slf4j.LoggerFactory;
import org.slf4j.event.Level;

import java.util.Map;

public class Logger {

    private org.slf4j.Logger logger;

    public Logger(Class clazz) {
        this.logger = LoggerFactory.getLogger(clazz);
    }

    private void log(LaMetricRequest request, Level level, String message, Object obj) {
        Map<String, Object> map = ImmutableMap.of(
                "request", request,
                "level", level.name(),
                "message", message,
                "data", obj
        );
        try {
            String content = JacksonUtils.getObjectMapper().writeValueAsString(map);
            switch (level) {
                case ERROR:
                    logger.error(content);
                    break;
                case WARN:
                    logger.warn(content);
                    break;
                default:
                    logger.info(content);
            }
        } catch (JsonProcessingException ignored) {
            logger.error(message, obj);
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
