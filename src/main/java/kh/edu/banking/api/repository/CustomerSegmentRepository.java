package kh.edu.banking.api.repository;

import kh.edu.banking.api.domain.Customer;
import kh.edu.banking.api.domain.CustomerSegment;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.Optional;

public interface CustomerSegmentRepository extends JpaRepository<CustomerSegment, Integer> {
    Optional<CustomerSegment> findCustomerSegmentById(Integer id);
}
