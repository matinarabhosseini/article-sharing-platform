import { Link } from "react-router-dom";

function formatDate(dateValue) {
  if (!dateValue) {
    return "Unknown date";
  }

  return new Date(dateValue).toLocaleString();
}

function ArticleCard({ article }) {
  return (
    <article className="article-card">
      <div>
        <h2>
          <Link to={`/articles/${article.id}`}>{article.title}</Link>
        </h2>

        <p className="article-abstract">{article.abstractText}</p>
      </div>

      <div className="article-meta">
        <span>Published: {formatDate(article.createdAt)}</span>
        <span>References: {article.referenceCount}</span>
      </div>
    </article>
  );
}

export default ArticleCard;