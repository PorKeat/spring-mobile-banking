package kh.edu.banking.api.controller;

import jakarta.validation.Valid;
import kh.edu.banking.api.dto.CreateCustomerRequest;
import kh.edu.banking.api.dto.CustomerResponse;
import kh.edu.banking.api.dto.UpdateCustomerRequest;
import kh.edu.banking.api.service.CustomerService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/v1/customers")
@RequiredArgsConstructor
public class CustomerController {

    private final CustomerService customerService;


    @PutMapping("/verify/{customerId}")
    public void verifyKyc(@PathVariable Integer customerId) {
        customerService.verifyKyc(customerId);
    }

    @PutMapping("/disable/{phoneNumber}")
    @ResponseStatus(HttpStatus.NO_CONTENT)
    public void disableByPhoneNumber(@PathVariable String phoneNumber){
        customerService.disableByPhoneNumber(phoneNumber);
    }

    @PostMapping
    @ResponseStatus(HttpStatus.CREATED)
    public CustomerResponse createNew(@Valid @RequestBody CreateCustomerRequest createCustomerRequest){
        return customerService.createNew(createCustomerRequest);
    }

    @GetMapping
    public List<CustomerResponse> findAll() {
        return customerService.findAll();
    }

    @GetMapping("/{phoneNumber}")
    public CustomerResponse findByPhoneNumber(@PathVariable String phoneNumber) {
        return customerService.findByPhoneNumber(phoneNumber);
    }


    @PatchMapping("/{phoneNumber}")
    public CustomerResponse updateByPhoneNumber(@PathVariable String phoneNumber,@RequestBody UpdateCustomerRequest updateCustomerRequest){
        return customerService.updateByPhoneNumber(phoneNumber,updateCustomerRequest);
    }

    @DeleteMapping("/{phoneNumber}")
    @ResponseStatus(HttpStatus.NO_CONTENT)
    public void deleteByPhoneNumber(@PathVariable String phoneNumber){
        customerService.deleteByPhoneNumber(phoneNumber);
    }

}
