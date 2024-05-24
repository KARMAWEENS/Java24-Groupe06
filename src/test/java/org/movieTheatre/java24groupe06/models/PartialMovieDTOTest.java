package org.movieTheatre.java24groupe06.models;

import org.junit.jupiter.api.Test;
import org.movieTheatre.java24groupe06.models.DAO.PartialMovieDTO;

import static org.junit.jupiter.api.Assertions.*;
/**
 * Test class for PartialMovieDTO.
 */
class PartialMovieDTOTest {
    /**
     * Tests that the constructor creates a valid PartialMovieDTO object.
     */
    @Test
    void getTitleReturnsCorrectTitle() {
        PartialMovieDTO partialMovieDto = new PartialMovieDTO("Test Title", 120, 1, "Test Synopsis", "2022-01-01", "Test Producer", "Test Path", true);
        assertEquals("Test Title", partialMovieDto.getTitle());
    }

    /**
     * tests that the getDuration method returns the correct duration.
     */
    @Test
    void getDurationReturnsCorrectDuration() {
        PartialMovieDTO partialMovieDto = new PartialMovieDTO("Test Title", 120, 1, "Test Synopsis", "2022-01-01", "Test Producer", "Test Path", true);
        assertEquals(120, partialMovieDto.getDuration());
    }
    /**
     * Tests that the getID method returns the correct ID.
     */
    @Test
    void getIDReturnsCorrectID() {
        PartialMovieDTO partialMovieDto = new PartialMovieDTO("Test Title", 120, 1, "Test Synopsis", "2022-01-01", "Test Producer", "Test Path", true);
        assertEquals(1, partialMovieDto.getID());
    }
    /**
     * Tests that the getSynopsis method returns the correct synopsis.
     */
    @Test
    void getSynopsisReturnsCorrectSynopsis() {
        PartialMovieDTO partialMovieDto = new PartialMovieDTO("Test Title", 120, 1, "Test Synopsis", "2022-01-01", "Test Producer", "Test Path", true);
        assertEquals("Test Synopsis", partialMovieDto.getSynopsis());
    }
    /**
     * Tests that the getReleaseDate method returns the correct release date.
     */
    @Test
    void getReleaseDateReturnsCorrectReleaseDate() {
        PartialMovieDTO partialMovieDto = new PartialMovieDTO("Test Title", 120, 1, "Test Synopsis", "2022-01-01", "Test Producer", "Test Path", true);
        assertEquals("2022-01-01", partialMovieDto.getReleaseDate());
    }
    /**
     * Tests that the getProducer method returns the correct producer.
     */
    @Test
    void getProducerReturnsCorrectProducer() {
        PartialMovieDTO partialMovieDto = new PartialMovieDTO("Test Title", 120, 1, "Test Synopsis", "2022-01-01", "Test Producer", "Test Path", true);
        assertEquals("Test Producer", partialMovieDto.getProducer());
    }
    /**
     * Tests that the getPathImg method returns the correct path to the image.
     */
    @Test
    void getPathImgReturnsCorrectPathImg() {
        PartialMovieDTO partialMovieDto = new PartialMovieDTO("Test Title", 120, 1, "Test Synopsis", "2022-01-01", "Test Producer", "Test Path", true);
        assertEquals("Test Path", partialMovieDto.getPathImg());
    }
    /**
     * Tests that the isShowing method returns the correct isShowing.
     */
    @Test
    void isShowingReturnsCorrectIsShowing() {
        PartialMovieDTO partialMovieDto = new PartialMovieDTO("Test Title", 120, 1, "Test Synopsis", "2022-01-01", "Test Producer", "Test Path", true);
        assertTrue(partialMovieDto.isShowing());
    }
    /**
     * Tests that the constructor throws an exception when the title is null.
     */
    @Test
    void constructorThrowsExceptionWhenTitleIsNull() {
        assertThrows(IllegalArgumentException.class, () -> {
            new PartialMovieDTO(null, 120, 1, "Test Synopsis", "2022-01-01", "Test Producer", "Test Path", true);
        });
    }
    /**
     * Tests that the constructor throws an exception when the duration is negative.
     */
    @Test
    void constructorThrowsExceptionWhenDurationIsNegative() {
        assertThrows(IllegalArgumentException.class, () -> {
            new PartialMovieDTO("Test Title", -1, 1, "Test Synopsis", "2022-01-01", "Test Producer", "Test Path", true);
        });
    }
    /**
     * Tests that the constructor throws an exception when the ID is negative.
     */
    @Test
    void constructorThrowsExceptionWhenIDIsNegative() {
        assertThrows(IllegalArgumentException.class, () -> {
            new PartialMovieDTO("Test Title", 120, -1, "Test Synopsis", "2022-01-01", "Test Producer", "Test Path", true);
        });
    }
}
