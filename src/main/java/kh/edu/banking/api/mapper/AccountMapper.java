package kh.edu.banking.api.mapper;


import kh.edu.banking.api.domain.Account;
import kh.edu.banking.api.dto.AccountResponse;
import kh.edu.banking.api.dto.CreateAccountRequest;
import kh.edu.banking.api.dto.UpdateAccountRequest;
import org.mapstruct.*;

@Mapper(componentModel = "spring")
public interface AccountMapper {

    // Model → DTO (flat fields from nested objects)
    @Mapping(target = "accountTypeName", source = "accountType.name")
    @Mapping(target = "customerName", source = "customer.fullName")
    @Mapping(target = "customerEmail", source = "customer.email")
    AccountResponse fromAccount(Account account);

    // DTO → Model (direct fields only, no manual ignores)
    Account toAccount(CreateAccountRequest createAccountRequest);

    // Partial update with null-checking (no manual ignores)
    @BeanMapping(nullValuePropertyMappingStrategy = NullValuePropertyMappingStrategy.IGNORE)
    void toAccountPartially(UpdateAccountRequest updateAccountRequest, @MappingTarget Account account);

}
