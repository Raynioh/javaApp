package models;

import java.sql.*;
import java.util.*;

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

    public void saveUserArticles2DB(int userID, int articleID, int quantity) throws SQLException {
        if(con != null) {
            PreparedStatement userCheck = con.prepareStatement("select count(*) as count from users where userId = ?");
            PreparedStatement articleCheck = con.prepareStatement("select count(*) as count from articles where articleId = ?");

            userCheck.setInt(1, userID);
            articleCheck.setInt(1, articleID);

            ResultSet userNum = userCheck.executeQuery();
            ResultSet articleNum = articleCheck.executeQuery();

            userNum.next();
            articleNum.next();

            if(userNum.getInt(1) == 0 || articleNum.getInt(1) == 0){
                return;
            }

            String selSql = "select articleId, number from buying where userId = ?";
            String insSql = "insert into buying (number, userId, articleId) values (?,?,?)";
            String updSql = "update buying set number = ? where userId = ? and articleId = ?";

            PreparedStatement selStm = con.prepareStatement(selSql);
            PreparedStatement insrStm = con.prepareStatement(insSql);
            PreparedStatement updStm = con.prepareStatement(updSql);

            selStm.setInt(1, userID);
            ResultSet result = selStm.executeQuery();

            int oldQuantity = 0;
            List<Integer> list = new ArrayList<>();
            while(result.next()){
                if(result.getInt(1) == articleID) {
                    oldQuantity = result.getInt(2);
                }
                list.add(result.getInt(1));
            }

            int col = 1;
            if(!list.contains(articleID)) {
                //insert

                insrStm.setInt(1, quantity);
                insrStm.setInt(2, userID);
                insrStm.setInt(3, articleID);

                insrStm.executeUpdate();
            } else {
                //update

                updStm.setInt(1, quantity + oldQuantity);
                updStm.setInt(2, userID);
                updStm.setInt(3, articleID);

                updStm.executeUpdate();
            }
        }
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

    public Map<Article, Integer> loadUserArticlesFromDB(int userID) throws SQLException {
        if(con != null) {
            String sql = "select number, articleId from buying where userId = ?";
            PreparedStatement sqlStm = con.prepareStatement(sql);
            sqlStm.setInt(1, userID);
            ResultSet result = sqlStm.executeQuery();

            Map<Integer, Integer> articles = new HashMap<>();
            while(result.next()) {
                articles.put(result.getInt(2), result.getInt(1));
            }

            Map<Article, Integer> ret = new HashMap<>();
            for(Map.Entry<Integer, Integer> e: articles.entrySet()) {
                String q = "select * from articles where articleId = ?";
                PreparedStatement artStm = con.prepareStatement(q);

                artStm.setInt(1, e.getKey());
                ResultSet r = artStm.executeQuery();
                r.next();

                int articleId = r.getInt(1);
                String articleName = r.getString(2);
                float price = r.getFloat(3);
                String pictPath = r.getString(4);
                Article article = new Article(articleId, articleName, price, pictPath);

                ret.put(article, e.getValue());
            }

            return ret;
        }

        return null;
    }

    public void loadArticlesFromDB() throws SQLException {
        if(con != null) {
            System.out.println("Loading from DB...");
            String slctSQL = "select articleId, articleName, price, pictPath from articles";
            PreparedStatement slcStm = con.prepareStatement(slctSQL);

            ResultSet slcResult = slcStm.executeQuery();

            while(slcResult.next()) {
                int articleId = slcResult.getInt(1);
                String articleName = slcResult.getString(2);
                float price = slcResult.getFloat(3);
                String pictPath = slcResult.getString(4);
                Article article = new Article(articleId, articleName, price, pictPath);
                articles.add(article);
            }

            slcResult.close();
            slcStm.close();
        }
    }
}