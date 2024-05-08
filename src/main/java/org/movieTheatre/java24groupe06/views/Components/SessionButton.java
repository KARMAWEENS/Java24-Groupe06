package org.movieTheatre.java24groupe06.views.Components;

import javafx.scene.control.Button;
import org.movieTheatre.java24groupe06.models.Session;

public class SessionButton extends Button {
    private int sessionID;
    public SessionButton(Session session) {
        super(session.getTime());
        this.sessionID = session.getSessionID();
    }

}
