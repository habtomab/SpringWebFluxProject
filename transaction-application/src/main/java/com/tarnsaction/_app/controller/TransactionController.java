package com.tarnsaction._app.controller;

import com.tarnsaction._app.entity.Transaction;
import com.tarnsaction._app.request.TransactionRequest;
import com.tarnsaction._app.service.TransactionService;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.responses.ApiResponse;
import io.swagger.v3.oas.annotations.responses.ApiResponses;
import io.swagger.v3.oas.annotations.tags.Tag;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/transactions")
@Tag(name = "Transactions API", description = "APIs for managing transactions")
public class TransactionController {

    @Autowired
    private  TransactionService transactionService;

    @PostMapping
    @Operation(summary = "Create Transaction", description = "Creates a new Transaction entry")
    @ApiResponses(value = {
            @ApiResponse(responseCode = "200", description = "Order created successfully"),
            @ApiResponse(responseCode = "400", description = "Bad Request: Invalid order data"),
            @ApiResponse(responseCode = "404", description = "Not Found: Customer_id not found"),
            @ApiResponse(responseCode = "500", description = "Internal Server Error")
    })

    public Transaction processTransaction(@RequestBody TransactionRequest request) {
        return transactionService.processTransaction(request);
    }
}

