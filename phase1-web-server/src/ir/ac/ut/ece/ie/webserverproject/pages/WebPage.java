package ir.ac.ut.ece.ie.webserverproject.pages;

import ir.ac.ut.ece.ie.webserverproject.server.HttpRequest;

public interface WebPage {
    String render(HttpRequest request);
}