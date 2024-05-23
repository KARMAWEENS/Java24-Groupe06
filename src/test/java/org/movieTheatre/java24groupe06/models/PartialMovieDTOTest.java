package org.movieTheatre.java24groupe06.models;

import org.junit.jupiter.api.Test;
import org.movieTheatre.java24groupe06.models.DAO.PartialMovieDTO;

import static org.junit.jupiter.api.Assertions.*;

class PartialMovieDTOTest {

    @Test
    void getTitleReturnsCorrectTitle() {
        PartialMovieDTO partialMovieDto = new PartialMovieDTO("Test Title", 120, 1, "Test Synopsis", "2022-01-01", "Test Producer", "Test Path", true);
        assertEquals("Test Title", partialMovieDto.getTitle());
    }

    @Test
    void getDurationReturnsCorrectDuration() {
        PartialMovieDTO partialMovieDto = new PartialMovieDTO("Test Title", 120, 1, "Test Synopsis", "2022-01-01", "Test Producer", "Test Path", true);
        assertEquals(120, partialMovieDto.getDuration());
    }

    @Test
    void getIDReturnsCorrectID() {
        PartialMovieDTO partialMovieDto = new PartialMovieDTO("Test Title", 120, 1, "Test Synopsis", "2022-01-01", "Test Producer", "Test Path", true);
        assertEquals(1, partialMovieDto.getID());
    }

    @Test
    void getSynopsisReturnsCorrectSynopsis() {
        PartialMovieDTO partialMovieDto = new PartialMovieDTO("Test Title", 120, 1, "Test Synopsis", "2022-01-01", "Test Producer", "Test Path", true);
        assertEquals("Test Synopsis", partialMovieDto.getSynopsis());
    }

    @Test
    void getReleaseDateReturnsCorrectReleaseDate() {
        PartialMovieDTO partialMovieDto = new PartialMovieDTO("Test Title", 120, 1, "Test Synopsis", "2022-01-01", "Test Producer", "Test Path", true);
        assertEquals("2022-01-01", partialMovieDto.getReleaseDate());
    }

    @Test
    void getProducerReturnsCorrectProducer() {
        PartialMovieDTO partialMovieDto = new PartialMovieDTO("Test Title", 120, 1, "Test Synopsis", "2022-01-01", "Test Producer", "Test Path", true);
        assertEquals("Test Producer", partialMovieDto.getProducer());
    }

    @Test
    void getPathImgReturnsCorrectPathImg() {
        PartialMovieDTO partialMovieDto = new PartialMovieDTO("Test Title", 120, 1, "Test Synopsis", "2022-01-01", "Test Producer", "Test Path", true);
        assertEquals("Test Path", partialMovieDto.getPathImg());
    }

    @Test
    void isShowingReturnsCorrectIsShowing() {
        PartialMovieDTO partialMovieDto = new PartialMovieDTO("Test Title", 120, 1, "Test Synopsis", "2022-01-01", "Test Producer", "Test Path", true);
        assertTrue(partialMovieDto.isShowing());
    }

    @Test
    void constructorThrowsExceptionWhenTitleIsNull() {
        assertThrows(IllegalArgumentException.class, () -> {
            new PartialMovieDTO(null, 120, 1, "Test Synopsis", "2022-01-01", "Test Producer", "Test Path", true);
        });
    }

    @Test
    void constructorThrowsExceptionWhenDurationIsNegative() {
        assertThrows(IllegalArgumentException.class, () -> {
            new PartialMovieDTO("Test Title", -1, 1, "Test Synopsis", "2022-01-01", "Test Producer", "Test Path", true);
        });
    }

    @Test
    void constructorThrowsExceptionWhenIDIsNegative() {
        assertThrows(IllegalArgumentException.class, () -> {
            new PartialMovieDTO("Test Title", 120, -1, "Test Synopsis", "2022-01-01", "Test Producer", "Test Path", true);
        });
    }
}
