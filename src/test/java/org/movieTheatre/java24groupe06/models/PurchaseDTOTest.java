package org.movieTheatre.java24groupe06.models;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.movieTheatre.java24groupe06.models.DAO.PurchaseDTO;

import java.util.Arrays;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertThrows;

public class PurchaseDTOTest {
    private Session session;

    @BeforeEach
    public void setUp() {
        int id = 1;
        Movie movie = new Movie.MovieBuilder()
                .setTitle("Titre du film")
                .setDuration(120)
                .setSynopsis("Synopsis du film")
                .setReleaseDate("2022-01-01")
                .setProducer("Producteur du film")
                .setPathImg("Chemin vers l'image du film")
                .setIsShowing(true)
                .setGenres(Arrays.asList("Genre1", "Genre2"))
                .setActors(Arrays.asList("Acteur1", "Acteur2"))
                .setID(1)
                .build();
        SeatsRoomLeft seatsRoomLeft = new SeatsRoomLeft(100, 20, 10);        String date = "2022-01-01";
        session = new Session(id, movie, seatsRoomLeft, date);

    }

    @Test
    public void constructorSetsAllFieldsCorrectly() {
        PurchaseDTO purchaseDto = new PurchaseDTO(session, 2, 1, 1);

        assertEquals(session, purchaseDto.getSession());
        assertEquals(2, purchaseDto.getNbRegularSeatsBuy());
        assertEquals(1, purchaseDto.getNbVIPSeatsBuy());
        assertEquals(1, purchaseDto.getNbHandicapsSeatsBuy());
    }

    @Test
    public void constructorThrowsExceptionWhenSessionIsNull() {
        assertThrows(NullPointerException.class, () -> new PurchaseDTO(null, 2, 1, 1));
    }

    @Test
    public void constructorThrowsExceptionWhenNumberOfSeatsIsNegative() {
        assertThrows(IllegalArgumentException.class, () -> new PurchaseDTO(session, -1, 1, 1));
        assertThrows(IllegalArgumentException.class, () -> new PurchaseDTO(session, 2, -1, 1));
        assertThrows(IllegalArgumentException.class, () -> new PurchaseDTO(session, 2, 1, -1));
    }
}