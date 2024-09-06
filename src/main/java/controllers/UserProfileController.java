package controllers;

import models.Article;
import models.DataBase;
import models.User;

import java.sql.SQLException;

public class UserProfileController {

    private DataBase db = new DataBase();


    public UserProfileController() {
        try {
            db.connect();
        } catch (SQLException e) {
            e.printStackTrace();
        }    }

    public void changeUserInfo(int userID, String username, String email, String address) {

        User user = null;
        try {
            user = db.loadUserByIDFromDB(userID);
        } catch (SQLException e) {
            e.printStackTrace();
        }

        if(user == null){
            return;
        }

        User newUser = new User(userID, username, user.getPassword(), email, address, user.getAdminStatus(), user.getArticles());

        db.addUser(newUser);
        try {
            db.saveUsers2DB();
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    public User getUser(int userID) {
        try {
            return db.loadUserByIDFromDB(userID);
        } catch (SQLException e) {
            e.printStackTrace();
        }

        return null;
    }

}
