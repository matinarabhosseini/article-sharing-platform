import ArticleCard from "./ArticleCard";

function ArticleList({ articles }) {
  if (articles.length === 0) {
    return (
      <div className="empty-state">
        <h3>No articles found</h3>
        <p>Try another search or create the first article.</p>
      </div>
    );
  }

  return (
    <div className="article-list">
      {articles.map((article) => (
        <ArticleCard key={article.id} article={article} />
      ))}
    </div>
  );
}

export default ArticleList;