package ru.javawebinar.topjava;

import org.junit.rules.TestRule;
import org.junit.runner.Description;
import org.junit.runners.model.Statement;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.util.concurrent.TimeUnit;

public class MethodExecutionTimeLogger implements TestRule {
    private static final Logger log = LoggerFactory.getLogger(MethodExecutionTimeLogger.class);
    @Override
    public Statement apply(Statement statement, Description description) {
        return new Statement() {
            @Override
            public void evaluate() throws Throwable {
                long startTime = System.nanoTime();
                statement.evaluate();
                long endTime = System.nanoTime();
                long duration = TimeUnit.NANOSECONDS.toMillis(endTime - startTime);
                log.debug("{}: Время выполнения - {} миллисекунд", description.getMethodName(), duration);
            }
        };
    }
}