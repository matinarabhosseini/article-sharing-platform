package ir.ac.ut.ece.ie.webserverproject.server;

import ir.ac.ut.ece.ie.webserverproject.pages.WebPage;
import ir.ac.ut.ece.ie.webserverproject.util.RequestUtil;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.lang.reflect.InvocationTargetException;
import java.net.ServerSocket;
import java.net.Socket;

public class WebServer {

    public void start() throws IOException {
        ServerSocket serverSocket = new ServerSocket(9092);
        System.out.println("Server started on port 9092...");

        while (true) {
            Socket socket = serverSocket.accept();

            try {
                BufferedReader reader = new BufferedReader(
                        new InputStreamReader(socket.getInputStream())
                );

                HttpRequest request = RequestUtil.parseRequest(reader);

                if (request == null) {
                    socket.close();
                    continue;
                }

                System.out.println("Request path: " + request.getPath());

                String className = RequestUtil.mapPathToPageClass(request.getPath());
                System.out.println("Mapped class name: " + className);

                if (className == null) {
                    socket.getOutputStream().write(HttpResponse.notFound());
                    socket.getOutputStream().flush();
                    socket.close();
                    continue;
                }

                String fullClassName = "ir.ac.ut.ece.ie.webserverproject.pages." + className;
                System.out.println("Full class name: " + fullClassName);

                Class<?> c = Class.forName(fullClassName);
                Object pageObject = c.getDeclaredConstructor().newInstance();

                WebPage page = (WebPage) pageObject;
                String html = page.render(request);

                socket.getOutputStream().write(HttpResponse.ok(html));

            } catch (ClassNotFoundException |
                     InstantiationException |
                     IllegalAccessException |
                     InvocationTargetException |
                     NoSuchMethodException e) {

                e.printStackTrace();
                socket.getOutputStream().write(HttpResponse.notFound());

            } catch (Exception e) {
                e.printStackTrace();
                socket.getOutputStream().write(
                        HttpResponse.badRequest("Request could not be processed.")
                );
            }

            socket.getOutputStream().flush();
            socket.close();
        }
    }

    public static void main(String[] args) throws IOException {
        WebServer server = new WebServer();
        server.start();
    }
}