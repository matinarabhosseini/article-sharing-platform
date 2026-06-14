package ir.ac.ut.ece.ie.webserverproject.exception;

public class InvalidSortException extends RuntimeException {
    public InvalidSortException(String sort) {
        super("Invalid sort value: " + sort + ". Allowed values are: time, references");
    }
}
