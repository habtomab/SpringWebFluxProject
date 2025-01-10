package com.eligibility.dto;



import com.eligibility.entity.Account;

import java.util.List;

public class ParticipationAccountsResponse {
    private List<Account> accounts;

    // Getters and Setters

    public List<Account> getAccounts() {
        return accounts;
    }

    public void setAccounts(List<Account> accounts) {
        this.accounts = accounts;
    }
}

