package ir.ac.ut.ece.ie.webserverproject.controller;

import ir.ac.ut.ece.ie.webserverproject.dto.ArticleDetailResponse;
import ir.ac.ut.ece.ie.webserverproject.dto.ArticleSummaryResponse;
import ir.ac.ut.ece.ie.webserverproject.dto.CreateArticleRequest;
import ir.ac.ut.ece.ie.webserverproject.exception.ArticleNotFoundException;
import ir.ac.ut.ece.ie.webserverproject.exception.DuplicateArticleTitleException;
import ir.ac.ut.ece.ie.webserverproject.exception.InvalidReferenceException;
import ir.ac.ut.ece.ie.webserverproject.model.Article;
import ir.ac.ut.ece.ie.webserverproject.repository.ArticleRepository;
import jakarta.validation.Valid;
import org.springframework.data.domain.Sort;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.support.ServletUriComponentsBuilder;

import java.net.URI;
import java.util.Comparator;
import java.util.List;

@RestController
@RequestMapping("/api/articles")
public class ArticleController {
    private final ArticleRepository articleRepository;

    public ArticleController(ArticleRepository articleRepository) {
        this.articleRepository = articleRepository;
    }

    @GetMapping
    public List<ArticleSummaryResponse> getArticles(
            @RequestParam(required = false) String query,
            @RequestParam(defaultValue = "time") String sort
    ) {
        List<Article> articles = articleRepository.findAll(Sort.by(Sort.Direction.DESC, "createdAt"));

        if (query != null && !query.isBlank()) {
            String q = query.trim().toLowerCase();

            List<Article> titleMatches = articles.stream()
                    .filter(article -> article.getTitle().toLowerCase().contains(q))
                    .toList();

            List<Article> abstractMatches = articles.stream()
                    .filter(article ->
                            !article.getTitle().toLowerCase().contains(q)
                                    && article.getAbstractText().toLowerCase().contains(q))
                    .toList();

            articles = new java.util.ArrayList<>();
            articles.addAll(titleMatches);
            articles.addAll(abstractMatches);
        } else if ("references".equalsIgnoreCase(sort)) {
            articles = articles.stream()
                    .sorted(Comparator.comparingInt(Article::getReferenceCount).reversed())
                    .toList();
        }

        return articles.stream()
                .map(ArticleSummaryResponse::new)
                .toList();
    }

    @GetMapping("/{id}")
    public ArticleDetailResponse getArticle(@PathVariable String id) {
        Article article = articleRepository.findById(id)
                .orElseThrow(() -> new ArticleNotFoundException(id));

        List<ArticleSummaryResponse> references = article.getReferences().stream()
                .map(ArticleSummaryResponse::new)
                .toList();

        return new ArticleDetailResponse(article, references);
    }

    @PostMapping
    public ResponseEntity<ArticleDetailResponse> createArticle(@Valid @RequestBody CreateArticleRequest request) {
        if (articleRepository.findByTitleIgnoreCase(request.getTitle().trim()).isPresent()) {
            throw new DuplicateArticleTitleException(request.getTitle());
        }

        Article article = new Article(
                request.getTitle().trim(),
                request.getAbstractText().trim(),
                request.getBody().trim()
        );

        if (request.getReferenceIds() != null) {
            for (String referenceId : request.getReferenceIds()) {
                Article referenceArticle = articleRepository.findById(referenceId)
                        .orElseThrow(() -> new InvalidReferenceException(referenceId));

                article.addReference(referenceArticle);
            }
        }

        Article savedArticle = articleRepository.save(article);

        List<ArticleSummaryResponse> references = savedArticle.getReferences().stream()
                .map(ArticleSummaryResponse::new)
                .toList();

        URI location = ServletUriComponentsBuilder
                .fromCurrentRequest()
                .path("/{id}")
                .buildAndExpand(savedArticle.getId())
                .toUri();

        ArticleDetailResponse response = new ArticleDetailResponse(savedArticle, references);

        return ResponseEntity.status(HttpStatus.CREATED)
                .location(location)
                .body(response);
    }
}