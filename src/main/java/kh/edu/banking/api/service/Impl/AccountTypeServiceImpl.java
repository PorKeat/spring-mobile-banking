package kh.edu.banking.api.service.Impl;

import kh.edu.banking.api.domain.AccountType;
import kh.edu.banking.api.dto.AccountTypeResponse;
import kh.edu.banking.api.dto.CreateAccountTypeRequest;
import kh.edu.banking.api.mapper.AccountTypeMapper;
import kh.edu.banking.api.repository.AccountTypeRepository;
import kh.edu.banking.api.service.AccountTypeService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Service;
import org.springframework.web.server.ResponseStatusException;

import java.util.List;


@Service
@RequiredArgsConstructor
public class AccountTypeServiceImpl implements AccountTypeService {

    private final AccountTypeRepository accountTypeRepository;
    private final AccountTypeMapper accountTypeMapper;

    @Override
    public AccountTypeResponse createNew(CreateAccountTypeRequest createAccountTypeRequest) {

        if(accountTypeRepository.existsByName(createAccountTypeRequest.name())){
            throw new ResponseStatusException(HttpStatus.CONFLICT,"Account type already exists");
        }

        AccountType accountType = accountTypeMapper.toAccountType(createAccountTypeRequest);
        accountType.setIsDeleted(false);
        accountType = accountTypeRepository.save(accountType);


        return accountTypeMapper.fromAccountType(accountType);
    }

    @Override
    public List<AccountTypeResponse> findAll() {
        return accountTypeRepository.findAll().stream()
                .map(accountTypeMapper::fromAccountType)
                .toList();
    }
}
