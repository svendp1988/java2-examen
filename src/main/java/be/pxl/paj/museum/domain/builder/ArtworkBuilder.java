package be.pxl.paj.museum.domain.builder;

import be.pxl.paj.museum.domain.Artist;
import be.pxl.paj.museum.domain.Artwork;
import be.pxl.paj.museum.domain.ArtworkType;
import be.pxl.paj.museum.domain.Museum;

public final class ArtworkBuilder {
    private String title;
    private ArtworkType type;
    private Artist artist;
    private Museum owner;

    private ArtworkBuilder() {
    }

    public static ArtworkBuilder anArtwork() {
        return new ArtworkBuilder();
    }

    public ArtworkBuilder withTitle(String title) {
        this.title = title;
        return this;
    }

    public ArtworkBuilder withType(ArtworkType type) {
        this.type = type;
        return this;
    }

    public ArtworkBuilder withArtist(Artist artist) {
        this.artist = artist;
        return this;
    }

    public ArtworkBuilder withOwner(Museum owner) {
        this.owner = owner;
        return this;
    }

    public Artwork build() {
        return new Artwork(title, type, artist, owner);
    }
}
