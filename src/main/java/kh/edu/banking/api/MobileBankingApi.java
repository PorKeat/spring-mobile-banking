package kh.edu.banking.api;

import kh.edu.banking.api.domain.AccountType;
import kh.edu.banking.api.repository.AccountTypeRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

@SpringBootApplication
@RequiredArgsConstructor
public class MobileBankingApi{

    public static void main(String[] args) {
        SpringApplication.run(MobileBankingApi.class, args);
    }

}
