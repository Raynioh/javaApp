package controllers;

import models.Article;
import models.DataBase;
import models.User;

import java.sql.SQLException;

public class UserProfileController {

    private DataBase db = new DataBase();


    public UserProfileController() {
        this.db = db;
    }

    public void changeUserInfo(int userID, String username, String email, String address) {

        User user = db.getUserByID(userID);

        User newUser = new User(userID, user.getPassword(), username, email, address, user.getAdminStatus(), user.getArticles());

        db.addUser(newUser);
        try {
            db.saveUsers2DB();
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    public User getUser(int userID) {
        return db.getUserByID(userID);
    }

}
