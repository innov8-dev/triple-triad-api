package dev.innov8.triple_triad.common.exceptions;

public class ResourcePersistenceException extends RuntimeException{

    public ResourcePersistenceException() {
        super("An error occurred while persisting the resource.");
    }

    public ResourcePersistenceException(String message) {
        super(message);
    }

}
