package kh.edu.banking.api.service.Impl;

import kh.edu.banking.api.domain.Customer;
import kh.edu.banking.api.domain.CustomerSegment;
import kh.edu.banking.api.domain.KYC;
import kh.edu.banking.api.dto.CreateCustomerRequest;
import kh.edu.banking.api.dto.CustomerResponse;
import kh.edu.banking.api.dto.UpdateCustomerRequest;
import kh.edu.banking.api.mapper.CustomerMapper;
import kh.edu.banking.api.repository.CustomerRepository;
import kh.edu.banking.api.repository.CustomerSegmentRepository;
import kh.edu.banking.api.repository.KYCRepository;
import kh.edu.banking.api.service.CustomerService;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.web.server.ResponseStatusException;

import java.util.ArrayList;
import java.util.List;

@Service
@RequiredArgsConstructor
@Slf4j
public class CustomerServiceImpl implements CustomerService {

    private final CustomerRepository customerRepository;
    private final CustomerSegmentRepository customerSegmentRepository;
    private final CustomerMapper customerMapper;
    private final KYCRepository kycRepository;

    @Transactional
    @Override
    public void disableByPhoneNumber(String phoneNumber) {
        if(!customerRepository.isExistsByPhoneNumber(phoneNumber)){
            throw new ResponseStatusException(HttpStatus.NOT_FOUND,
                    "Customer phone number not found");
        }
        customerRepository.disableByPhoneNumber(phoneNumber);
    }

    public CustomerResponse createNew(CreateCustomerRequest createCustomerRequest) {
        if (customerRepository.existsByEmail(createCustomerRequest.email())) {
            throw new ResponseStatusException(HttpStatus.CONFLICT, "Email already exists");
        }

        if (customerRepository.existsByPhoneNumber(createCustomerRequest.phoneNumber())) {
            throw new ResponseStatusException(HttpStatus.CONFLICT, "Phone number already exists");
        }

        CustomerSegment segment = customerSegmentRepository.findById(createCustomerRequest.segmentId())
                .orElseThrow(() -> new ResponseStatusException(HttpStatus.NOT_FOUND, "Segment not found"));

        Customer customer = customerMapper.toCustomer(createCustomerRequest);
        customer.setSegment(segment);
        customer.setIsDeleted(false);
        customer.setAccounts(new ArrayList<>());

        KYC kyc = new KYC();
        kyc.setNationalCardId(createCustomerRequest.nationalCardId());
        kyc.setIsVerified(false);
        kyc.setIsDeleted(false);
        kyc.setCustomer(customer);
        customer.setKyc(kyc);

        customer = customerRepository.save(customer);
        return customerMapper.fromCustomer(customer);
    }

    @Override
    public void verifyKyc(Integer customerId) {
        Customer customer = customerRepository.findById(customerId)
                .orElseThrow(() -> new ResponseStatusException(HttpStatus.NOT_FOUND, "Customer not found"));

        if (customer.getKyc() == null) {
            throw new ResponseStatusException(HttpStatus.NOT_FOUND, "KYC not found for this customer");
        }

        customer.getKyc().setIsVerified(true);
        kycRepository.save(customer.getKyc());
    }

    @Override
    public List<CustomerResponse> findAll() {
        List<Customer> customers = customerRepository.findAllByIsDeletedFalse();
        return customers.stream()
                .map(customerMapper::fromCustomer)
                .toList();
    }

    @Override
    public CustomerResponse findByPhoneNumber(String phoneNumber) {
        return customerRepository.findByPhoneNumberAndIsDeletedFalse(phoneNumber)
                .map(customerMapper::fromCustomer)
                .orElseThrow(()->new ResponseStatusException(
                        HttpStatus.NOT_FOUND,
                        "Customer phone number not found"
                ));
    }

    @Override
    public CustomerResponse updateByPhoneNumber(String phoneNumber, UpdateCustomerRequest updateCustomerRequest) {

        Customer customer = customerRepository
                .findByPhoneNumber(phoneNumber)
                .orElseThrow(()->
                        new ResponseStatusException(
                                HttpStatus.NOT_FOUND,
                                "Customer phonenumber not found")
                );

        /* If we dont use MapStruct we can use the following code
        if (updateCustomerRequest.fullName() != null){
            customer.setFullName(updateCustomerRequest.fullName());
        } */

        customerMapper.toCustomerPartially(updateCustomerRequest,customer);
        customer = customerRepository.save(customer);

        return customerMapper.fromCustomer(customer);
    }

    @Override
    public void deleteByPhoneNumber(String phoneNumber) {
        Customer customer = customerRepository
                .findByPhoneNumber(phoneNumber)
                .orElseThrow(()->
                        new ResponseStatusException(
                                HttpStatus.NOT_FOUND,
                                "Customer phonenumber not found")
                );
        customerRepository.delete(customer);
    }

}
