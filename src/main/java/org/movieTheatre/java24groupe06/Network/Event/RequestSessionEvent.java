package org.movieTheatre.java24groupe06.Network.Event;

import org.movieTheatre.java24groupe06.models.DAO.DTOCreateSession;

import java.io.Serializable;

public class RequestSessionEvent implements Serializable {
    DTOCreateSession dtoCreateSession;
    public RequestSessionEvent(DTOCreateSession dtoCreateSession){
        this.dtoCreateSession = dtoCreateSession;
    }
    public DTOCreateSession getDtoCreateSession() {
        return dtoCreateSession;
    }
}
