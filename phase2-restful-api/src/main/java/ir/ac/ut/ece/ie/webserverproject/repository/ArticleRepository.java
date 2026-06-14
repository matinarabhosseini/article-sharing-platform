package ir.ac.ut.ece.ie.webserverproject.repository;

import ir.ac.ut.ece.ie.webserverproject.model.Article;
import org.springframework.stereotype.Repository;

import java.util.ArrayList;
import java.util.Comparator;
import java.util.List;
import java.util.Optional;

@Repository
public class ArticleRepository {
    private final List<Article> articles = new ArrayList<>();

    public synchronized List<Article> getAllArticlesSortedByTime() {
        return articles.stream()
                .sorted(Comparator.comparing(Article::getCreatedAt).reversed())
                .toList();
    }

    public synchronized List<Article> getArticlesSortedByReferenceCount() {
        return articles.stream()
                .sorted(Comparator.comparingInt(Article::getReferenceCount).reversed())
                .toList();
    }

    public synchronized Optional<Article> findById(String id) {
        return articles.stream()
                .filter(article -> article.getId().equals(id))
                .findFirst();
    }

    public synchronized Optional<Article> findByTitle(String title) {
        return articles.stream()
                .filter(article -> article.getTitle().equalsIgnoreCase(title))
                .findFirst();
    }

    public synchronized boolean existsById(String id) {
        return findById(id).isPresent();
    }

    public synchronized Article addArticle(Article article) {
        articles.add(article);
        return article;
    }

    public synchronized List<Article> search(String query) {
        String q = query.toLowerCase();
        List<Article> titleMatches = new ArrayList<>();
        List<Article> abstractMatches = new ArrayList<>();

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
