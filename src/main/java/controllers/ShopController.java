package controllers;

import models.Article;
import models.DataBase;
import models.User;

import java.sql.SQLException;
import java.util.List;

public class ShopController {

    private DataBase db = new DataBase();


    public ShopController() {
        try {
            db.connect();
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    public List<Article> getArticles() {
        try {
            db.loadArticlesFromDB();
        } catch (SQLException e) {
            e.printStackTrace();
        }

        return db.getArticles();
    }

    public void addArticles(int userID, Article article, int quantity) {
        User user = null;
        try {
            user = db.loadUserByIDFromDB(userID);
        } catch (SQLException e) {
            e.printStackTrace();
        }

        if(user == null){
            return;
        }

        try {
            db.saveUserArticles2DB(userID, article.getArticleId(), quantity);
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }
}
