package be.pxl.paj.museum.repository;

import be.pxl.paj.museum.domain.Museum;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface MuseumRepository extends JpaRepository<Museum, Long> {

}
