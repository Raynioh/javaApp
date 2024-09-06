package testDB;

import models.DataBase;

import java.sql.*;
import java.util.Enumeration;


public class TestDB {
        public static void main(String[] args) {

            DataBase db = new DataBase();

            try {
                db.connect();
            } catch (SQLException e1) {
                // TODO Auto-generated catch block
                e1.printStackTrace();
            }

            try {
                db.disconnect();
            } catch (SQLException e) {
                // TODO Auto-generated catch block
                e.printStackTrace();
            }

        }
    }