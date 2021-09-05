package be.pxl.paj.museum.domain;

import be.pxl.paj.museum.exception.BusinessException;

import javax.persistence.*;
import java.time.LocalDate;

@Entity
public class ArtworkLoan {

	@Id
	@GeneratedValue
	private Long id;
	@OneToOne
	private Artwork artwork;
	@ManyToOne
	private Museum borrower;
	private ArtworkLoanStatus status;
	private LocalDate startDate;
	private LocalDate returnDate;

	public ArtworkLoan() {
		// JPA only
	}

	public ArtworkLoan(Artwork artwork, Museum borrower) {
		this.artwork = artwork;
		this.artwork.addLoan(this);
		this.borrower = borrower;
		status = ArtworkLoanStatus.ON_LOAN;
		startDate = LocalDate.now();
	}

	public Long getId() {
		return id;
	}

	public Artwork getArtwork() {
		return artwork;
	}

	public Museum getBorrower() {
		return borrower;
	}

	public LocalDate getStartDate() {
		return startDate;
	}

	public LocalDate getReturnDate() {
		return returnDate;
	}

	public ArtworkLoanStatus getStatus() {
		return status;
	}

	public void returnLoan() {
		validateCanEndLoan();

		status = ArtworkLoanStatus.RETURNED;
		returnDate = LocalDate.now();
	}

	private void validateCanEndLoan() {
		if (status != ArtworkLoanStatus.ON_LOAN) {
			throw new BusinessException("Only a loan with status [LENT] can be returned.");
		}
	}
}
