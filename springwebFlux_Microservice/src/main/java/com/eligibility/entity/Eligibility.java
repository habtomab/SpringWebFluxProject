package com.eligibility.entity;

import jakarta.persistence.*;
import lombok.Data;

@Entity
@Table(name = "eligibility")
@Data
public class Eligibility {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(name = "customer_id", nullable = false)
    private String customerId;

    @Column(name = "plan_number", nullable = false)
    private String planNumber;

    @Column(nullable = false)
    private boolean eligible;

    // Getters and Setters
}