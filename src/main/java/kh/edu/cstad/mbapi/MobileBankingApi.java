package kh.edu.cstad.mbapi;

import kh.edu.cstad.mbapi.domain.Customer;
import kh.edu.cstad.mbapi.domain.KYC;
import kh.edu.cstad.mbapi.repository.CustomerRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

@SpringBootApplication
@RequiredArgsConstructor
public class MobileBankingApi implements CommandLineRunner {

    public static void main(String[] args) {
        SpringApplication.run(MobileBankingApi.class, args);
    }

    private final CustomerRepository customerRepository;

    @Override
    public void run(String... args) throws Exception {

//        KYC kyc = new KYC();
//        Customer customer = new Customer();
//
//        kyc.setNationalCardId("99990000");
//        kyc.setIsVerified(false);
//        kyc.setIsDeleted(false);
//        kyc.setCustomer(customer);
//
//        customer.setFullName("CHAN CHHAYA");
//        customer.setGender("M");
//        customer.setEmail("it.chhaya@gmail.com");
//        customer.setPhoneNumber("123456789");
//        customer.setKyc(kyc);
//        customer.setRemark("INSTRUCTOR");
//        customer.setIsDeleted(false);
//
//        customerRepository.save(customer);
    }
}
