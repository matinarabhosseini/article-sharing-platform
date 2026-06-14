package ir.ac.ut.ece.ie.webserverproject.dto;

import ir.ac.ut.ece.ie.webserverproject.model.Article;

import java.time.LocalDateTime;

public class ArticleSummaryResponse {
    private final String id;
    private final String title;
    private final String abstractText;
    private final int referenceCount;
    private final LocalDateTime createdAt;

    public ArticleSummaryResponse(Article article) {
        this.id = article.getId();
        this.title = article.getTitle();
        this.abstractText = article.getAbstractText();
        this.referenceCount = article.getReferenceCount();
        this.createdAt = article.getCreatedAt();
    }

    public String getId() { return id; }
    public String getTitle() { return title; }
    public String getAbstractText() { return abstractText; }
    public int getReferenceCount() { return referenceCount; }
    public LocalDateTime getCreatedAt() { return createdAt; }
}
