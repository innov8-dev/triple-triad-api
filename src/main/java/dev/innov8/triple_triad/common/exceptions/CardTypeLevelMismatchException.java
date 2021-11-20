package dev.innov8.triple_triad.common.exceptions;

public class CardTypeLevelMismatchException extends RuntimeException {
    public CardTypeLevelMismatchException() {
        super("Prevented an operation that would result in a type/level discrepancy.");
    }

    public CardTypeLevelMismatchException(String msg) {
        super(msg);
    }
}
