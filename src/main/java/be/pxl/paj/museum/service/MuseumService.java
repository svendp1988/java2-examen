package be.pxl.paj.museum.service;

import be.pxl.paj.museum.api.data.CreateArtworkCommand;
import be.pxl.paj.museum.api.data.MuseumArtworkDTO;
import be.pxl.paj.museum.api.data.MuseumDTO;

import java.util.List;

public interface MuseumService {

	MuseumArtworkDTO findMuseum(Long museumId);

	List<MuseumDTO> getMusea();

	Long createArtwork(Long museumId, CreateArtworkCommand createArtworkCommand);

	void lendArtwork(Long museumId, Long artworkId);

	void returnArtwork(Long museumId, Long artworkId);
}
