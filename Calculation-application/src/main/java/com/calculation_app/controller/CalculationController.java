package com.calculation_app.controller;

import com.calculation_app.entity.Calculation;
import com.calculation_app.service.CalculationService;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.responses.ApiResponse;
import io.swagger.v3.oas.annotations.responses.ApiResponses;
import io.swagger.v3.oas.annotations.tags.Tag;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/calculation")
@Tag(name = "Calculation API", description = "APIs for managing calculations")
public class CalculationController {
     @Autowired
    private  CalculationService calculationService;



    @GetMapping("/{customerId}/{planNumber}")
    @Operation(summary = "Create Calculation", description = "Creates a new calculation entry")
    @ApiResponses(value = {
            @ApiResponse(responseCode = "200", description = "Order created successfully"),
            @ApiResponse(responseCode = "400", description = "Bad Request: Invalid order data"),
            @ApiResponse(responseCode = "404", description = "Not Found: Customer_id not found"),
            @ApiResponse(responseCode = "500", description = "Internal Server Error")
    })

    public Calculation calculateFees(
            @PathVariable String customerId, @PathVariable String planNumber) {
        return calculationService.calculateFees(customerId, planNumber);
    }
}

