package com.retry.examples.exception;

import java.util.function.Predicate;

public class RetryPredicate implements Predicate<Throwable> {

    @Override
    public boolean test(Throwable throwable) {
        return throwable instanceof IllegalArgumentException;
    }
}
