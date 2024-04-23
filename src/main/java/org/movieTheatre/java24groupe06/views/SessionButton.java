package org.movieTheatre.java24groupe06.views;

import javafx.scene.control.Button;
import org.movieTheatre.java24groupe06.models.Session;

public class SessionButton extends Button {
    private Session session;

    public SessionButton(Session session) {
        super(session.getHour());
        this.session = session;
    }

    public Session getSession() {
        return session;
    }
}
