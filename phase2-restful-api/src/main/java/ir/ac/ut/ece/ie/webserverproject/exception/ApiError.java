package ir.ac.ut.ece.ie.webserverproject.exception;

import java.time.LocalDateTime;
import java.util.List;

public class ApiError {
    private final LocalDateTime timestamp;
    private final int status;
    private final String error;
    private final List<String> messages;
    private final String path;

    public ApiError(int status, String error, List<String> messages, String path) {
        this.timestamp = LocalDateTime.now();
        this.status = status;
        this.error = error;
        this.messages = messages;
        this.path = path;
    }

    public LocalDateTime getTimestamp() { return timestamp; }
    public int getStatus() { return status; }
    public String getError() { return error; }
    public List<String> getMessages() { return messages; }
    public String getPath() { return path; }
}
