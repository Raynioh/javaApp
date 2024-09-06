package models;

import java.sql.*;
import java.util.ArrayList;
import java.util.LinkedList;
import java.util.List;
import java.util.Locale;

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

    public void addUser(User user) {
        users.add(user);
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

    public int createNewID(String table, String val) throws SQLException {
        if(con != null) {
            PreparedStatement stm = con.prepareStatement("select " + val + " from " + table);
            ResultSet r = stm.executeQuery();

            int max = 0;
            while(r.next()) {
                int id = r.getInt(1);
                if(id > max) {
                    max = id;
                }
            }
            max++;

            stm.close();
            return max;
        }

        return -1;
    }

    public void saveUsers2DB() throws SQLException {
        if(con != null) {
            // SQL queries
            String cntSql = "select count(*) as count from users where userId = ?";
            String insSql = "insert into users (userId, username, password, email, address, admin) values (?,?,?,?,?,?)";
            String updSql = "update users set username = ?, password = ?, email = ?, address = ?, admin = ? where userId = ?";

            // statements
            PreparedStatement cntStm = con.prepareStatement(cntSql);
            PreparedStatement insrStm = con.prepareStatement(insSql);
            PreparedStatement updStm = con.prepareStatement(updSql);

            // checking all users
            for (User u : users) {
                int id = u.getUserId(); // for now we only need this

                cntStm.setInt(1, id);
                ResultSet result = cntStm.executeQuery();
                result.next();

                int cnt = result.getInt(1);
                System.out.println("Cnt -> " + cnt);

                if (cnt == 0) {
                    System.out.println("Inserting new programmer -> " + id);
                    // insert commands
                    int col = 1;
                    insrStm.setInt(col++, u.getUserId());
                    insrStm.setString(col++, u.getUsername());
                    insrStm.setString(col++, u.getPassword());
                    insrStm.setString(col++, u.getEmail());
                    insrStm.setString(col++, u.getAddress());
                    insrStm.setString(col++, String.valueOf(u.getAdminStatus()));

                    insrStm.executeUpdate();
                } else {
                    System.out.println("Updating programmer -> " + id);
                    // update commands
                    int col = 1;
                    updStm.setString(col++, u.getUsername());
                    updStm.setString(col++, u.getPassword());
                    updStm.setString(col++, u.getEmail());
                    updStm.setString(col++, u.getAddress());
                    updStm.setString(col++, String.valueOf(u.getAdminStatus()));
                    updStm.setInt(col++, id);

                    updStm.executeUpdate();
                }
            }
            // closing I/O streams
            cntStm.close();
            insrStm.close();
            updStm.close();
        }
    }

    public void loadAllUsersFromDB() throws SQLException {
        if(con != null) {
            System.out.println("Loading from DB...");
            String slctSQL = "select userId, username, password, email, address, admin from users order by username";
            PreparedStatement slcStm = con.prepareStatement(slctSQL);

            ResultSet slcResult = slcStm.executeQuery();

            while(slcResult.next()) {
                int userId = slcResult.getInt(1);
                String username = slcResult.getString(2);
                String password = slcResult.getString(3);
                String email = slcResult.getString(4);
                String address = slcResult.getString(5);
                boolean admin = Boolean.getBoolean(slcResult.getString(6));
                User user = new User(userId, username, password, email, address, admin);
                users.add(user);
            }

            slcResult.close();
            slcStm.close();
        }
    }

    public User loadUserByUsernameFromDB(String username) throws SQLException {
        if(con != null) {
            System.out.println("Loading user from DB...");
            String slctSQL = "select userId, username, password, email, address, admin from users where username = ?";
            PreparedStatement slcStm = con.prepareStatement(slctSQL);
            slcStm.setString(1, username);

            ResultSet slcResult = slcStm.executeQuery();

            if(!slcResult.next()) {
                return null;
            }

            int userId = slcResult.getInt(1);
            String usernm = slcResult.getString(2);
            String password = slcResult.getString(3);
            String email = slcResult.getString(4);
            String address = slcResult.getString(5);
            boolean admin = Boolean.getBoolean(slcResult.getString(6));
            User user = new User(userId, usernm, password, email, address, admin);

            slcResult.close();
            slcStm.close();

            return user;
        }

        return null;
    }

    public User loadUserByIDFromDB(int id) throws SQLException {
        if(con != null) {
            System.out.println("Loading user from DB...");
            String slctSQL = "select userId, username, password, email, address, admin from users where userId = ?";
            PreparedStatement slcStm = con.prepareStatement(slctSQL);
            slcStm.setInt(1, id);

            ResultSet slcResult = slcStm.executeQuery();

            if(!slcResult.next()) {
                return null;
            }

            int userId = slcResult.getInt(1);
            String username = slcResult.getString(2);
            String password = slcResult.getString(3);
            String email = slcResult.getString(4);
            String address = slcResult.getString(5);
            boolean admin = Boolean.getBoolean(slcResult.getString(6));
            User user = new User(userId, username, password, email, address, admin);

            slcResult.close();
            slcStm.close();

            return user;
        }

        return null;
    }
}