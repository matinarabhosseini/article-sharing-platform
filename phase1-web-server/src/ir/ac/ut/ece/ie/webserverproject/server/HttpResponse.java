package ir.ac.ut.ece.ie.webserverproject.server;

public class HttpResponse {
    public static byte[] ok(String html) {
        String header = "HTTP/1.1 200 OK\r\n"
                + "Content-Type: text/html; charset=UTF-8\r\n"
                + "Content-Length: " + html.getBytes().length + "\r\n"
                + "\r\n";
        return (header + html).getBytes();
    }

    public static byte[] notFound() {
        String html = "<html><body><h1>404 Page Not Found</h1></body></html>";
        String header = "HTTP/1.1 404 Not Found\r\n"
                + "Content-Type: text/html; charset=UTF-8\r\n"
                + "Content-Length: " + html.getBytes().length + "\r\n"
                + "\r\n";
        return (header + html).getBytes();
    }

    public static byte[] badRequest(String message) {
        String html = "<html><body><h1>400 Bad Request</h1><p>" + message + "</p></body></html>";
        String header = "HTTP/1.1 400 Bad Request\r\n"
                + "Content-Type: text/html; charset=UTF-8\r\n"
                + "Content-Length: " + html.getBytes().length + "\r\n"
                + "\r\n";
        return (header + html).getBytes();
    }
}