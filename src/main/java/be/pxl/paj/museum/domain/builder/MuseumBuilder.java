package be.pxl.paj.museum.domain.builder;

import be.pxl.paj.museum.domain.Museum;

public final class MuseumBuilder {
    private String name;
    private String city;

    private MuseumBuilder() {
    }

    public static MuseumBuilder aMuseum() {
        return new MuseumBuilder();
    }

    public MuseumBuilder withName(String name) {
        this.name = name;
        return this;
    }

    public MuseumBuilder withCity(String city) {
        this.city = city;
        return this;
    }

    public Museum build() {
        return new Museum(name, city);
    }
}
