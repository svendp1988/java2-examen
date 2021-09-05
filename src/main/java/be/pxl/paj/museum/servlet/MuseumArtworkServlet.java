package be.pxl.paj.museum.servlet;

import be.pxl.paj.museum.api.data.ArtworkDTO;
import be.pxl.paj.museum.api.data.MuseumArtworkDTO;
import be.pxl.paj.museum.domain.Museum;
import be.pxl.paj.museum.exception.NotFoundException;
import be.pxl.paj.museum.service.MuseumService;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.io.PrintWriter;
import java.util.List;

@WebServlet("/MuseumArtwork")
public class MuseumArtworkServlet extends HttpServlet {

    private static final Logger LOGGER = LogManager.getLogger(MuseumArtworkServlet.class);
    private final MuseumService museumService;

    public MuseumArtworkServlet(MuseumService museumService) {
        this.museumService = museumService;
    }

    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        LOGGER.info("Initializing MuseumArtWorkServlet");
        resp.setContentType("text/html");
        resp.setCharacterEncoding("UTF-8");
        String museumId = req.getParameter("museumId");
        LOGGER.warn("Retrieving museum with id [" + museumId + "]");
        try {
            MuseumArtworkDTO artworkDTO = museumService.findMuseum(Long.valueOf(museumId));
            try (PrintWriter out = resp.getWriter()) {
                out.println("<html>");
                out.println("<body>");
                displayMuseum(out, artworkDTO);
                out.println("<h2> Own artwork </h2>");
                artworkDTO.getOwnArtwork().forEach(ownArtwork -> displayOwnArtworkDTO(out, ownArtwork));
                out.println("<h2> Borrowed artwork </h2>");
                artworkDTO.getBorrowedArtwork().forEach(borrowedArtwork -> displayOwnArtworkDTO(out, borrowedArtwork));
                out.println("</body>");
                out.println("</html>");
            }
        } catch (NotFoundException exception) {
            PrintWriter writer = resp.getWriter();
            writer.println("<html>");
            writer.println("<body>");

            writer.println("No museum found with id [" + museumId + "]");

            writer.println("</body>");
            writer.println("</html>");
        } finally {
            LOGGER.info("Destroy MuseumArtWorkServlet...");

        }
    }

    private void displayMuseum(PrintWriter writer, MuseumArtworkDTO museum) {
        writer.println("<h1 style=\"text-align:center\">" + museum.getName() + " (" + museum.getCity() + ") </h1><p>");
    }

    private void displayBorrowedArtworkDTO(PrintWriter writer, ArtworkDTO artwork) {
        writer.println("<div>" + artwork.getId() + ": " + artwork.getName() + " [" + artwork.getArtist().getName() + "]" + "</div>");
    }

    private void displayOwnArtworkDTO(PrintWriter writer, ArtworkDTO artwork) {
        writer.println("<div>" + artwork.getId() + ": " + artwork.getName() + " [" + artwork.getArtist().getName() + (artwork.isLent() ? "] (on loan)" : "] ") + "</div>");
    }
}
