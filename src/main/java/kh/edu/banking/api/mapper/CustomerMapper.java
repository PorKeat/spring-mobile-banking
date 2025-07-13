package kh.edu.banking.api.mapper;

import kh.edu.banking.api.domain.Customer;
import kh.edu.banking.api.dto.CreateCustomerRequest;
import kh.edu.banking.api.dto.CustomerResponse;
import kh.edu.banking.api.dto.UpdateCustomerRequest;
import org.mapstruct.BeanMapping;
import org.mapstruct.Mapper;
import org.mapstruct.MappingTarget;
import org.mapstruct.NullValuePropertyMappingStrategy;

@Mapper(componentModel = "spring")
public interface CustomerMapper {

    // Partially upadate
    @BeanMapping(nullValuePropertyMappingStrategy = NullValuePropertyMappingStrategy.IGNORE)
    void toCustomerPartially(
            UpdateCustomerRequest updateCustomerRequest,
            @MappingTarget Customer customer
    );


    // DTO -> Model
    // Model -> DTO
    // return type is converted/target data
    // parameter is source data
    CustomerResponse fromCustomer(Customer customer);
    Customer toCustomer(CreateCustomerRequest createCustomerRequest);

}
