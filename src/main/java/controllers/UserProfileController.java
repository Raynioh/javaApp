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

    public boolean changeUserInfo(int userID, String username, String email, String address) {
        User user = null;

        try {
            User temp = db.loadUserByUsernameFromDB(username);
            user = db.loadUserByIDFromDB(userID);

            if(temp != null && !username.equals(user.getUsername())) {
                return false;
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }

        if(user == null){
            return false;
        }

        User newUser = new User(userID, username, user.getPassword(), email, address, user.getAdminStatus(), user.getArticles());

        db.addUser(newUser);
        try {
            db.saveUsers2DB();
        } catch (SQLException e) {
            e.printStackTrace();
            return false;
        }

        return true;
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
