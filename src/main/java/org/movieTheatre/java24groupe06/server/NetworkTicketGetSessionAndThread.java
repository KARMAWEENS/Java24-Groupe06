package org.movieTheatre.java24groupe06.server;

import org.movieTheatre.java24groupe06.models.DAO.DTOCreateSession;

import java.io.Serializable;

public class NetworkTicketGetSessionAndThread implements Serializable {
    DTOCreateSession dtoCreateSession;
    public NetworkTicketGetSessionAndThread(DTOCreateSession dtoCreateSession){
        this.dtoCreateSession = dtoCreateSession;
    }
    public DTOCreateSession getDtoCreateSession() {
        return dtoCreateSession;
    }
}
