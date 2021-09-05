package be.pxl.paj.museum.repository;

import be.pxl.paj.museum.domain.Artwork;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface ArtworkRepository extends JpaRepository<Artwork, Long> {

}
