package kh.edu.banking.api.repository;

import kh.edu.banking.api.domain.AccountType;
import org.springframework.data.jpa.repository.JpaRepository;

public interface AccountTypeRepository extends JpaRepository<AccountType, Integer> {
}
