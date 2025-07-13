package kh.edu.banking.api.service;

import kh.edu.banking.api.dto.AccountResponse;
import kh.edu.banking.api.dto.AccountTypeResponse;
import kh.edu.banking.api.dto.CreateAccountTypeRequest;

import java.util.List;

public interface AccountTypeService {
    AccountTypeResponse createNew(CreateAccountTypeRequest createAccountTypeRequest);
    List<AccountTypeResponse> findAll();
}
