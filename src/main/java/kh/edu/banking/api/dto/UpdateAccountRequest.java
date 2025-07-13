package kh.edu.banking.api.dto;

import java.math.BigDecimal;

public record UpdateAccountRequest(
        String accountCurrency,
        BigDecimal balance,
        Integer accountTypeId
) {}