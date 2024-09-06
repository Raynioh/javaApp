package controllers;

import models.DataBase;
import models.User;

import java.sql.SQLException;

public class LoginController {

    private DataBase db = new DataBase();

    public LoginController() {
        try {
            db.connect();
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    public int getUser(String username, String password) {
        User user = null;
        try {
            user = db.loadUserByUsernameFromDB(username);
        } catch (SQLException e) {
            e.printStackTrace();
        }

        if(user == null || !password.equals(user.getPassword())){
            //krivi password ili ne postoji
            return -1;
        } else {
            return user.getUserId();
        }
    }

    public boolean createProfile(String username, String password, String email, String address, boolean admin) {
        try {
            if(db.loadUserByUsernameFromDB(username) != null){
                System.out.println("username exists");
                return false;
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }

        //finding new ID
        int id = -1;
        try {
            id = db.createNewID("users", "userId");
        } catch (SQLException e) {
            e.printStackTrace();
        }

        if(id == -1) {
            return false;
        }

        User user = new User(id, username, password, email, address, admin);

        db.addUser(user);
        try {
            db.saveUsers2DB();
            return true;
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return false;
    }
}
