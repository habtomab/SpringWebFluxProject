package com.eligibility.controller;

import com.eligibility.entity.Eligibility;
import com.eligibility.service.EligibilityService;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/eligibility")
public class EligibilityController {

    private final EligibilityService eligibilityService;

    public EligibilityController(EligibilityService eligibilityService) {
        this.eligibilityService = eligibilityService;
    }

    @GetMapping("/{customerId}/{planNumber}")
    public Eligibility checkEligibility(
            @PathVariable String customerId, @PathVariable String planNumber) {
        return eligibilityService.checkEligibility(customerId, planNumber);
    }
}