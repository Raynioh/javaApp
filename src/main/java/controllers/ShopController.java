package controllers;

import models.Article;
import models.DataBase;
import models.User;

import java.sql.SQLException;

public class ShopController {

    private DataBase db = new DataBase();


    public ShopController() {
        this.db = db;
    }

    public void addArticles(int userID, Article article, int quantity) {
        User user = db.getUserByID(userID);

        user.addArticles(article, quantity);

        db.addUser(user);
        try {
            db.saveUsers2DB();
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }
}
