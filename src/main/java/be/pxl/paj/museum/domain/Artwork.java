package be.pxl.paj.museum.domain;

import javax.persistence.*;
import java.util.ArrayList;
import java.util.List;

@Entity
public class Artwork {

	@Id
	@GeneratedValue
	private Long id;
	private String title;
	private ArtworkType type;
	@OneToOne
	private Artist artist;
	@OneToOne
	private Museum owner;
	// use a bi-directional relationship. ArtworkLoan is owner of this relationship.
	@OneToMany(cascade = CascadeType.ALL)
	private List<ArtworkLoan> loans = new ArrayList<>();


	public Artwork() {
		// JPA only
	}

	public Artwork(String title, ArtworkType type, Artist artist, Museum owner) {
		this.title = title;
		this.type = type;
		this.artist = artist;
		setOwner(owner);
	}

	public Long getId() {
		return id;
	}

	public String getTitle() {
		return title;
	}

	public Artist getArtist() {
		return artist;
	}

	public ArtworkType getType() {
		return type;
	}

	public Museum getOwner() {
		return owner;
	}

	public void setOwner(Museum owner) {
		this.owner = owner;
		owner.addArtwork(this);
	}

	public boolean isLent() {
		return loans.stream()
				.anyMatch(loan -> loan.getStatus() == ArtworkLoanStatus.ON_LOAN);
	}

	public void addLoan(ArtworkLoan loan) {
		loans.add(loan);
	}
}
