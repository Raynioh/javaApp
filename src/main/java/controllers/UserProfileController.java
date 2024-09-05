package controllers;

import models.Article;
import models.DataBase;
import models.User;

public class UserProfileController {

    private DataBase db = new DataBase();


    public UserProfileController() {

    }

    public void changeUserInfo(int userID, String username, String email, String address) {

        User user = db.getUserByID(userID);

        User newUser = new User(userID, user.getPassword(), username, email, address, user.getAdminStatus(), user.getArticles());

        db.saveUser(newUser);
    }

    public User getUser(int userID) {
        return db.getUserByID(userID);
    }

}
