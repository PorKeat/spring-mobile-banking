package kh.edu.banking.api.controller;

import kh.edu.banking.api.dto.AccountTypeResponse;
import kh.edu.banking.api.dto.CreateAccountTypeRequest;
import kh.edu.banking.api.service.AccountTypeService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/v1/account-types")
@RequiredArgsConstructor
public class AccountTypeController {

    private final AccountTypeService accountTypeService;

    @GetMapping
    public List<AccountTypeResponse> findAll() {
        return accountTypeService.findAll();
    }

    @PostMapping
    @ResponseStatus(HttpStatus.CREATED)
    public AccountTypeResponse createNew(@RequestBody CreateAccountTypeRequest createAccountTypeRequest){
        return accountTypeService.createNew(createAccountTypeRequest);
    }

}
