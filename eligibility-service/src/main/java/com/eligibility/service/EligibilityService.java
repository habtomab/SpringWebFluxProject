package com.eligibility.service;

import com.eligibility.constants.AppConstants;
import com.eligibility.customExceptions.RetryOnAPIFailureException;
import com.eligibility.customExceptions.ServiceException;
import com.eligibility.dto.ParticipationAccountsResponse;
import com.eligibility.entity.Eligibility;
import com.eligibility.repository.EligibilityRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.web.reactive.function.client.WebClient;
import org.springframework.http.HttpHeaders;
import org.springframework.http.MediaType;
import org.springframework.web.client.HttpClientErrorException;
import org.springframework.web.client.HttpServerErrorException;
import org.springframework.web.client.ResourceAccessException;
import org.springframework.web.client.UnknownHttpStatusCodeException;

@Service
public class EligibilityService {
    private final WebClient restClient;

    public EligibilityService(WebClient.Builder webClientBuilder) {
        this.restClient = webClientBuilder.baseUrl("http://external-api-url").build();
    }

    public ParticipationAccountsResponse getParticipantAccounts(String customerId, String planNumber) {
        try {
            return restClient.get()
                    .uri(uriBuilder -> uriBuilder
                            .path(AppConstants.PARTICIPANT_ACCOUNTS_API)
                            .build(customerId, planNumber))
                    .accept(MediaType.APPLICATION_JSON)
                    .headers(this::setHeaders)
                    .retrieve()
                    .bodyToMono(ParticipationAccountsResponse.class)
                    .block(); // Blocking to simulate non-reactive context
        } catch (HttpServerErrorException | ResourceAccessException e) {
            throw new RetryOnAPIFailureException(AppConstants.PARTICIPATION_ACCOUNTS_EXCEPTION, e);
        } catch (HttpClientErrorException | UnknownHttpStatusCodeException e) {
            throw new ServiceException(AppConstants.PARTICIPATION_ACCOUNTS_EXCEPTION, e);
        }
    }

    private void setHeaders(HttpHeaders headers) {
        headers.set("Authorization", "Bearer some-secure-token");
    }
     @Autowired
    private  EligibilityRepository repository;


    public Eligibility checkEligibility(String customerId, String planNumber) {
        return repository.findByCustomerIdAndPlanNumber(customerId, planNumber);
    }
}
