package kh.edu.banking.api.repository;

import kh.edu.banking.api.domain.KYC;
import org.springframework.data.repository.CrudRepository;

public interface KYCRepository extends CrudRepository<KYC, Integer> {
}
