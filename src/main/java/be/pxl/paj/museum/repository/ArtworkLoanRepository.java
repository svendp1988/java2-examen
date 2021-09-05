package be.pxl.paj.museum.repository;

import be.pxl.paj.museum.domain.Artwork;
import be.pxl.paj.museum.domain.ArtworkLoan;
import be.pxl.paj.museum.domain.ArtworkLoanStatus;
import be.pxl.paj.museum.domain.Museum;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;
import java.util.Optional;

public interface ArtworkLoanRepository extends JpaRepository<ArtworkLoan, Long> {

	long countArtworkLoansByBorrowerAndStatus(Museum museum, ArtworkLoanStatus status);

	Optional<ArtworkLoan> findArtworkLoanByArtworkAndBorrowerAndStatus(Artwork artwork, Museum borrower, ArtworkLoanStatus status);

	List<ArtworkLoan> findArtworkLoansByBorrowerAndStatus(Museum museum, ArtworkLoanStatus status);
}
