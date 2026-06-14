import { useEffect, useState } from "react";
import { Link, useNavigate } from "react-router-dom";
import { toast } from "react-toastify";

import { createArticle, getArticles } from "../api/articles";
import ReferencesSelector from "./ReferencesSelector";

function ArticleForm() {
  const navigate = useNavigate();

  const [formData, setFormData] = useState({
    title: "",
    abstractText: "",
    body: "",
    referenceIds: [],
  });

  const [availableArticles, setAvailableArticles] = useState([]);
  const [submitting, setSubmitting] = useState(false);
  const [loadingReferences, setLoadingReferences] = useState(true);

  useEffect(() => {
    async function loadArticles() {
      try {
        setLoadingReferences(true);
        const articles = await getArticles();
        setAvailableArticles(articles);
      } catch (error) {
        toast.error(error.message);
      } finally {
        setLoadingReferences(false);
      }
    }

    loadArticles();
  }, []);

  function handleChange(event) {
    const { name, value } = event.target;

    setFormData((currentData) => ({
      ...currentData,
      [name]: value,
    }));
  }

  function handleReferencesChange(referenceIds) {
    setFormData((currentData) => ({
      ...currentData,
      referenceIds,
    }));
  }

  async function handleSubmit(event) {
    event.preventDefault();

    const articleToCreate = {
      title: formData.title.trim(),
      abstractText: formData.abstractText.trim(),
      body: formData.body.trim(),
      referenceIds: formData.referenceIds,
    };

    if (
      !articleToCreate.title ||
      !articleToCreate.abstractText ||
      !articleToCreate.body
    ) {
      toast.error("Please fill in all required fields.");
      return;
    }

    try {
      setSubmitting(true);

      const createdArticle = await createArticle(articleToCreate);

      toast.success("Article created successfully.");
      navigate(`/articles/${createdArticle.id}`);
    } catch (error) {
      toast.error(error.message);
    } finally {
      setSubmitting(false);
    }
  }

  return (
    <form className="article-form" onSubmit={handleSubmit}>
      <div className="form-group">
        <label htmlFor="title">Title</label>
        <input
          id="title"
          name="title"
          type="text"
          value={formData.title}
          onChange={handleChange}
          placeholder="Enter article title"
        />
      </div>

      <div className="form-group">
        <label htmlFor="abstractText">Abstract</label>
        <textarea
          id="abstractText"
          name="abstractText"
          value={formData.abstractText}
          onChange={handleChange}
          placeholder="Write a short abstract"
          rows="4"
        />
      </div>

      <div className="form-group">
        <label htmlFor="body">Body</label>
        <textarea
          id="body"
          name="body"
          value={formData.body}
          onChange={handleChange}
          placeholder="Write the full article body"
          rows="8"
        />
      </div>

      <div className="form-group">
        <label>References</label>

        {loadingReferences ? (
          <p className="helper-text">Loading references...</p>
        ) : (
          <ReferencesSelector
            articles={availableArticles}
            selectedIds={formData.referenceIds}
            onChange={handleReferencesChange}
          />
        )}
      </div>

      <div className="form-actions">
        <Link to="/" className="cancel-button">
            Cancel
        </Link>
        <button type="submit" disabled={submitting}>
          {submitting ? "Creating..." : "Create Article"}
        </button>
      </div>
    </form>
  );
}

export default ArticleForm;