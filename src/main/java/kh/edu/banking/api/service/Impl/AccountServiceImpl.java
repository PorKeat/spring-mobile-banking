package kh.edu.banking.api.service.Impl;

import jakarta.persistence.EntityNotFoundException;
import kh.edu.banking.api.domain.Account;
import kh.edu.banking.api.domain.AccountType;
import kh.edu.banking.api.domain.Customer;
import kh.edu.banking.api.dto.AccountResponse;
import kh.edu.banking.api.dto.CreateAccountRequest;
import kh.edu.banking.api.dto.UpdateAccountRequest;
import kh.edu.banking.api.mapper.AccountMapper;
import kh.edu.banking.api.repository.AccountRepository;
import kh.edu.banking.api.repository.AccountTypeRepository;
import kh.edu.banking.api.repository.CustomerRepository;
import kh.edu.banking.api.service.AccountService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Service;
import org.springframework.web.server.ResponseStatusException;

import java.util.List;

@Service
@RequiredArgsConstructor
public class AccountServiceImpl implements AccountService {

    private final AccountRepository accountRepository;
    private final CustomerRepository customerRepository;
    private final AccountTypeRepository accountTypeRepository;
    private final AccountMapper accountMapper;

    @Override
    public AccountResponse createNew(CreateAccountRequest createAccountRequest) {
        if (accountRepository.existsByAccountNumber(createAccountRequest.accountNumber())) {
            throw new ResponseStatusException(HttpStatus.CONFLICT,"Account number already exists");
        }
        Customer customer = customerRepository.findById(createAccountRequest.customerId())
                .orElseThrow(() -> new ResponseStatusException(HttpStatus.NOT_FOUND,"Customer not found"));

        AccountType accountType = accountTypeRepository.findById(createAccountRequest.accountTypeId())
                .orElseThrow(() -> new ResponseStatusException(HttpStatus.NOT_FOUND,"Account type not found"));

        Account account = accountMapper.toAccount(createAccountRequest);
        account.setCustomer(customer);
        account.setAccountType(accountType);
        account.setIsDeleted(false);

        Account saved = accountRepository.save(account);
        return accountMapper.fromAccount(saved);
    }

    @Override
    public List<AccountResponse> findAll() {
        return accountRepository.findAll().stream()
                .map(accountMapper::fromAccount)
                .toList();
    }

    @Override
    public AccountResponse findByAccountNumber(String accountNumber) {
        Account account = accountRepository.findByAccountNumber(accountNumber)
                .orElseThrow(() -> new ResponseStatusException(HttpStatus.NOT_FOUND,"Account not found"));
        return accountMapper.fromAccount(account);
    }

    @Override
    public List<AccountResponse> findByCustomerId(Integer customerId) {
        List<Account> accounts = accountRepository.findAllByCustomerId(customerId);
        if (accounts.isEmpty()) {
            throw new ResponseStatusException(HttpStatus.NOT_FOUND,"No accounts found for customer ID: " + customerId);
        }
        return accounts.stream().map(accountMapper::fromAccount).toList();
    }

    @Override
    public AccountResponse update(String accountNumber, UpdateAccountRequest request) {
        Account account = accountRepository.findByAccountNumber(accountNumber)
                .orElseThrow(() -> new ResponseStatusException(HttpStatus.NOT_FOUND,"Account not found"));

        accountMapper.toAccountPartially(request, account);
        return accountMapper.fromAccount(accountRepository.save(account));
    }

    @Override
    public void delete(String accountNumber) {
        Account account = accountRepository.findByAccountNumber(accountNumber)
                .orElseThrow(() -> new ResponseStatusException(HttpStatus.NOT_FOUND,"Account not found"));
        accountRepository.delete(account);
    }

    @Override
    public AccountResponse disable(String accountNumber) {
        Account account = accountRepository.findByAccountNumber(accountNumber)
                .orElseThrow(() -> new ResponseStatusException(HttpStatus.NOT_FOUND,"Account not found"));
        account.setIsDeleted(true);
        return accountMapper.fromAccount(accountRepository.save(account));
    }

}
