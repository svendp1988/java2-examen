package be.pxl.paj.museum.api.data;

import be.pxl.paj.museum.domain.ArtworkType;

import javax.validation.constraints.NotEmpty;
import javax.validation.constraints.NotNull;

public class CreateArtworkCommand {

	@NotEmpty
	private final String title;
	@NotEmpty
	private final String artist;
	@NotNull
	private final ArtworkType artworkType;

	public CreateArtworkCommand(@NotEmpty String title, @NotEmpty String artist, @NotNull ArtworkType artworkType) {
		this.title = title;
		this.artist = artist;
		this.artworkType = artworkType;
	}

	public String getTitle() {
		return title;
	}

	public String getArtist() {
		return artist;
	}

	public ArtworkType getArtworkType() {
		return artworkType;
	}
}
