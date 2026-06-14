package ir.ac.ut.ece.ie.webserverproject.model;

import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;
import java.util.UUID;

public class Article {
    private final String id;
    private String title;
    private String abstractText;
    private String body;
    private final LocalDateTime createdAt;
    private final List<String> referenceIds;

    public Article(String title, String abstractText, String body) {
        this.id = UUID.randomUUID().toString();
        this.title = title;
        this.abstractText = abstractText;
        this.body = body;
        this.createdAt = LocalDateTime.now();
        this.referenceIds = new ArrayList<>();
    }

    public String getId() { return id; }
    public String getTitle() { return title; }
    public String getAbstractText() { return abstractText; }
    public String getBody() { return body; }
    public LocalDateTime getCreatedAt() { return createdAt; }
    public List<String> getReferenceIds() { return new ArrayList<>(referenceIds); }
    public int getReferenceCount() { return referenceIds.size(); }

    public void addReference(String articleId) {
        if (articleId != null && !articleId.isBlank() && !referenceIds.contains(articleId)) {
            referenceIds.add(articleId);
        }
    }
}
