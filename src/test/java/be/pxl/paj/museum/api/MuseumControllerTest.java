package be.pxl.paj.museum.api;

import be.pxl.paj.museum.api.data.CreateArtworkCommand;
import be.pxl.paj.museum.domain.ArtworkType;
import be.pxl.paj.museum.service.MuseumService;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.ArgumentCaptor;
import org.mockito.Captor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.http.MediaType;
import org.springframework.test.context.junit.jupiter.SpringExtension;
import org.springframework.test.web.servlet.MockMvc;

import java.time.LocalTime;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.ArgumentMatchers.*;
import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.when;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.post;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.content;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

@ExtendWith(SpringExtension.class)
@WebMvcTest(value = MuseumController.class)
class MuseumControllerTest {
    private static final Long MUSEUM_ID = 1234L;
    private static final Long ARTWORK_ID = 5678L;

    @Autowired
    private MockMvc mockMvc;
    @MockBean
    private MuseumService service;
    @Captor
    private ArgumentCaptor<CreateArtworkCommand> createArtworkCommandArgumentCaptor;

    @Test
    void badRequestWhenArtworkTypeNotGiven() throws Exception {
        mockMvc.perform(post("/museum/1234/artwork")
                .contentType(MediaType.APPLICATION_JSON)
                .content(ResourceFileReader.getAsString("request/artworkrequest_without_artworktype.json")))
                .andExpect(status().isBadRequest());
    }

    @Test
    void createArtwork() throws Exception {
        when(service.createArtwork(anyLong(), any(CreateArtworkCommand.class))).thenReturn(ARTWORK_ID);

        mockMvc.perform(post("/museum/1234/artwork")
                .contentType(MediaType.APPLICATION_JSON)
                .content(ResourceFileReader.getAsString("request/artworkrequest_valid.json")))
                .andExpect(status().isCreated());

        verify(service).createArtwork(eq(MUSEUM_ID), createArtworkCommandArgumentCaptor.capture());

        CreateArtworkCommand actual = createArtworkCommandArgumentCaptor.getValue();

        assertEquals("Rembrandt van Rijn", actual.getArtist());
        assertEquals("De Nachtwacht", actual.getTitle());
        assertEquals(ArtworkType.PAINTING, actual.getArtworkType());
    }
}