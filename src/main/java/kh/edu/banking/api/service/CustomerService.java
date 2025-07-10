package kh.edu.banking.api.service;

import kh.edu.banking.api.dto.CreateCustomerRequest;
import kh.edu.banking.api.dto.CustomerResponse;

import java.util.List;

public interface CustomerService {
    CustomerResponse createNew(CreateCustomerRequest createCustomerRequest);
    List<CustomerResponse> findAll();
    CustomerResponse findByPhoneNumber(String phoneNumber);
}
