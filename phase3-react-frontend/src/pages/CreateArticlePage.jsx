import ArticleForm from "../components/ArticleForm";

function CreateArticlePage() {
  return (
    <section>
      <div className="page-header">
        <h1>Create Article</h1>
        <p>Add a new article to the platform.</p>
      </div>

      <ArticleForm />
    </section>
  );
}

export default CreateArticlePage;