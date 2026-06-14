package ir.ac.ut.ece.ie.webserverproject.util;

import ir.ac.ut.ece.ie.webserverproject.server.HttpRequest;

import java.io.BufferedReader;
import java.io.IOException;
import java.net.URLDecoder;
import java.nio.charset.StandardCharsets;
import java.util.StringTokenizer;

public class RequestUtil {

    public static HttpRequest parseRequest(BufferedReader reader) throws IOException {
        HttpRequest request = new HttpRequest();

        String firstLine = reader.readLine();
        if (firstLine == null || firstLine.trim().isEmpty()) {
            return null;
        }

        System.out.println("First request line: " + firstLine);

        StringTokenizer tokenizer = new StringTokenizer(firstLine, " ");
        if (tokenizer.countTokens() < 2) {
            return null;
        }

        String method = tokenizer.nextToken();
        String fullPath = tokenizer.nextToken();

        request.setMethod(method);

        if (fullPath.contains("?")) {
            String path = fullPath.substring(0, fullPath.indexOf("?"));
            String query = fullPath.substring(fullPath.indexOf("?") + 1);
            request.setPath(path);
            parseQueryString(query, request);
        } else {
            request.setPath(fullPath);
        }

        String line;
        int contentLength = 0;

        while ((line = reader.readLine()) != null && !line.isEmpty()) {
            if (line.startsWith("Content-Length:")) {
                contentLength = Integer.parseInt(
                        line.substring("Content-Length:".length()).trim()
                );
            }
        }

        if ("POST".equalsIgnoreCase(method) && contentLength > 0) {
            char[] bodyChars = new char[contentLength];
            int readCount = reader.read(bodyChars);

            if (readCount > 0) {
                String body = new String(bodyChars, 0, readCount);
                parseQueryString(body, request);
            }
        }

        return request;
    }

    private static void parseQueryString(String query, HttpRequest request) {
        if (query == null || query.trim().isEmpty()) {
            return;
        }

        String[] pairs = query.split("&");
        for (String pair : pairs) {
            if (pair.contains("=")) {
                String[] keyValue = pair.split("=", 2);
                String key = decode(keyValue[0]);
                String value = decode(keyValue[1]);
                request.addParam(key, value);
            }
        }
    }

    private static String decode(String value) {
        return URLDecoder.decode(value, StandardCharsets.UTF_8);
    }

    public static String mapPathToPageClass(String path) {
        if (path == null) {
            return null;
        }

        if (path.equals("/") || path.equals("/home")) {
            return "HomePage";
        }

        if (path.equals("/article")) {
            return "ViewArticlePage";
        }

        if (path.equals("/add")) {
            return "AddArticlePage";
        }

        return null;
    }
}