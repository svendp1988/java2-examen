package be.pxl.paj.museum.domain;

import javax.persistence.*;
import java.util.ArrayList;
import java.util.List;

@Entity
public class Museum {

	@Id
	@GeneratedValue
	private Long id;
	private String name;
	private String city;
	// use a bi-directional relationship. Artwork is owner of this relationship.
	@OneToMany(cascade = CascadeType.ALL)
	private List<Artwork> artworkList = new ArrayList<>();

	public Museum() {
		// JPA only
	}

	public Museum(String name, String city) {
		this.name = name;
		this.city = city;
	}

	public Long getId() {
		return id;
	}

	public String getName() {
		return name;
	}

	public String getCity() {
		return city;
	}

	public List<Artwork> getArtworkList() {
		return artworkList;
	}

	public void addArtwork(Artwork artwork) {
		artworkList.add(artwork);
	}
}
