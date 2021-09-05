package be.pxl.paj.museum.service.impl;

import be.pxl.paj.museum.api.data.CreateArtworkCommand;
import be.pxl.paj.museum.api.data.MuseumArtworkDTO;
import be.pxl.paj.museum.api.data.MuseumDTO;
import be.pxl.paj.museum.domain.*;
import be.pxl.paj.museum.exception.BusinessException;
import be.pxl.paj.museum.exception.NotFoundException;
import be.pxl.paj.museum.repository.ArtistRepository;
import be.pxl.paj.museum.repository.ArtworkLoanRepository;
import be.pxl.paj.museum.repository.ArtworkRepository;
import be.pxl.paj.museum.repository.MuseumRepository;
import be.pxl.paj.museum.service.MuseumService;
import org.springframework.stereotype.Service;

import javax.transaction.Transactional;
import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

import static be.pxl.paj.museum.domain.builder.ArtworkBuilder.anArtwork;

@Service
public class MuseumServiceImpl implements MuseumService {

    private final ArtworkRepository artworkRepository;
    private final ArtistRepository artistRepository;
    private final MuseumRepository museumRepository;
    private final ArtworkLoanRepository artworkLoanRepository;

    public MuseumServiceImpl(ArtworkRepository artworkRepository, MuseumRepository museumRepository, ArtworkLoanRepository artworkLoanRepository, ArtistRepository artistRepository) {
        this.artworkRepository = artworkRepository;
        this.museumRepository = museumRepository;
        this.artworkLoanRepository = artworkLoanRepository;
        this.artistRepository = artistRepository;
    }

    @Override
    public List<MuseumDTO> getMusea() {
        return museumRepository.findAll().stream().map(MuseumDTO::new).collect(Collectors.toList());
    }

    @Override
    public Long createArtwork(Long museumId, CreateArtworkCommand createArtworkCommand) {
        Optional<Artist> artistByName = artistRepository.findArtistByName(createArtworkCommand.getArtist());
        Optional<Museum> optionalMuseum = museumRepository.findById(museumId);
        if (optionalMuseum.isEmpty()) {
            throw new NotFoundException("No museum could be found with id [" + museumId + "]");
        }
        Artist artist;
        if (artistByName.isPresent()) {
            artist = artistByName.get();
        } else {
            artist = new Artist(createArtworkCommand.getArtist());
            artistRepository.save(artist);
        }
		Artwork artwork = anArtwork()
				.withArtist(artist)
				.withOwner(optionalMuseum.get())
				.withTitle(createArtworkCommand.getTitle())
				.withType(createArtworkCommand.getArtworkType())
				.build();
		artworkRepository.save(artwork);
		return artwork.getId();
    }

    @Override
    @Transactional
    public MuseumArtworkDTO findMuseum(Long museumId) {
        Museum museum = museumRepository.findById(museumId).orElseThrow(() -> new NotFoundException("No museum found with id [" + museumId + "]"));
        List<ArtworkLoan> loans = artworkLoanRepository.findArtworkLoansByBorrowerAndStatus(museum, ArtworkLoanStatus.ON_LOAN);
        return new MuseumArtworkDTO(museum, loans.stream().map(ArtworkLoan::getArtwork).collect(Collectors.toList()));
    }

    @Override
    @Transactional
    public void lendArtwork(Long museumId, Long artworkId) {
        Museum museum = museumRepository.findById(museumId).orElseThrow(() -> new NotFoundException("No museum found with id[" + museumId + "]"));
        List<Artwork> artworks = artworkRepository.findAll();
        Artwork artwork = artworkRepository.findById(artworkId).orElseThrow(() -> new NotFoundException("No artwork found with id [" + artworkId + "]"));
        if (artwork.getOwner().equals(museum)) {
            throw new BusinessException("You're owner of this artwork.");
        }
        if (artwork.isLent()) {
            throw new BusinessException("This artwork is already on loan.");
        }

        long loansByMuseum = artworkLoanRepository.countArtworkLoansByBorrowerAndStatus(museum, ArtworkLoanStatus.ON_LOAN);
        if (loansByMuseum >= 2) {
            throw new BusinessException("You already have 2 artworks on loan.");
        }
        ArtworkLoan artworkLoan = new ArtworkLoan(artwork, museum);
        artworkLoanRepository.save(artworkLoan);
    }

    @Override
    public void returnArtwork(Long museumId, Long artworkId) {
        Museum museum = museumRepository.findById(museumId).orElseThrow(() -> new NotFoundException("No museum found with id[" + museumId + "]"));
        Artwork artwork = artworkRepository.findById(artworkId).orElseThrow(() -> new NotFoundException("No artwork with id [" + artworkId + "]"));
        Optional<ArtworkLoan> artworkLoan = artworkLoanRepository.findArtworkLoanByArtworkAndBorrowerAndStatus(artwork, museum, ArtworkLoanStatus.ON_LOAN);
        artworkLoan.ifPresentOrElse(ArtworkLoan::returnLoan, () -> {
            throw new BusinessException("Artwork [" + artworkId + "] is currently not borrowed by [" + museum.getName() + "]");
        });
    }
}
