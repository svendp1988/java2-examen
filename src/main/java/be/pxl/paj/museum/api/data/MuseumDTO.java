package be.pxl.paj.museum.api.data;

import be.pxl.paj.museum.domain.Museum;

public class MuseumDTO {

	private final Long id;
	private final String name;
	private final String city;

	public MuseumDTO(Museum museum) {
		this.id = museum.getId();
		this.name = museum.getName();
		this.city = museum.getCity();
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
}
