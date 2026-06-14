package ir.ac.ut.ece.ie.webserverproject.exception;

public class DuplicateArticleTitleException extends RuntimeException {
    public DuplicateArticleTitleException(String title) {
        super("Article title must be unique: " + title);
    }
}
