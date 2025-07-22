package kh.edu.cstad.mbapi.repository;

import kh.edu.cstad.mbapi.domain.KYC;
import org.springframework.data.repository.CrudRepository;

public interface KYCRepository
        extends CrudRepository<KYC, Integer> {

    boolean existsByNationalCardId(String nationalCardId);

}
