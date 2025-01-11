package com.eligibility.service;

import com.eligibility.entity.Eligibility;
import com.eligibility.repository.EligibilityRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class EligibilityService {

     @Autowired
    private  EligibilityRepository repository;


    public Eligibility checkEligibility(String customerId, String planNumber) {
        return repository.findByCustomerIdAndPlanNumber(customerId, planNumber);
    }

    public Eligibility addEligibility(Eligibility eligibility) {
        return repository.save(eligibility);
    }

    public void deleteEligibility(Long id) {
        repository.deleteById(id);
    }
}
