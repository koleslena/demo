package ru.koleslena.demo.exception;

/**
 * Created by elenko on 23.06.15.
 */
public class ResourceNotFoundException extends Exception {

    public ResourceNotFoundException() {
    }

    public ResourceNotFoundException(String message) {
        super(message);
    }

    public ResourceNotFoundException(String message, Throwable cause) {
        super(message, cause);
    }

    public ResourceNotFoundException(Throwable cause) {
        super(cause);
    }
}
