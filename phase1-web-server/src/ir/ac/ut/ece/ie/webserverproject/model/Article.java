package ir.ac.ut.ece.ie.webserverproject.model;

import java.util.ArrayList;
import java.util.List;

public class Article {
    private String title;
    private String abstractText;
    private String body;
    private List<String> references;

    public Article(String title, String abstractText, String body) {
        this.title = title;
        this.abstractText = abstractText;
        this.body = body;
        this.references = new ArrayList<>();
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

    public List<String> getReferences() {
        return references;
    }

    public void addReference(String title) {
        references.add(title);
    }

    public int getReferenceCount() {
        return references.size();
    }
}