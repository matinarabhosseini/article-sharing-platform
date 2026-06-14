import { Link } from "react-router-dom";

function Navbar() {
  return (
    <header className="navbar">
      <Link to="/" className="brand">
        Article Sharing
      </Link>

      <nav>
        <Link to="/">Articles</Link>
        <Link to="/articles/new" className="new-button">
          New Article
        </Link>
      </nav>
    </header>
  );
}

export default Navbar;