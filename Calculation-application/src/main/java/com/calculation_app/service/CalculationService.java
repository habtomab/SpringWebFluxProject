package com.calculation_app.service;

import com.calculation_app.exception.RetryOnAPIFailureException;
import com.calculation_app.entity.Calculation;
import com.calculation_app.entity.RetryPolicy;
import com.calculation_app.repository.CalculationRepository;
import com.calculation_app.respose.EligibilityResponse;
import org.hibernate.service.spi.ServiceException;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatusCode;
import org.springframework.http.MediaType;
import org.springframework.stereotype.Service;
import org.springframework.web.reactive.function.client.WebClient;
import reactor.core.publisher.Mono;

@Service
public class CalculationService {
    @Autowired
    private CalculationRepository repository;
    @Autowired
    private WebClient webClient;
    private static final Logger logger = LoggerFactory.getLogger(CalculationService.class);

    String baseUrl = "http://localhost:8081/eligibility";


    public Calculation calculateFees(String customerId, String planNumber) {
        // Call Eligibility Service

        EligibilityResponse eligibility = webClient.get()
                .uri(baseUrl, uriBuilder -> uriBuilder
                        .path("/{customerId}/{planNumber}")
                        .build(customerId, planNumber))
                .accept(MediaType.APPLICATION_JSON)
                .retrieve()
                .onStatus(HttpStatusCode::is5xxServerError, clientResponse ->
                        Mono.error(new RetryOnAPIFailureException("Server error occurred: " + clientResponse)))
                .onStatus(HttpStatusCode::is4xxClientError, clientResponse ->
                        Mono.error(new ServiceException("Client error occurred: " + clientResponse)))
                .bodyToMono(EligibilityResponse.class)
                .doOnError(ex -> logger.error("Error occurred while fetching eligibility"))
                .retryWhen(RetryPolicy.createRetryPolicy())  // Applying the retry policy from the RetryPolicy class
                .block(); // Blocking to save the response in a variable
        // Calculate fees if eligible
        if (eligibility != null && eligibility.isEligible()) {
            double fee = 100.00; // Example fee calculation
            Calculation calculation = new Calculation();
            calculation.setCustomerId(customerId);
            calculation.setPlanNumber(planNumber);
            calculation.setFee(fee);
            return repository.save(calculation);
        } else {
            throw new RuntimeException("Customer not eligible");
        }
    }
}

