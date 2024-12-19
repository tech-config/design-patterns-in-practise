package com.design.patterns.commander;

import java.security.SecureRandom;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.concurrent.atomic.AtomicInteger;
import java.util.function.Predicate;

public class Retry<T> {

    public interface Operation {
        void operation(List<Exception> exceptionList) throws Exception;
    }

    public interface HandleErrorIssue<T> {
        void handleIssue(T obj, Exception e);
    }

    private static final SecureRandom RANDOM = new SecureRandom();

    private final Operation op;
    private final HandleErrorIssue<T> handleErrorIssue;
    private final int maxAttempts;
    private long maxDelay;
    private final AtomicInteger attempts;
    private final Predicate<Exception> test;
    private final List<Exception> errors;

    public Retry(Operation operation,
                 HandleErrorIssue<T> handleErrorIssue,
                 int maxAttempts,
                 long maxDelay,
                 AtomicInteger attempts,
                 Predicate<Exception>... ignoreTests) {
        this.op = operation;
        this.handleErrorIssue = handleErrorIssue;
        this.maxAttempts = maxAttempts;
        this.maxDelay = maxDelay;
        this.attempts = attempts;
        this.test = Arrays.stream(ignoreTests).reduce(Predicate::or).orElse(e -> false);
        this.errors = new ArrayList<>();
    }

    public void perform(List<Exception> list, T obj) {
        do {
            try {
                op.operation(list);
            } catch (Exception e) {
                this.errors.add(e);
                if (this.attempts.incrementAndGet() >= maxAttempts || !this.test.test(e)) {
                    this.handleErrorIssue.handleIssue(obj, e);
                    return;
                }
                try {
                    long testDelay = (long) Math.pow(2, this.attempts.intValue()) * 1000 + RANDOM.nextInt(1000);
                    long delay = Math.min(testDelay, this.maxDelay);
                    Thread.sleep(delay);
                } catch (InterruptedException f) {
                    // ignore
                }
            }
        } while (true);
    }
}
