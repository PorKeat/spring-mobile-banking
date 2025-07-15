package kh.edu.banking.api.service;

import kh.edu.banking.api.dto.CreateCustomerRequest;
import kh.edu.banking.api.dto.CustomerResponse;
import kh.edu.banking.api.dto.UpdateCustomerRequest;

import java.util.List;

public interface CustomerService {

    void verifyKyc(Integer customerId);

    void disableByPhoneNumber(String phoneNumber);

    void deleteByPhoneNumber(String phoneNumber);

    CustomerResponse createNew(CreateCustomerRequest createCustomerRequest);

    List<CustomerResponse> findAll();

    CustomerResponse findByPhoneNumber(String phoneNumber);

    //If we want to update using HTTP PUT we need to validate the fields
    //If we want to update using HTTP PATCH we dont need to validation the fields
    CustomerResponse updateByPhoneNumber(String phoneNumber, UpdateCustomerRequest updateCustomerRequest);

}
