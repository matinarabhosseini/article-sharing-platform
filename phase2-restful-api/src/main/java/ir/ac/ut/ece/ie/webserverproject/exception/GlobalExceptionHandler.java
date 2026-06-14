package ir.ac.ut.ece.ie.webserverproject.exception;

import jakarta.servlet.http.HttpServletRequest;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.MethodArgumentNotValidException;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.RestControllerAdvice;

import java.util.List;

@RestControllerAdvice
public class GlobalExceptionHandler {

    @ExceptionHandler(ArticleNotFoundException.class)
    public ResponseEntity<ApiError> handleNotFound(ArticleNotFoundException ex, HttpServletRequest request) {
        return build(HttpStatus.NOT_FOUND, List.of(ex.getMessage()), request.getRequestURI());
    }

    @ExceptionHandler(DuplicateArticleTitleException.class)
    public ResponseEntity<ApiError> handleDuplicateTitle(DuplicateArticleTitleException ex, HttpServletRequest request) {
        return build(HttpStatus.CONFLICT, List.of(ex.getMessage()), request.getRequestURI());
    }

    @ExceptionHandler(InvalidReferenceException.class)
    public ResponseEntity<ApiError> handleInvalidReference(InvalidReferenceException ex, HttpServletRequest request) {
        return build(HttpStatus.BAD_REQUEST, List.of(ex.getMessage()), request.getRequestURI());
    }

    @ExceptionHandler(InvalidSortException.class)
    public ResponseEntity<ApiError> handleInvalidSort(InvalidSortException ex, HttpServletRequest request) {
        return build(HttpStatus.BAD_REQUEST, List.of(ex.getMessage()), request.getRequestURI());
    }

    @ExceptionHandler(MethodArgumentNotValidException.class)
    public ResponseEntity<ApiError> handleValidation(MethodArgumentNotValidException ex, HttpServletRequest request) {
        List<String> messages = ex.getBindingResult().getFieldErrors().stream()
                .map(error -> error.getField() + ": " + error.getDefaultMessage())
                .toList();

        return build(HttpStatus.BAD_REQUEST, messages, request.getRequestURI());
    }

    private ResponseEntity<ApiError> build(HttpStatus status, List<String> messages, String path) {
        return ResponseEntity.status(status)
                .body(new ApiError(status.value(), status.getReasonPhrase(), messages, path));
    }
}