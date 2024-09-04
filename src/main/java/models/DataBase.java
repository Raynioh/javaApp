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

}