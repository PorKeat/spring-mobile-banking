package kh.edu.banking.api.mapper;

import kh.edu.banking.api.domain.AccountType;
import kh.edu.banking.api.dto.AccountTypeResponse;
import kh.edu.banking.api.dto.CreateAccountTypeRequest;
import org.mapstruct.Mapper;

@Mapper(componentModel = "spring")
public interface AccountTypeMapper {
    AccountTypeResponse fromAccountType(AccountType accountType);
    AccountType toAccountType(CreateAccountTypeRequest createAccountTypeRequest);
}
