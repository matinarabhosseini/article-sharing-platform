package ir.ac.ut.ece.ie.webserverproject.dto;

import jakarta.validation.constraints.NotBlank;

import java.util.List;

public class CreateArticleRequest {
    @NotBlank(message = "title is required")
    private String title;

    @NotBlank(message = "abstractText is required")
    private String abstractText;

    @NotBlank(message = "body is required")
    private String body;

    private List<String> referenceIds;

    public String getTitle() { return title; }
    public void setTitle(String title) { this.title = title; }

    public String getAbstractText() { return abstractText; }
    public void setAbstractText(String abstractText) { this.abstractText = abstractText; }

    public String getBody() { return body; }
    public void setBody(String body) { this.body = body; }

    public List<String> getReferenceIds() { return referenceIds; }
    public void setReferenceIds(List<String> referenceIds) { this.referenceIds = referenceIds; }
}
