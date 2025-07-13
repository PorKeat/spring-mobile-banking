package kh.edu.banking.api.dto;

import jakarta.validation.constraints.NotNull;
import kh.edu.banking.api.domain.AccountType;
import kh.edu.banking.api.domain.Customer;

import java.math.BigDecimal;


public record CreateAccountRequest(

        @NotNull(message = "Account number is required")
        String accountNumber,

        @NotNull(message = "Currency is required")
        String accountCurrency,

        @NotNull(message = "Balance is required")
        BigDecimal balance,

        @NotNull(message = "Customer ID is required")
        Integer customerId,

        @NotNull(message = "Account type ID is required")
        Integer accountTypeId

) {}

