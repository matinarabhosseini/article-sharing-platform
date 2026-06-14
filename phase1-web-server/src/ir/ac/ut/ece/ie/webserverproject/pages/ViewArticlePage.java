package ir.ac.ut.ece.ie.webserverproject.pages;

import ir.ac.ut.ece.ie.webserverproject.model.Article;
import ir.ac.ut.ece.ie.webserverproject.repository.ArticleRepository;
import ir.ac.ut.ece.ie.webserverproject.server.HttpRequest;
import ir.ac.ut.ece.ie.webserverproject.util.HtmlUtil;

public class ViewArticlePage implements WebPage {

    @Override
    public String render(HttpRequest request) {
        String title = request.getParam("title");

        StringBuilder html = new StringBuilder();
        html.append(HtmlUtil.pageStart("View Article"));

        if (title == null || title.trim().isEmpty()) {
            html.append("<div class='error'>Article title is missing.</div>");
            html.append(HtmlUtil.pageEnd());
            return html.toString();
        }

        Article article = ArticleRepository.findByTitle(title);

        if (article == null) {
            html.append("<div class='error'>Article not found.</div>");
            html.append(HtmlUtil.pageEnd());
            return html.toString();
        }

        html.append("<h1>").append(HtmlUtil.escape(article.getTitle())).append("</h1>");
        html.append("<div class='card'>");
        html.append("<h3>Abstract</h3>");
        html.append("<p>").append(HtmlUtil.escape(article.getAbstractText())).append("</p>");
        html.append("</div>");

        html.append("<div class='card'>");
        html.append("<h3>Body</h3>");
        html.append("<p>").append(HtmlUtil.escape(article.getBody())).append("</p>");
        html.append("</div>");

        html.append("<div class='card'>");
        html.append("<h3>References</h3>");
        if (article.getReferences().isEmpty()) {
            html.append("<p>No references registered for this article.</p>");
        } else {
            html.append("<ul>");
            for (String ref : article.getReferences()) {
                html.append("<li><a href='/article?title=")
                        .append(ref.replace(" ", "%20"))
                        .append("'>")
                        .append(HtmlUtil.escape(ref))
                        .append("</a></li>");
            }
            html.append("</ul>");
        }
        html.append("</div>");

        html.append(HtmlUtil.pageEnd());
        return html.toString();
    }
}