import { Routes, Route } from "react-router-dom";
import { ToastContainer } from "react-toastify";
import "react-toastify/dist/ReactToastify.css";

import Navbar from "./components/Navbar";
import HomePage from "./pages/HomePage";
import ArticleDetailsPage from "./pages/ArticleDetailsPage";
import CreateArticlePage from "./pages/CreateArticlePage";

function App() {
  return (
    <>
      <Navbar />

      <main className="container">
        <Routes>
          <Route path="/" element={<HomePage />} />
          <Route path="/articles/new" element={<CreateArticlePage />} />
          <Route path="/articles/:id" element={<ArticleDetailsPage />} />
        </Routes>
      </main>

      <ToastContainer position="top-right" />
    </>
  );
}

export default App;