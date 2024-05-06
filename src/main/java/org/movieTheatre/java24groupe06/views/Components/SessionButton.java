package org.movieTheatre.java24groupe06.views.Components;

import javafx.scene.control.Button;
import org.movieTheatre.java24groupe06.models.Session;

public class SessionButton extends Button {
    private Session session;

    public SessionButton(Session session) {
        super(session.getHours());
        this.session = session;
    }

    public Session getSession() {
        return session;
    }
}
