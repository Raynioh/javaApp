package models;

import java.util.LinkedList;
import java.util.List;

public class DataBase {

    private List<User> users;
    private List<Article> articles;


    public DataBase() {
        users = new LinkedList<User>();
        articles = new LinkedList<Article>();
    }

    public User getUserByUsername(String username) {
        return null;
    }

    public User getUserByID(int userID) {
        return null;
    }

    public List<User> getUsers() {
        return users;
    }

    public List<Article> getArticles() {
        return articles;
    }

    public User createUser(User user) {
        return null;
    }

    public void saveUser(User user) {

    }

}