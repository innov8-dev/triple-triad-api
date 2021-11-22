package dev.innov8.triple_triad.common.exceptions;

public class ResourceNotFoundException extends RuntimeException {
    public ResourceNotFoundException() {
        super("No resources found with the provided search criteria.");
    }
}
