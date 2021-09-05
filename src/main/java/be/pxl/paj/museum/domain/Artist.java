package be.pxl.paj.museum.domain;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;

@Entity
public class Artist {

	@Id
	@GeneratedValue
	private Long id;
	private String name;

	public Artist(String name) {
		this.name = name;
	}

	public Artist() {
		// JPA only
	}

	public Long getId() {
		return id;
	}

	public String getName() {
		return name;
	}
}
