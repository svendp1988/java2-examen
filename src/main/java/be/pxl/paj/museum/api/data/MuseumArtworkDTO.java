package be.pxl.paj.museum.api.data;

import be.pxl.paj.museum.domain.Artwork;
import be.pxl.paj.museum.domain.Museum;

import java.util.List;
import java.util.stream.Collectors;

public class MuseumArtworkDTO extends MuseumDTO {

	private final List<ArtworkDTO> ownArtwork;
	private final List<ArtworkDTO> borrowedArtwork;

	public MuseumArtworkDTO(Museum museum, List<Artwork> borrowedArtwork) {
		super(museum);
		this.ownArtwork = museum.getArtworkList().stream().map(ArtworkDTO::new).collect(Collectors.toList());
		this.borrowedArtwork = borrowedArtwork.stream().map(ArtworkDTO::new).collect(Collectors.toList());
	}

	public List<ArtworkDTO> getOwnArtwork() {
		return ownArtwork;
	}

	public List<ArtworkDTO> getBorrowedArtwork() {
		return borrowedArtwork;
	}
}
