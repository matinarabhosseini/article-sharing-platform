import { useEffect, useState } from "react";
import { getArticles } from "../api/articles";
import ArticleList from "../components/ArticleList";
import ErrorMessage from "../components/ErrorMessage";
import Loading from "../components/Loading";
import SearchBar from "../components/SearchBar";

function HomePage() {
  const [articles, setArticles] = useState([]);
  const [query, setQuery] = useState("");
  const [loading, setLoading] = useState(true);
  const [error, setError] = useState("");

  useEffect(() => {
    async function loadArticles() {
      try {
        setLoading(true);
        setError("");

        const data = await getArticles({ query });
        setArticles(data);
      } catch (err) {
        setError(err.message);
      } finally {
        setLoading(false);
      }
    }

    loadArticles();
  }, [query]);

  function handleSearch(searchValue) {
    setQuery(searchValue);
  }

  return (
    <section>
      <div className="page-header">
        <h1>Articles</h1>
        <p>Browse and search shared articles.</p>
      </div>

      <SearchBar onSearch={handleSearch} />

      {loading && <Loading message="Loading articles..." />}

      {!loading && error && <ErrorMessage message={error} />}

      {!loading && !error && <ArticleList articles={articles} />}
    </section>
  );
}

export default HomePage;