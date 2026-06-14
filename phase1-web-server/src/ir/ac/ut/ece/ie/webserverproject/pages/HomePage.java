package ir.ac.ut.ece.ie.webserverproject.pages;

import ir.ac.ut.ece.ie.webserverproject.model.Article;
import ir.ac.ut.ece.ie.webserverproject.repository.ArticleRepository;
import ir.ac.ut.ece.ie.webserverproject.server.HttpRequest;
import ir.ac.ut.ece.ie.webserverproject.util.HtmlUtil;

import java.util.List;

public class HomePage implements WebPage {

    @Override
    public String render(HttpRequest request) {
        String query = request.getParam("query");
        List<Article> articles;

        if (query != null && !query.trim().isEmpty()) {
            articles = ArticleRepository.search(query);
        } else {
            articles = ArticleRepository.getArticlesSortedByReferenceCount();
        }

        StringBuilder html = new StringBuilder();

        html.append(HtmlUtil.pageStart("Academic Article Platform"));
        html.append("<h1>Academic Article Platform</h1>");
        html.append("<p class='muted'>Welcome to the article sharing platform.</p>");

        html.append("<div class='search-box'>");
        html.append("<form method='GET' action='/home'>");
        html.append("<label>Search Articles</label>");
        html.append("<input type='text' name='query' placeholder='Search in title or abstract...' value='")
                .append(HtmlUtil.escape(query == null ? "" : query))
                .append("'>");
        html.append("<button class='btn' type='submit'>Search</button>");
        html.append("</form>");
        html.append("</div>");

       if (articles == null || articles.isEmpty()) {
    html.append("<div class='card' style='text-align:center;'>");

    html.append("<h2 style='color:#243b53;'>No Articles Available</h2>");

    html.append("<p class='muted'>There are currently no articles in the system.</p>");

    html.append("<p class='muted'>You can add a new article using the button below.</p>");

    html.append("<br>");

    html.append("<a class='btn' href='/add'>Add First Article</a>");

    html.append("</div>");
} else {
            for (Article article : articles) {
                html.append("<div class='card'>");

                html.append("<a class='article-link' href='/article?title=")
                        .append(article.getTitle().replace(" ", "%20"))
                        .append("'>")
                        .append(HtmlUtil.escape(article.getTitle()))
                        .append("</a>");

                html.append("<p>")
                        .append(HtmlUtil.escape(article.getAbstractText()))
                        .append("</p>");

                html.append("<p class='muted'>Reference count: ")
                        .append(article.getReferenceCount())
                        .append("</p>");

                html.append("</div>");
            }
        }

        html.append(HtmlUtil.pageEnd());
        return html.toString();
    }
}