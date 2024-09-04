package models;

import java.util.List;
import java.util.Map;

public class User {

    int userId;
    String username;
    String password;
    String email;
    String address;
    Map<Article, Integer> articles;


    public User(int userId, String username, String password, String email, String address) {
        this.userId = userId;
        this.username = username;
        this.password = password;
        this.email = email;
        this.address = address;
    }

    public User(int userId, String username, String password, String email, String address, Map<Article, Integer> articles) {
        this.userId = userId;
        this.username = username;
        this.password = password;
        this.email = email;
        this.address = address;
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

    public Map<Article, Integer> getArticles() {
        return articles;
    }
}
