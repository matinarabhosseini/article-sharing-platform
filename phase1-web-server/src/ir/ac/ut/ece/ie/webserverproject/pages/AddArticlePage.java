package ir.ac.ut.ece.ie.webserverproject.pages;

import ir.ac.ut.ece.ie.webserverproject.model.Article;
import ir.ac.ut.ece.ie.webserverproject.repository.ArticleRepository;
import ir.ac.ut.ece.ie.webserverproject.server.HttpRequest;
import ir.ac.ut.ece.ie.webserverproject.util.HtmlUtil;

public class AddArticlePage implements WebPage {

    @Override
    public String render(HttpRequest request) {
        StringBuilder html = new StringBuilder();
        html.append(HtmlUtil.pageStart("Add Article"));
        html.append("<h1>Add New Article</h1>");

        if ("POST".equalsIgnoreCase(request.getMethod())) {
            String title = request.getParam("title");
            String abstractText = request.getParam("abstract");
            String body = request.getParam("body");

            if (isEmpty(title) || isEmpty(abstractText) || isEmpty(body)) {
                html.append("<div class='error'>All fields are required.</div>");
            } else {
                Article article = new Article(title, abstractText, body);
                boolean added = ArticleRepository.addArticle(article);

                if (added) {
                    html.append("<div class='success'>Article added successfully.</div>");
                } else {
                    html.append("<div class='error'>Article title must be unique.</div>");
                }
            }
        }

        html.append("<form method='POST' action='/add'>");

        html.append("<label>Title</label>");
        html.append("<input type='text' name='title' placeholder='Enter article title'>");

        html.append("<label>Abstract</label>");
        html.append("<textarea name='abstract' rows='5' placeholder='Enter article abstract'></textarea>");

        html.append("<label>Body</label>");
        html.append("<textarea name='body' rows='10' placeholder='Enter article body'></textarea>");

        html.append("<button class='btn' type='submit'>Submit Article</button>");
        html.append("</form>");

        html.append(HtmlUtil.pageEnd());
        return html.toString();
    }

    private boolean isEmpty(String value) {
        return value == null || value.trim().isEmpty();
    }
}