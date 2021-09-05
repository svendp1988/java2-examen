package be.pxl.paj.museum.service.impl;

import be.pxl.paj.museum.domain.*;
import be.pxl.paj.museum.exception.BusinessException;
import be.pxl.paj.museum.exception.NotFoundException;
import be.pxl.paj.museum.repository.ArtistRepository;
import be.pxl.paj.museum.repository.ArtworkLoanRepository;
import be.pxl.paj.museum.repository.ArtworkRepository;
import be.pxl.paj.museum.repository.MuseumRepository;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;

import java.util.Optional;

import static be.pxl.paj.museum.domain.builder.ArtworkBuilder.anArtwork;
import static be.pxl.paj.museum.domain.builder.ArtworkLoanBuilder.anArtworkLoan;
import static be.pxl.paj.museum.domain.builder.MuseumBuilder.aMuseum;
import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.ArgumentMatchers.anyLong;
import static org.mockito.Mockito.when;

@ExtendWith(MockitoExtension.class)
class MuseumServiceImplTest {
    private static final Long MUSEUM_ID = 1234L;
    private static final Long ARTWORK_ID = 5678L;

    @Mock
    private ArtworkRepository artworkRepository;
    @Mock
    private ArtistRepository artistRepository;
    @Mock
    private MuseumRepository museumRepository;
    @Mock
    private ArtworkLoanRepository artworkLoanRepository;
    @InjectMocks
    private MuseumServiceImpl museumService;
    private Museum museum;
    private Artist artist;
    private Artwork artwork;
    private ArtworkLoan artworkLoan;

    @BeforeEach
    void setUp() {
        museum = aMuseum()
                .withCity("Paris")
                .withName("Louvre")
                .build();
        artist = new Artist("Rembrandt van Rijn");
        artwork = anArtwork()
                .withArtist(artist)
                .withOwner(museum)
                .withTitle("De Nachtwacht")
                .withType(ArtworkType.PAINTING)
                .build();
        artworkLoan = anArtworkLoan()
                .withArtwork(artwork)
                .withBorrower(museum)
                .build();
    }

    @Test
    void throwsNotFoundExceptionWhenNoMuseumFound() {
        when(museumRepository.findById(anyLong())).thenReturn(Optional.empty());
        NotFoundException actual = assertThrows(NotFoundException.class, () -> museumService.lendArtwork(MUSEUM_ID, ARTWORK_ID));
        assertEquals("No museum found with id[1234]", actual.getMessage());
    }

    @Test
    void throwsNotFoundExceptionWhenNoArtworkFound() {
        when(museumRepository.findById(anyLong())).thenReturn(Optional.of(museum));
        when(artworkRepository.findById(anyLong())).thenReturn(Optional.empty());
        NotFoundException actual = assertThrows(NotFoundException.class, () -> museumService.lendArtwork(MUSEUM_ID, ARTWORK_ID));
        assertEquals("No artwork found with id [5678]", actual.getMessage());
    }

    @Test
    void throwsBusinessExceptionWhenMuseumAlreadyOwnerOfArtwork() {
        when(museumRepository.findById(anyLong())).thenReturn(Optional.of(museum));
        when(artworkRepository.findById(anyLong())).thenReturn(Optional.of(artwork));
        BusinessException actual = assertThrows(BusinessException.class, () -> museumService.lendArtwork(MUSEUM_ID, ARTWORK_ID));
        assertEquals("You're owner of this artwork.", actual.getMessage());
    }
}