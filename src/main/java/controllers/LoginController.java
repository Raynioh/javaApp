package controllers;

import models.DataBase;
import models.User;

public class LoginController {

    private DataBase db = new DataBase();

    public LoginController() {

    }

    public User getUser(String username, String password) {
        User user = db.getUser(username);

        if(user == null || !password.equals(user.getPassword())){
            //krivi password ili ne postoji
            return null;
        } else {
            return user;
        }
    }

    public boolean createProfile(String username, String password, String email, String address) {
        if(db.getUser(username) != null){
            return false;
        }

        //finding new ID
        int id = db.getUsers().stream().mapToInt(value -> value.getUserId()).max().orElse(0) + 1;

        User user = new User(id, username, password, email, address);

        db.createUser(user);
        return true;
    }
}
