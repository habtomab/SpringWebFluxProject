package com.eligibility.controller;

import com.eligibility.entity.Eligibility;
import com.eligibility.service.EligibilityService;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.responses.ApiResponse;
import io.swagger.v3.oas.annotations.responses.ApiResponses;
import io.swagger.v3.oas.annotations.tags.Tag;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/eligibility")
@Tag(name = "Eligibility API", description = "APIs for managing eligibility")
public class EligibilityController {

    private final EligibilityService eligibilityService;

    public EligibilityController(EligibilityService eligibilityService) {
        this.eligibilityService = eligibilityService;
    }

    @GetMapping("/{customerId}/{planNumber}")
    @Operation(summary = "Create Eligibility", description = "Creates a new Eligibility entry")
    @ApiResponses(value = {
            @ApiResponse(responseCode = "200", description = "Order created successfully"),
            @ApiResponse(responseCode = "400", description = "Bad Request: Invalid order data"),
            @ApiResponse(responseCode = "404", description = "Not Found: Customer_id not found"),
            @ApiResponse(responseCode = "500", description = "Internal Server Error")
    })

    public Eligibility checkEligibility(
            @PathVariable String customerId, @PathVariable String planNumber) {
        return eligibilityService.checkEligibility(customerId, planNumber);
    }
    @PostMapping("/insert")
    public ResponseEntity<Eligibility> addEligibility(@RequestBody Eligibility eligibility) {
        return ResponseEntity.ok(eligibilityService.addEligibility(eligibility));
    }

    @DeleteMapping("/{id}")
    @Operation(summary = "Create Calculation", description = "Delete a  calculation entry")
    @ApiResponses(value = {
            @ApiResponse(responseCode = "200", description = "Order created successfully"),
            @ApiResponse(responseCode = "400", description = "Bad Request: Invalid order data"),
            @ApiResponse(responseCode = "404", description = "Not Found: Id not found"),
            @ApiResponse(responseCode = "500", description = "Internal Server Error")
    })

    public ResponseEntity<String> deleteEligibility(@PathVariable Long id) {
        eligibilityService.deleteEligibility(id);
        return ResponseEntity.ok("Eligibility record deleted successfully.");
    }
}