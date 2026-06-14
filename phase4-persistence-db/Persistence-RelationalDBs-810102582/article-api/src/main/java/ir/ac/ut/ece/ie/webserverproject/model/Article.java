package ir.ac.ut.ece.ie.webserverproject.model;

import jakarta.persistence.*;
import java.time.LocalDateTime;
import java.util.HashSet;
import java.util.Set;
import java.util.UUID;

@Entity
@Table(name = "articles")
public class Article {

    @Id
    private String id;

    @Column(nullable = false, unique = true)
    private String title;

    @Column(name = "abstract_text", nullable = false, columnDefinition = "TEXT")
    private String abstractText;

    @Column(nullable = false, columnDefinition = "TEXT")
    private String body;

    @Column(name = "created_at", nullable = false)
    private LocalDateTime createdAt;

    @ManyToMany
    @JoinTable(
            name = "article_references",
            joinColumns = @JoinColumn(name = "article_id"),
            inverseJoinColumns = @JoinColumn(name = "referenced_article_id")
    )
    private Set<Article> references = new HashSet<>();

    @ManyToMany(mappedBy = "references")
    private Set<Article> referencedBy = new HashSet<>();

    protected Article() {
    }

    public Article(String title, String abstractText, String body) {
        this.id = UUID.randomUUID().toString();
        this.title = title;
        this.abstractText = abstractText;
        this.body = body;
        this.createdAt = LocalDateTime.now();
        this.references = new HashSet<>();
        this.referencedBy = new HashSet<>();
    }

    public String getId() {
        return id;
    }

    public String getTitle() {
        return title;
    }

    public String getAbstractText() {
        return abstractText;
    }

    public String getBody() {
        return body;
    }

    public LocalDateTime getCreatedAt() {
        return createdAt;
    }

    public Set<Article> getReferences() {
        return references;
    }

    public Set<Article> getReferencedBy() {
        return referencedBy;
    }

    public int getReferenceCount() {
        return referencedBy.size();
    }

    public void addReference(Article article) {
        if (article != null && !article.getId().equals(this.id)) {
            references.add(article);
        }
    }
}