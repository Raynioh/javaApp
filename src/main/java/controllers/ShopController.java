package controllers;

import models.Article;
import models.DataBase;
import models.User;

public class ShopController {

    private DataBase db = new DataBase();


    public ShopController() {

    }

    public void addArticles(int userID, Article article, int quantity) {
        User user = db.getUserByID(userID);

        user.addArticles(article, quantity);

        db.saveUser(user);
    }

}
