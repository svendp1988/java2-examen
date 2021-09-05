package be.pxl.paj.museum.api.data;

import be.pxl.paj.museum.domain.Artist;

public class ArtistDTO {
	private final Long id;
	private final String name;

	public ArtistDTO(Artist artist) {
		this.id = artist.getId();
		this.name = artist.getName();
	}

	public Long getId() {
		return id;
	}

	public String getName() {
		return name;
	}
}
