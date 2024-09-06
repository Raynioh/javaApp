package models;

import java.sql.*;
import java.util.LinkedList;
import java.util.List;

public class DataBase {

    private List<User> users;
    private List<Article> articles;

    private Connection con;


    public DataBase() {
        users = new LinkedList<User>();
        articles = new LinkedList<Article>();
    }

    public User getUserByUsername(String username) {
        return null;
    }

    public User getUserByID(int userID) {
        return null;
    }

    public List<User> getUsers() {
        return users;
    }

    public List<Article> getArticles() {
        return articles;
    }

    public User createUser(User user) {
        return null;
    }

    public void saveUser(User user) {

    }

    public void connect() throws SQLException {
        System.out.println("Connecting to database...");
        try {
            // load driver
            Class.forName("com.mysql.cj.jdbc.Driver");
            // obtain connection
            String url = "jdbc:mysql://localhost:3306/java_app";
            String user = "raynioh"; // your username
            String password = "23211521"; // your password
            con = DriverManager.getConnection(url, user, password);
            System.out.println("Connected to -> " + con.toString());
        } catch (ClassNotFoundException e) {
            System.out.println("Could not load driver!!!");
        }
    }

    public void disconnect() throws SQLException {
        con.close();
        System.out.println("Disconnected from DB...");
    }

    public void save2DB() throws SQLException {
        if(con != null) {
            // SQL queries
            String cntSql = "select count(*) as count from newProgrammerTable where id = ?";

            // statements
            PreparedStatement cntStm = con.prepareStatement(cntSql);


            // checking all programmers
            for (User prg : users) {
                int id = prg.getUserId(); // for now we only need this


                cntStm.setInt(1, id);
                ResultSet result = cntStm.executeQuery();
                result.next();

                int cnt = result.getInt(1);
                System.out.println("Cnt -> " + cnt);

                if (cnt == 0) {
                    System.out.println("Inserting new programmer -> " + id);
                    // insert commands


                } else {
                    System.out.println("Updating programmer -> " + id);
                    // update commands

                }
            }
            // closing I/O streams
            cntStm.close();
        }
    }

    public void loadFromDB() throws SQLException {
        if(con != null) {
            System.out.println("Loading from DB...");
            String slctSQL = "select id, name, surname, programming, experience, workingTime from newProgrammerTable order by name ";
            PreparedStatement slcStm = con.prepareStatement(slctSQL);

            ResultSet slcResult = slcStm.executeQuery();

            while(slcResult.next()) {
                int id = slcResult.getInt(1);
                String name = slcResult.getString(2);
                String sname = slcResult.getString(3);
                String time = slcResult.getString(6);
            }

            slcResult.close();
            slcStm.close();
        }
    }

}