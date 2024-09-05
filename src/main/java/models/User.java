package models;

import java.util.List;
import java.util.Map;

public class User {

    int userId;
    String username;
    String password;
    String email;
    String address;
    boolean admin;
    Map<Article, Integer> articles;


    public User(int userId, String username, String password, String email, String address, boolean admin) {
        this.userId = userId;
        this.username = username;
        this.password = password;
        this.email = email;
        this.address = address;
        this.admin = admin;
    }

    public User(int userId, String username, String password, String email, String address, boolean admin, Map<Article, Integer> articles) {
        this.userId = userId;
        this.username = username;
        this.password = password;
        this.email = email;
        this.address = address;
        this.admin = admin;
        this.articles = articles;
    }

    public int getUserId() {
        return userId;
    }

    public String getUsername() {
        return username;
    }

    public String getPassword() {
        return password;
    }

    public String getEmail() {
        return email;
    }

    public String getAddress() {
        return address;
    }

    public boolean getAdminStatus() {
        return admin;
    }

    public Map<Article, Integer> getArticles() {
        return articles;
    }

    public void addArticles(Article article, int quantity) {
        if(articles.containsKey(article)) {
            articles.replace(article, quantity + articles.get(article));
        } else {
            articles.put(article, quantity);
        }
    }
}
