package kh.edu.banking.api.repository;

import kh.edu.banking.api.domain.Account;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;
import java.util.Optional;

public interface AccountRepository extends JpaRepository<Account, Integer> {
    boolean existsByAccountNumber(String accountNumber);
    Optional<Account> findByAccountNumber(String accountNumber);
    List<Account> findAllByCustomerId(Integer customerId);
    void deleteByAccountNumber(String accountNumber);
}
