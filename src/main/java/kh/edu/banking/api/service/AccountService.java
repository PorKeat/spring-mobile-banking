package kh.edu.banking.api.service;

import kh.edu.banking.api.dto.AccountResponse;
import kh.edu.banking.api.dto.CreateAccountRequest;
import kh.edu.banking.api.dto.UpdateAccountRequest;

import java.util.List;

public interface AccountService {
    AccountResponse createNew(CreateAccountRequest createAccountRequest);
    List<AccountResponse> findAll();
    AccountResponse findByAccountNumber(String accountNumber);
    List<AccountResponse> findByCustomerId(Integer customerId);
    AccountResponse update(String accountNumber, UpdateAccountRequest request);
    void delete(String accountNumber);
    AccountResponse disable(String accountNumber);
}
