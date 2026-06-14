package ir.ac.ut.ece.ie.webserverproject.exception;

public class InvalidReferenceException extends RuntimeException {
    public InvalidReferenceException(String referenceId) {
        super("Reference article does not exist: " + referenceId);
    }
}
