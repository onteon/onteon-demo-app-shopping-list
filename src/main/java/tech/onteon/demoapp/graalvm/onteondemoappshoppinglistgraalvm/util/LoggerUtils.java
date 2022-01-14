package tech.onteon.demoapp.graalvm.onteondemoappshoppinglistgraalvm.util;

import ch.qos.logback.classic.Level;
import ch.qos.logback.classic.Logger;
import ch.qos.logback.classic.LoggerContext;
import ch.qos.logback.classic.spi.ILoggingEvent;
import ch.qos.logback.contrib.json.classic.JsonLayout;
import ch.qos.logback.core.FileAppender;
import org.slf4j.LoggerFactory;
import tech.onteon.demoapp.graalvm.onteondemoappshoppinglistgraalvm.logger.JsonLoggerFormatter;

public class LoggerUtils {
    public static Logger createLoggerFor(Class<?> clazz) {
        final Logger logger = (Logger) LoggerFactory.getLogger(clazz.getName());
        logger.addAppender(getJsonFileAppender());
        logger.setLevel(Level.INFO);
        logger.setAdditive(false);

        return logger;
    }

    private static FileAppender<ILoggingEvent> getJsonFileAppender() {
        final LoggerContext loggerContext = (LoggerContext) LoggerFactory.getILoggerFactory();
        final FileAppender<ILoggingEvent> fileAppender = new FileAppender<>();
        fileAppender.setFile(System.getProperty("log.dir") + "/log.json");
        fileAppender.setAppend(true);
        fileAppender.setLayout(getJsonLayout());
        fileAppender.setContext(loggerContext);
        fileAppender.start();

        return fileAppender;
    }

    private static JsonLayout getJsonLayout() {
        final LoggerContext loggerContext = (LoggerContext) LoggerFactory.getILoggerFactory();
        final JsonLayout jsonLayout = new JsonLayout();
        jsonLayout.setJsonFormatter(new JsonLoggerFormatter());
        jsonLayout.setAppendLineSeparator(true);
        jsonLayout.setContext(loggerContext);
        jsonLayout.start();

        return jsonLayout;
    }
}