package com.calculation_app.repository;
import com.calculation_app.entity.Calculation;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface CalculationRepository extends JpaRepository<Calculation, Long> {
    Calculation findByCustomerIdAndPlanNumber(String customerId, String planNumber);
}

