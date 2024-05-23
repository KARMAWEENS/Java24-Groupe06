package org.movieTheatre.java24groupe06.controllers.exceptions;

public class CustomExceptions extends Exception{

    private final ErrorCode errorCode;

    public CustomExceptions(String message, Throwable cause, ErrorCode errorCode) {
        super(message, cause);
        this.errorCode = errorCode;
    }

    public ErrorCode getErrorCode() {
        return errorCode;
    }

    public enum ErrorCode {
        MOVIE_DETAIL_ERROR,
        TICKET_CREATION_ERROR,
        SESSION_LIST_ERROR,
        INITIALIZE_TICKETS_ERROR,
        BUY_TICKET_ERROR,
        WELCOME_PAGE_ERROR,
        SESSION_CREATION_ERROR
    }

    public static class ReadTicketException extends RuntimeException {
        public ReadTicketException(String message, Throwable cause) {
            super(message, cause);
        }
    }
}
