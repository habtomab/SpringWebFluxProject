package com.calculation_app.entity;

import com.calculation_app.exception.RetryOnAPIFailureException;
import org.hibernate.annotations.CurrentTimestamp;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.web.client.HttpServerErrorException;
import org.springframework.web.reactive.function.client.WebClientException;
import reactor.util.retry.Retry;
import java.time.Duration;
import java.time.LocalDateTime;

public class RetryPolicy {


    private static final Logger logger = LoggerFactory.getLogger(RetryPolicy.class);

    public static Retry createRetryPolicy() {
        return Retry
                .backoff(3, Duration.ofSeconds(2))  // Retry up to 3 times with exponential backoff
                .filter(ex -> ex instanceof HttpServerErrorException || ex instanceof WebClientException)
                .onRetryExhaustedThrow((retrySpec, retrySignal) -> {
                    logger.error("Retries exhausted, failure: {}", retrySignal.failure());
                    return new RetryOnAPIFailureException("Max retries exhausted due to error", retrySignal.failure());
                })
                .doBeforeRetry(retrySignal -> {
                    logger.warn("Retrying due to error: {}. Retry count: {}", retrySignal.failure(), retrySignal.totalRetries(),LocalDateTime.now().getMinute());
                });
    }
}

