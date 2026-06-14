import { Link, useParams } from "react-router-dom";
import { useEffect, useState } from "react";

import { getArticleById } from "../api/articles";
import ErrorMessage from "../components/ErrorMessage";
import Loading from "../components/Loading";

function formatDate(dateValue) {
  if (!dateValue) {
    return "Unknown date";
  }

  return new Date(dateValue).toLocaleString();
}

function ArticleDetailsPage() {
  const { id } = useParams();

  const [article, setArticle] = useState(null);
  const [loading, setLoading] = useState(true);
  const [error, setError] = useState("");

  useEffect(() => {
    async function loadArticle() {
      try {
        setLoading(true);
        setError("");

        const data = await getArticleById(id);
        setArticle(data);
      } catch (err) {
        setError(err.message);
      } finally {
        setLoading(false);
      }
    }

    loadArticle();
  }, [id]);

  if (loading) {
    return <Loading message="Loading article..." />;
  }

  if (error) {
    return <ErrorMessage message={error} />;
  }

  if (!article) {
    return <ErrorMessage message="Article not found." />;
  }

  return (
  <section>
    <Link to="/" className="back-link">
      ← Back to Articles
    </Link>

    <article className="article-details">
        <div className="article-details-header">
          <h1>{article.title}</h1>

          <div className="article-meta">
            <span>Published: {formatDate(article.createdAt)}</span>
            <span>References: {article.referenceCount}</span>
          </div>
        </div>

        <div className="article-section">
          <h2>Abstract</h2>
          <p>{article.abstractText}</p>
        </div>

        <div className="article-section">
          <h2>Body</h2>
          <p className="article-body">{article.body}</p>
        </div>

        <div className="article-section">
          <h2>Referenced Articles</h2>

          {article.references && article.references.length > 0 ? (
            <ul className="references-list">
              {article.references.map((reference) => (
                <li key={reference.id}>
                  <Link to={`/articles/${reference.id}`}>
                    {reference.title}
                  </Link>
                </li>
              ))}
            </ul>
          ) : (
            <p className="helper-text">This article has no references.</p>
          )}
        </div>
      </article>
    </section>
  );
}

export default ArticleDetailsPage;