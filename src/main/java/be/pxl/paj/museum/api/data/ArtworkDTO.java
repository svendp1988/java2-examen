package be.pxl.paj.museum.api.data;

import be.pxl.paj.museum.domain.Artwork;
import be.pxl.paj.museum.domain.ArtworkType;

public class ArtworkDTO {

	private final Long id;
	private final String name;
	private final ArtistDTO artist;
	private final ArtworkType type;
	private final boolean lent;

	public ArtworkDTO(Artwork artwork) {
		this.id = artwork.getId();
		this.name = artwork.getTitle();
		this.artist = new ArtistDTO(artwork.getArtist());
		this.type = artwork.getType();
		this.lent = artwork.isLent();
	}

	public Long getId() {
		return id;
	}

	public String getName() {
		return name;
	}

	public ArtistDTO getArtist() {
		return artist;
	}

	public ArtworkType getType() {
		return type;
	}

	public boolean isLent() {
		return lent;
	}
}
