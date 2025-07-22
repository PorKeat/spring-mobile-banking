package kh.edu.cstad.mbapi.service;

import kh.edu.cstad.mbapi.dto.AccountResponse;
import kh.edu.cstad.mbapi.dto.CreateAccountRequest;

import java.util.List;

public interface AccountService {

    AccountResponse createNew(CreateAccountRequest createAccountRequest);
    List<AccountResponse> findAll();

}
