package controllers;

import models.Article;
import models.DataBase;

import java.sql.SQLException;
import java.util.Map;

public class CartController {

    private DataBase db = new DataBase();


    public CartController() {
        try {
            db.connect();
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    public Map<Article, Integer> getUserArticles(int userID) {
        try {
            return db.loadUserArticlesFromDB(userID);
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return null;
    }

}
