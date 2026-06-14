package ir.ac.ut.ece.ie.webserverproject.util;

public class HtmlUtil {

    public static String pageStart(String title) {
        return "<!DOCTYPE html>"
                + "<html>"
                + "<head>"
                + "<meta charset='UTF-8'>"
                + "<title>" + title + "</title>"
                + "<style>"
                + "body{font-family:Tahoma,Arial,sans-serif;background:#f4f6f8;margin:0;padding:0;direction:ltr;}"
                + ".container{width:900px;margin:30px auto;background:white;padding:30px;border-radius:12px;box-shadow:0 2px 10px rgba(0,0,0,0.08);}"
                + ".navbar{margin-bottom:25px;padding-bottom:15px;border-bottom:1px solid #ddd;}"
                + ".navbar a{margin-right:15px;text-decoration:none;color:#1f4e79;font-weight:bold;}"
                + "h1,h2,h3{color:#1f2d3d;}"
                + ".card{border:1px solid #d9e2ec;border-radius:10px;padding:18px;margin-bottom:16px;background:#fafbfc;}"
                + ".muted{color:#6b7785;font-size:14px;}"
                + ".btn{display:inline-block;background:#1f4e79;color:white;padding:10px 16px;border-radius:8px;text-decoration:none;border:none;cursor:pointer;}"
                + ".btn:hover{background:#173a5b;}"
                + "input[type=text], textarea{width:100%;padding:10px;margin-top:8px;margin-bottom:16px;border:1px solid #cbd2d9;border-radius:8px;font-size:14px;box-sizing:border-box;}"
                + "label{font-weight:bold;color:#243b53;}"
                + ".success{padding:12px;background:#e6ffed;border:1px solid #b7ebc6;border-radius:8px;color:#17663a;margin-bottom:15px;}"
                + ".error{padding:12px;background:#fff1f0;border:1px solid #f5c2c0;border-radius:8px;color:#a61b1b;margin-bottom:15px;}"
                + ".search-box{margin-bottom:20px;}"
                + ".article-link{text-decoration:none;color:#102a43;font-size:20px;font-weight:bold;}"
                + ".article-link:hover{color:#1f4e79;}"
                + "</style>"
                + "</head>"
                + "<body>"
                + "<div class='container'>"
                + "<div class='navbar'>"
                + "<a href='/home'>Home</a>"
                + "<a href='/add'>Add Article</a>"
                + "</div>";
    }

    public static String pageEnd() {
        return "</div></body></html>";
    }

    public static String escape(String text) {
        if (text == null) return "";
        return text.replace("&", "&amp;")
                .replace("<", "&lt;")
                .replace(">", "&gt;");
    }




}