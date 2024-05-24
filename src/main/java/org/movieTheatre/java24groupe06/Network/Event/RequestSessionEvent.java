package org.movieTheatre.java24groupe06.Network.Event;

import org.movieTheatre.java24groupe06.models.DAO.CreateSessionDTO;

import java.io.Serializable;
/**
 * The RequestSessionEvent class provides methods for managing the request session event.
 */
public class RequestSessionEvent implements Serializable {
    CreateSessionDTO createSessionDTO;
    public RequestSessionEvent(CreateSessionDTO createSessionDTO){
        this.createSessionDTO = createSessionDTO;
    }
    public CreateSessionDTO getDtoCreateSession() {
        return createSessionDTO;
    }
}
