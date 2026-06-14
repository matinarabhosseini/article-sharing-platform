function ReferencesSelector({ articles, selectedIds, onChange }) {
  function handleToggle(articleId) {
    if (selectedIds.includes(articleId)) {
      onChange(selectedIds.filter((id) => id !== articleId));
    } else {
      onChange([...selectedIds, articleId]);
    }
  }

  if (articles.length === 0) {
    return (
      <p className="helper-text">
        No articles are available for references yet.
      </p>
    );
  }

  return (
    <div className="references-box">
      {articles.map((article) => (
        <label key={article.id} className="reference-option">
          <input
            type="checkbox"
            checked={selectedIds.includes(article.id)}
            onChange={() => handleToggle(article.id)}
          />
          <span>{article.title}</span>
        </label>
      ))}
    </div>
  );
}

export default ReferencesSelector;