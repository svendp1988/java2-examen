package be.pxl.paj.museum.repository;

import be.pxl.paj.museum.domain.Artist;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.Optional;

@Repository
public interface ArtistRepository extends JpaRepository<Artist, Long> {
    public Optional<Artist> findArtistByName(String name);
}
