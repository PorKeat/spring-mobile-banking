package kh.edu.banking.api;

import kh.edu.banking.api.domain.AccountType;
import kh.edu.banking.api.domain.CustomerSegment;
import kh.edu.banking.api.repository.AccountTypeRepository;
import kh.edu.banking.api.repository.CustomerSegmentRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

import java.util.List;

@SpringBootApplication
@RequiredArgsConstructor
public class MobileBankingApi implements CommandLineRunner{

    private final CustomerSegmentRepository customerSegmentRepository;

    public static void main(String[] args) {
        SpringApplication.run(MobileBankingApi.class, args);
    }

    @Override
    public void run(String... args) throws Exception {
        CustomerSegment gold = new CustomerSegment();
        gold.setName("Gold");

        CustomerSegment silver = new CustomerSegment();
        silver.setName("Silver");

        CustomerSegment regular = new CustomerSegment();
        regular.setName("Regular");

        customerSegmentRepository.saveAll(List.of(gold, silver, regular));
    }
}
