package be.pxl.paj.museum.domain.builder;

import be.pxl.paj.museum.domain.Artwork;
import be.pxl.paj.museum.domain.ArtworkLoan;
import be.pxl.paj.museum.domain.Museum;

public final class ArtworkLoanBuilder {
    private Artwork artwork;
    private Museum borrower;

    private ArtworkLoanBuilder() {
    }

    public static ArtworkLoanBuilder anArtworkLoan() {
        return new ArtworkLoanBuilder();
    }

    public ArtworkLoanBuilder withArtwork(Artwork artwork) {
        this.artwork = artwork;
        return this;
    }

    public ArtworkLoanBuilder withBorrower(Museum borrower) {
        this.borrower = borrower;
        return this;
    }

    public ArtworkLoan build() {
        return new ArtworkLoan(artwork, borrower);
    }
}
