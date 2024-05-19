package org.movieTheatre.java24groupe06.models;

import org.junit.jupiter.api.Test;
import org.movieTheatre.java24groupe06.models.DAO.DTO;

import static org.junit.jupiter.api.Assertions.*;

class DTOTest {

    @Test
    void getTitleReturnsCorrectTitle() {
        DTO dto = new DTO("Test Title", 120, 1, "Test Synopsis", "2022-01-01", "Test Producer", "Test Path", true);
        assertEquals("Test Title", dto.getTitle());
    }

    @Test
    void getDurationReturnsCorrectDuration() {
        DTO dto = new DTO("Test Title", 120, 1, "Test Synopsis", "2022-01-01", "Test Producer", "Test Path", true);
        assertEquals(120, dto.getDuration());
    }

    @Test
    void getIDReturnsCorrectID() {
        DTO dto = new DTO("Test Title", 120, 1, "Test Synopsis", "2022-01-01", "Test Producer", "Test Path", true);
        assertEquals(1, dto.getID());
    }

    @Test
    void getSynopsisReturnsCorrectSynopsis() {
        DTO dto = new DTO("Test Title", 120, 1, "Test Synopsis", "2022-01-01", "Test Producer", "Test Path", true);
        assertEquals("Test Synopsis", dto.getSynopsis());
    }

    @Test
    void getReleaseDateReturnsCorrectReleaseDate() {
        DTO dto = new DTO("Test Title", 120, 1, "Test Synopsis", "2022-01-01", "Test Producer", "Test Path", true);
        assertEquals("2022-01-01", dto.getReleaseDate());
    }

    @Test
    void getProducerReturnsCorrectProducer() {
        DTO dto = new DTO("Test Title", 120, 1, "Test Synopsis", "2022-01-01", "Test Producer", "Test Path", true);
        assertEquals("Test Producer", dto.getProducer());
    }

    @Test
    void getPathImgReturnsCorrectPathImg() {
        DTO dto = new DTO("Test Title", 120, 1, "Test Synopsis", "2022-01-01", "Test Producer", "Test Path", true);
        assertEquals("Test Path", dto.getPathImg());
    }

    @Test
    void isShowingReturnsCorrectIsShowing() {
        DTO dto = new DTO("Test Title", 120, 1, "Test Synopsis", "2022-01-01", "Test Producer", "Test Path", true);
        assertTrue(dto.isShowing());
    }

    @Test
    void constructorThrowsExceptionWhenTitleIsNull() {
        assertThrows(IllegalArgumentException.class, () -> {
            new DTO(null, 120, 1, "Test Synopsis", "2022-01-01", "Test Producer", "Test Path", true);
        });
    }

    @Test
    void constructorThrowsExceptionWhenDurationIsNegative() {
        assertThrows(IllegalArgumentException.class, () -> {
            new DTO("Test Title", -1, 1, "Test Synopsis", "2022-01-01", "Test Producer", "Test Path", true);
        });
    }

    @Test
    void constructorThrowsExceptionWhenIDIsNegative() {
        assertThrows(IllegalArgumentException.class, () -> {
            new DTO("Test Title", 120, -1, "Test Synopsis", "2022-01-01", "Test Producer", "Test Path", true);
        });
    }
}
