package com.tarnsaction._app.service;

import com.tarnsaction._app.entity.Transaction;
import com.tarnsaction._app.exception.RetryOnAPIFailureException;
import com.tarnsaction._app.exception.RetryPolicy;
import com.tarnsaction._app.repository.TransactionRepository;
import com.tarnsaction._app.request.TransactionRequest;
import com.tarnsaction._app.response.CalculationResponse;
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
public class TransactionService {

    @Autowired
    private  TransactionRepository repository;
    @Autowired
    private  WebClient webClient;
private final static Logger logger= LoggerFactory.getLogger(TransactionService.class);

       String baseUrl= "http://localhost:8082/calculation";


    public Transaction processTransaction(TransactionRequest request) {
        // Call Calculation Service
        CalculationResponse calculationResponse = webClient.get()
                .uri(baseUrl,uriBuilder -> uriBuilder
                        .path("/{customerId}/{planNumber}")
                        .build(request.getCustomerId(), request.getPlanNumber()))
                .accept(MediaType.APPLICATION_JSON)
                .retrieve()
                .onStatus(HttpStatusCode::is5xxServerError, clientResponse ->
                        Mono.error(new RetryOnAPIFailureException("Server error occurred: " + clientResponse)))
                .onStatus(HttpStatusCode::is4xxClientError, clientResponse ->
                        Mono.error(new ServiceException("Client error occurred: " + clientResponse)))
                .bodyToMono(CalculationResponse.class)
                .doOnError(ex -> logger.error("Error occurred while fetching calculation"))
                .retryWhen(RetryPolicy.createRetryPolicy())  // Applying the retry policy from the RetryPolicy class
                .block(); // Blocking to save the response in a variable
        // Process transaction
        boolean success = calculationResponse != null && calculationResponse.getFee() > 0;
        String message = success ? "Transaction Successful" : "Transaction Failed";

        Transaction transaction = new Transaction();
        transaction.setCustomerId(request.getCustomerId());
        transaction.setPlanNumber(request.getPlanNumber());
        transaction.setSuccess(success);
        transaction.setMessage(message);

        return repository.save(transaction);
    }
}

