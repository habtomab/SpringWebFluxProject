package com.eligibility.repository;

import com.eligibility.entity.Eligibility;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface EligibilityRepository extends JpaRepository<Eligibility, Long> {
    Eligibility findByCustomerIdAndPlanNumber(String customerId, String planNumber);
}