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
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.servlet.support.ServletUriComponentsBuilder;

import java.net.URI;
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
        List<Article> articles;

        if (query != null && !query.isBlank()) {
            articles = articleRepository.search(query.trim());
        } else if ("references".equalsIgnoreCase(sort)) {
            articles = articleRepository.getArticlesSortedByReferenceCount();
        } else {
            articles = articleRepository.getAllArticlesSortedByTime();
        }

        return articles.stream()
                .map(ArticleSummaryResponse::new)
                .toList();
    }

    @GetMapping("/{id}")
    public ArticleDetailResponse getArticle(@PathVariable String id) {
        Article article = articleRepository.findById(id)
                .orElseThrow(() -> new ArticleNotFoundException(id));

        List<ArticleSummaryResponse> references = article.getReferenceIds().stream()
                .map(referenceId -> articleRepository.findById(referenceId)
                        .orElseThrow(() -> new ArticleNotFoundException(referenceId)))
                .map(ArticleSummaryResponse::new)
                .toList();

        return new ArticleDetailResponse(article, references);
    }

    @PostMapping
    public ResponseEntity<ArticleDetailResponse> createArticle(@Valid @RequestBody CreateArticleRequest request) {
        if (articleRepository.findByTitle(request.getTitle().trim()).isPresent()) {
            throw new DuplicateArticleTitleException(request.getTitle());
        }

        Article article = new Article(
                request.getTitle().trim(),
                request.getAbstractText().trim(),
                request.getBody().trim()
        );

        if (request.getReferenceIds() != null) {
            for (String referenceId : request.getReferenceIds()) {
                if (!articleRepository.existsById(referenceId)) {
                    throw new InvalidReferenceException(referenceId);
                }
                article.addReference(referenceId);
            }
        }

        Article savedArticle = articleRepository.addArticle(article);

        URI location = ServletUriComponentsBuilder
                .fromCurrentRequest()
                .path("/{id}")
                .buildAndExpand(savedArticle.getId())
                .toUri();

        ArticleDetailResponse response = new ArticleDetailResponse(savedArticle, List.of());
        return ResponseEntity.status(HttpStatus.CREATED)
                .location(location)
                .body(response);
    }
}
