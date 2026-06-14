const API_BASE = "/api/articles";

async function handleResponse(response) {
  if (!response.ok) {
    const errorData = await response.json().catch(() => null);

    const message =
      errorData?.messages?.join("\n") ||
      errorData?.message ||
      "Something went wrong";

    throw new Error(message);
  }

  return response.json();
}

export async function getArticles({ query = "", sort = "time" } = {}) {
  const params = new URLSearchParams();

  if (query.trim()) {
    params.set("query", query.trim());
  } else if (sort) {
    params.set("sort", sort);
  }

  const url = params.toString()
    ? `${API_BASE}?${params.toString()}`
    : API_BASE;

  const response = await fetch(url);
  return handleResponse(response);
}

export async function getArticleById(id) {
  const response = await fetch(`${API_BASE}/${id}`);
  return handleResponse(response);
}

export async function createArticle(article) {
  const response = await fetch(API_BASE, {
    method: "POST",
    headers: {
      "Content-Type": "application/json",
    },
    body: JSON.stringify(article),
  });

  return handleResponse(response);
}