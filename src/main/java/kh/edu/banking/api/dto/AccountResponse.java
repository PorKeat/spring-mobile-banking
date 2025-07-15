package kh.edu.banking.api.dto;

import kh.edu.banking.api.domain.AccountType;
import kh.edu.banking.api.domain.Customer;
import lombok.Builder;

import java.math.BigDecimal;

@Builder
public record AccountResponse(
        String accountNumber,
        String accountCurrency,
        BigDecimal balance,
        String accountTypeName,
        String customerName,
        String customerEmail,
        Boolean isDeleted
) {}