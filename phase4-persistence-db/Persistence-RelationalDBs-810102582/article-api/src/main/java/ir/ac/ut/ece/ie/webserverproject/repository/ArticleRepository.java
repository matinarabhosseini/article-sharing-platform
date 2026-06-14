package ir.ac.ut.ece.ie.webserverproject.repository;

import ir.ac.ut.ece.ie.webserverproject.model.Article;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.Optional;

@Repository
public interface ArticleRepository extends JpaRepository<Article, String> {
    Optional<Article> findByTitleIgnoreCase(String title);
}