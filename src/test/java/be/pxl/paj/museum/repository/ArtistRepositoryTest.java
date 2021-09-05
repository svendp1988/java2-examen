package be.pxl.paj.museum.repository;

import be.pxl.paj.museum.domain.Artist;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

import java.util.Optional;

import static org.junit.jupiter.api.Assertions.*;

@SpringBootTest
class ArtistRepositoryTest {
    public static final String ARTIST_NAME = "Vincent Van Gogh";
    @Autowired
    private ArtistRepository artistRepository;
    private Artist artist;

    @BeforeEach
    void setUp() {
        artist = new Artist(ARTIST_NAME);
    }

    @Test
    void findArtistByName() {
        artistRepository.save(artist);
        Optional<Artist> artistByName = artistRepository.findArtistByName(ARTIST_NAME);
        assertTrue(artistByName.isPresent());
        assertEquals(ARTIST_NAME, artistByName.get().getName());
    }
}