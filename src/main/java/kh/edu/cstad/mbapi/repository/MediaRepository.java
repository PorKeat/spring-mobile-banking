package kh.edu.cstad.mbapi.repository;

import kh.edu.cstad.mbapi.domain.Media;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.repository.CrudRepository;

import java.util.Optional;

public interface MediaRepository extends JpaRepository<Media, Integer> {

    Optional<Media> findByNameAndExtension(String name, String extension);

}
