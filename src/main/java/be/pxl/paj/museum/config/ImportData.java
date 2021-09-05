package be.pxl.paj.museum.config;

import be.pxl.paj.museum.domain.Artist;
import be.pxl.paj.museum.domain.Artwork;
import be.pxl.paj.museum.domain.ArtworkType;
import be.pxl.paj.museum.domain.Museum;
import be.pxl.paj.museum.repository.ArtistRepository;
import be.pxl.paj.museum.repository.ArtworkRepository;
import be.pxl.paj.museum.repository.MuseumRepository;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.springframework.boot.CommandLineRunner;
import org.springframework.stereotype.Component;

@Component
public class ImportData implements CommandLineRunner {

	private final Logger LOGGER = LogManager.getLogger(ImportData.class);

	private final ArtistRepository artistRepository;
	private final MuseumRepository museumRepository;
	private final ArtworkRepository artworkRepository;

	public ImportData(ArtistRepository artistRepository, MuseumRepository museumRepository, ArtworkRepository artworkRepository) {
		this.artistRepository = artistRepository;
		this.museumRepository = museumRepository;
		this.artworkRepository = artworkRepository;
	}

	@Override
	public void run(String... args) throws Exception {
		LOGGER.info("Importing testdata...");
		museumRepository.save(new Museum("Museum aan de Stroom", "Antwerpen"));
		Museum rubenshuis = new Museum("Rubenshuis", "Antwerpen");
		museumRepository.save(rubenshuis);
		Museum z33 = new Museum("Z33", "Hasselt");
		museumRepository.save(z33);
		museumRepository.save(new Museum("Het Stadsmus", "Hasselt"));
		Museum m = new Museum("M - Museum", "Leuven");
		museumRepository.save(m);

		//Artist rubens = new Artist("Pieter Paul Rubens", LocalDate.of(1577, 6, 28), LocalDate.of(1640, 5, 30));
		Artist rubens = new Artist("Pieter Paul Rubens");
		artistRepository.save(rubens);
		Artist kamrooz = new Artist("Kamrooz Aram");
		artistRepository.save(kamrooz);
		Artist puyenbroek = new Artist("Pieter Puyenbroek");
		artistRepository.save(puyenbroek);
		artworkRepository.save(new Artwork("Adam en Eva", ArtworkType.PAINTING, rubens, rubenshuis));
		artworkRepository.save(new Artwork("Henry IV in de slag bij Ivery", ArtworkType.PAINTING, rubens, rubenshuis));
		artworkRepository.save(new Artwork("Arabesque Composition", ArtworkType.PAINTING, kamrooz, z33));
		artworkRepository.save(new Artwork("Anna Boleyn", ArtworkType.SCULPTURE, puyenbroek, m));
		artworkRepository.save(new Artwork("Zelfportret", ArtworkType.PAINTING, rubens, m));
	}
}
