import { useState } from "react";

function SearchBar({ onSearch }) {
  const [value, setValue] = useState("");

  function handleSubmit(event) {
    event.preventDefault();
    onSearch(value);
  }

  function handleClear() {
    setValue("");
    onSearch("");
  }

  return (
    <form className="search-bar" onSubmit={handleSubmit}>
      <input
        type="text"
        placeholder="Search by title or abstract..."
        value={value}
        onChange={(event) => setValue(event.target.value)}
      />

      <button type="submit">Search</button>

      {value && (
        <button type="button" className="secondary-button" onClick={handleClear}>
          Clear
        </button>
      )}
    </form>
  );
}

export default SearchBar;