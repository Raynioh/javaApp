package controllers;

import models.Article;
import models.DataBase;
import models.User;

import java.sql.SQLException;
import java.util.List;
import java.util.Map;

public class AdminController {

    private DataBase db = new DataBase();


    public AdminController() {
        try {
            db.connect();
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    public List<User> getUsers() {
        List<User> users = null;

        try {
            db.loadAllUsersFromDB();
            users = db.getUsers();

            for(User u: users) {
                Map<Article, Integer> temp = db.loadUserArticlesFromDB(u.getUserId());
                u.setArticles(temp);
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }

        return users;
    }
}
