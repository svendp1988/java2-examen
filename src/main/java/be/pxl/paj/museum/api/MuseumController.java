package be.pxl.paj.museum.api;

import be.pxl.paj.museum.api.data.CreateArtworkCommand;
import be.pxl.paj.museum.api.data.MuseumDTO;
import be.pxl.paj.museum.service.MuseumService;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;
import java.util.List;

@RestController
@RequestMapping("museum")
public class MuseumController {

	private final MuseumService museumService;
	private static final Logger LOGGER = LogManager.getLogger(MuseumController.class);
	private static final String ADD_ARTWORK = "/{museumId}/artwork";
	private static final String LEND_ARTWORK = "/{museumId}/artwork/{artworkId}/lend";
	private static final String RETURN_ARTWORK = "/{museumId}/artwork/{artworkId}/return";

	public MuseumController(MuseumService museumService) {
		this.museumService = museumService;
	}

	@GetMapping
	public List<MuseumDTO> getMusea() {
		return museumService.getMusea();
	}

	@PostMapping(ADD_ARTWORK)
	public ResponseEntity<Void> addArtwork(@PathVariable long museumId, @RequestBody @Valid CreateArtworkCommand createArtworkCommand) {
		LOGGER.info("Adding artwork");
		museumService.createArtwork(museumId, createArtworkCommand);
		return new ResponseEntity<>(HttpStatus.CREATED);
	}

	@PutMapping(LEND_ARTWORK)
	public ResponseEntity<Void> lendArtwork(@PathVariable long museumId, @PathVariable long artworkId) {
		LOGGER.info("Lending artwork with id [" + artworkId + "]");
		museumService.lendArtwork(museumId, artworkId);
		return new ResponseEntity<>(HttpStatus.ACCEPTED);
	}

	@PutMapping(RETURN_ARTWORK)
	public ResponseEntity<Void> returnArtwork(@PathVariable long museumId, @PathVariable long artworkId) {
		LOGGER.info("Returning artwork with id [" + artworkId + "]");
		museumService.returnArtwork(museumId, artworkId);
		return new ResponseEntity<>(HttpStatus.ACCEPTED);
	}

}
