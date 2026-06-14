package ir.ac.ut.ece.ie.webserverproject.repository;

import ir.ac.ut.ece.ie.webserverproject.model.Article;

import java.util.ArrayList;
import java.util.Collections;
import java.util.Comparator;
import java.util.List;

public class ArticleRepository {
    private static final List<Article> articles = new ArrayList<>();


    public static synchronized List<Article> getAllArticles() {
        return new ArrayList<>(articles);
    }

    public static synchronized List<Article> getArticlesSortedByTime() {
        return new ArrayList<>(articles);
    }

    public static synchronized List<Article> getArticlesSortedByReferenceCount() {
        List<Article> result = new ArrayList<>(articles);
        result.sort(Comparator.comparingInt(Article::getReferenceCount).reversed());
        return result;
    }

    public static synchronized Article findByTitle(String title) {
        for (Article article : articles) {
            if (article.getTitle().equalsIgnoreCase(title)) {
                return article;
            }
        }
        return null;
    }

    public static synchronized boolean addArticle(Article article) {
        if (findByTitle(article.getTitle()) != null) {
            return false;
        }
        articles.add(article);
        return true;
    }

    public static synchronized List<Article> search(String query) {
        List<Article> titleMatches = new ArrayList<>();
        List<Article> abstractMatches = new ArrayList<>();

        String q = query.toLowerCase();

        for (Article article : articles) {
            boolean inTitle = article.getTitle().toLowerCase().contains(q);
            boolean inAbstract = article.getAbstractText().toLowerCase().contains(q);

            if (inTitle) {
                titleMatches.add(article);
            } else if (inAbstract) {
                abstractMatches.add(article);
            }
        }

        List<Article> result = new ArrayList<>();
        result.addAll(titleMatches);
        result.addAll(abstractMatches);
        return result;
    }
}