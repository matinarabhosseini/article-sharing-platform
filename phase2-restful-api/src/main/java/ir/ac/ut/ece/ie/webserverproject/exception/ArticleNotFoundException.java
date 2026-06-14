package ir.ac.ut.ece.ie.webserverproject.exception;

public class ArticleNotFoundException extends RuntimeException {
    public ArticleNotFoundException(String id) {
        super("Article not found: " + id);
    }
}
