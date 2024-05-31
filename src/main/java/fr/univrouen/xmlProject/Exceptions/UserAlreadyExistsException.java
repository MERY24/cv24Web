package fr.univrouen.xmlProject.Exceptions;

import org.springframework.http.HttpStatus;
import org.springframework.web.server.ResponseStatusException;

public class UserAlreadyExistsException extends ResponseStatusException {
    private final String message;

    public UserAlreadyExistsException(String message) {
        super(HttpStatus.CONFLICT, message);
        this.message = message;
    }

    @Override
    public String getMessage() {
        return message;
    }
}
