package ir.ac.ut.ece.ie.webserverproject.dto;

import ir.ac.ut.ece.ie.webserverproject.model.Article;

import java.time.LocalDateTime;
import java.util.List;

public class ArticleDetailResponse {
    private final String id;
    private final String title;
    private final String abstractText;
    private final String body;
    private final int referenceCount;
    private final LocalDateTime createdAt;
    private final List<ArticleSummaryResponse> references;

    public ArticleDetailResponse(Article article, List<ArticleSummaryResponse> references) {
        this.id = article.getId();
        this.title = article.getTitle();
        this.abstractText = article.getAbstractText();
        this.body = article.getBody();
        this.referenceCount = article.getReferenceCount();
        this.createdAt = article.getCreatedAt();
        this.references = references;
    }

    public String getId() { return id; }
    public String getTitle() { return title; }
    public String getAbstractText() { return abstractText; }
    public String getBody() { return body; }
    public int getReferenceCount() { return referenceCount; }
    public LocalDateTime getCreatedAt() { return createdAt; }
    public List<ArticleSummaryResponse> getReferences() { return references; }
}
