package ir.ac.ut.ece.ie.webserverproject.server;

import java.util.HashMap;
import java.util.Map;

public class HttpRequest {
    private String method;
    private String path;
    private Map<String, String> params;

    public HttpRequest() {
        params = new HashMap<>();
    }

    public String getMethod() {
        return method;
    }

    public void setMethod(String method) {
        this.method = method;
    }

    public String getPath() {
        return path;
    }

    public void setPath(String path) {
        this.path = path;
    }

    public Map<String, String> getParams() {
        return params;
    }

    public void addParam(String key, String value) {
        params.put(key, value);
    }

    public String getParam(String key) {
        return params.get(key);
    }
}