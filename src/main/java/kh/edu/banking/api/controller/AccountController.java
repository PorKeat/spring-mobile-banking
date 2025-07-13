package kh.edu.banking.api.controller;


import jakarta.validation.Valid;
import kh.edu.banking.api.dto.AccountResponse;
import kh.edu.banking.api.dto.CreateAccountRequest;
import kh.edu.banking.api.dto.UpdateAccountRequest;
import kh.edu.banking.api.service.AccountService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/v1/accounts")
@RequiredArgsConstructor
public class AccountController {

    private final AccountService accountService;

    @PostMapping
    @ResponseStatus(HttpStatus.CREATED)
    public AccountResponse createNewAccount(@Valid @RequestBody CreateAccountRequest createAccountRequest){
        return accountService.createNew(createAccountRequest);
    }


    @GetMapping
    public List<AccountResponse> getAllAccounts() {
        return accountService.findAll();
    }

    @GetMapping("/{accountNumber}")
    public AccountResponse getByAccountNumber(@PathVariable String accountNumber) {
        return accountService.findByAccountNumber(accountNumber);
    }

    @GetMapping("/customer/{customerId}")
    public List<AccountResponse> getAccountsByCustomer(@PathVariable Integer customerId) {
        return accountService.findByCustomerId(customerId);
    }

    @PatchMapping("/{accountNumber}")
    public AccountResponse updateAccount(
            @PathVariable String accountNumber,
            @Valid @RequestBody UpdateAccountRequest request
    ) {
        return accountService.update(accountNumber, request);
    }

    @DeleteMapping("/{accountNumber}")
    @ResponseStatus(HttpStatus.NO_CONTENT)
    public void deleteAccount(@PathVariable String accountNumber) {
        accountService.delete(accountNumber);
    }

    @PatchMapping("/{accountNumber}/disable")
    public AccountResponse disableAccount(@PathVariable String accountNumber) {
        return accountService.disable(accountNumber);
    }
}
