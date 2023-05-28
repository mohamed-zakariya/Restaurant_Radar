package com.example.zmrs_project;


import java.sql.*;
import java.util.ArrayList;
import java.util.Hashtable;

public class MyJDBC {
    private static MyJDBC jdbc;
    static Connection connection = null;
    String url = "jdbc:mysql://localhost:3306/login";
    String user = "root";
    String password = "Mhdzikoo@123";
    MyJDBC()throws SQLException{
        try {
            Class.forName("com.mysql.cj.jdbc.Driver");
            connection = DriverManager.getConnection(url, user, password);
        } catch (ClassNotFoundException ex) {
            System.out.println("Database Connection Creation Failed : " + ex.getMessage());
        }
        catch (SQLException e){
            System.out.println("not connected + "+ e.getMessage());
        }
    }
    public static MyJDBC getInstance() throws SQLException, ClassNotFoundException {
        if(jdbc == null){
            jdbc = new MyJDBC();
        } else if (jdbc.getConnection().isClosed()) {
            jdbc = new MyJDBC();
        }
        return jdbc;
    }
    public static Connection getConnection() throws SQLException{

        return connection;
    }

    public void insertUser(String username, String password, String email) throws SQLException {
        Connection c = null;
        PreparedStatement ps = null;

        try {
            c = this.getConnection();
            ps = c.prepareStatement("insert into user (userName, password, email, type) values (?, ?, ?, ?)");

            ps.setString(1, username);
            ps.setString(2, password);
            ps.setString(3, email);
            ps.setString(4, "1");

            ps.executeUpdate();
        }finally {
            if(ps != null){
                ps.close();
            }
            if (c != null){
                c.close();
            }
        }
    }
    public int searchUser(String username, String password){
        Connection c = null;
        try {
            c = this.getConnection();
            Statement statement = c.createStatement();
            ResultSet resultSet = statement.executeQuery("select * from user");
            while(resultSet.next()){
                if(resultSet.getString("userName").equals(username)){
                    return resultSet.getInt("type");
                }
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return 0;
    }
    public User getUsersData(String username, String password) throws SQLException {
        Connection c = null;
        String email = null;
        try {
            c = this.getConnection();
            Statement statement = c.createStatement();
            ResultSet resultSet = statement.executeQuery("select * from user");
            while(resultSet.next()){
                if(resultSet.getString("userName").equals(username)){
                     email = resultSet.getString("email");
                }
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return new User(username, password, email, this.getUserReviews(new User(username)));
    }
    public int noOfUsers() throws SQLException {
        Connection c = null;
        ResultSet rs = null;
        int noOfUsers = 0;
        try {
            c = this.getConnection();

            Statement st = c.createStatement();

            rs = st.executeQuery("SELECT COUNT(userName) FROM user WHERE type = 1");


            while(rs.next()){
                String s = rs.getString("count(userName)");
                noOfUsers = Integer.parseInt(s);
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }finally {
            if (rs != null)
                rs.close();
            if(c != null)
                c.close();
        }
        return noOfUsers;
    }
    public Location searchLocation(String location){
        Connection c = null;
        ArrayList<Restaurant> restaurants = new ArrayList<>();
        try{
            c = this.getConnection();
            Statement st = c.createStatement();
            ResultSet resultSet = st.executeQuery("select * from restaurant");
            while (resultSet.next()){
                if(resultSet.getString("location").equals(location)){
                    restaurants.add(new Restaurant(resultSet.getString("restaurantName"),
                           this.getRestaurantBranches(new Restaurant(resultSet.getString("restaurantName"))),
                            resultSet.getString("cusine"),
                            this.getRestaurantReviews(new Restaurant(resultSet.getString("restaurantName")))));
                }
            }

        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
        return new Location(location, restaurants);
    }
    public ArrayList<Review> getUserReviews(User user){
        Connection c = null;
        ArrayList<Review> reviews = new ArrayList<>();
        try{
            c = this.getConnection();
            Statement st = c.createStatement();
            ResultSet resultSet = st.executeQuery("select * from review");
            while (resultSet.next()){
                if(resultSet.getString("userName").equals(user.getUsername()))
                    reviews.add(new Review(user, resultSet.getDouble("rate"),
                            new Restaurant(resultSet.getString("restaurantName"))));
            }
            return reviews;
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
    }
    public boolean addUserReview(String userName, String restaurantName, double rate) throws SQLException {
        Connection c = null;
        PreparedStatement ps = null;
        try {
            c = this.getConnection();
            Statement st = c.createStatement();
            ResultSet resultSet1 = st.executeQuery("select * from review");
            while(resultSet1.next()){
                if(resultSet1.getString("userName").equals(userName)
                        && resultSet1.getString("restaurantName").equals(restaurantName))
                    return false;
            }
            ps = c.prepareStatement("insert into review (userName, restaurantName, rate) values (?, ?, ?)");

            ps.setString(1, userName);
            ps.setString(2, restaurantName);
            ps.setDouble(3, rate);

            ps.executeUpdate();
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
        finally {
            if (ps != null){
                ps.close();
            }
            if (c != null){
                c.close();
            }
        }
        return true;
    }
    public ArrayList<Review> getRestaurantReviews(Restaurant restaurant){
        Connection c = null;
        ArrayList<Review> reviews = new ArrayList<>();
        try{
            c = this.getConnection();
            Statement st = c.createStatement();
            ResultSet resultSet = st.executeQuery("select * from review");
            while (resultSet.next()){
                if(resultSet.getString("restaurantName").equals(restaurant.getRestaurantName()))
                    reviews.add(new Review(new User(resultSet.getString("userName")),
                            resultSet.getDouble("rate"),
                            new Restaurant(resultSet.getString("restaurantName"))));
            }
            return reviews;
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
    }
    public ArrayList<Restaurant> getCusineRestaurant(String cusine, String location){
        Connection c = null;
        Statement st = null;

        ArrayList<Restaurant> restaurants = new ArrayList<>();
        try {
            c = this.getConnection();
            st = c.createStatement();
            ResultSet resultSet = st.executeQuery("select * from restaurant");
            while(resultSet.next()){
                if(resultSet.getString("location").equals(location) && resultSet.getString("cusine").equals(cusine)){
                    restaurants.add(new Restaurant(resultSet.getString("restaurantName"),
                            resultSet.getString("cusine")));
                }
            }
            return restaurants;
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
    }
    public ArrayList<Restaurant> getCusinenooRestaurant(String cusine){
        Connection c = null;
        Statement st = null;

        ArrayList<Restaurant> restaurants = new ArrayList<>();
        try {
            c = this.getConnection();
            st = c.createStatement();
            ResultSet resultSet = st.executeQuery("select * from restaurant");
            while(resultSet.next()){
                if(resultSet.getString("cusine").equals(cusine)){
                    restaurants.add(new Restaurant(resultSet.getString("restaurantName"),
                            this.getRestaurantBranches(new Restaurant(resultSet.getString("restaurantName"))),
                            resultSet.getString("cusine")));
                }
            }
            return restaurants;
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
    }
    public ArrayList<String> getRestaurantBranches(Restaurant restaurant){
        Connection c = null;
        Statement st = null;
        ArrayList<String> branches = new ArrayList<>();
        try {
            c = this.getConnection();
            st = c.createStatement();
            ResultSet resultSet = st.executeQuery("select * from restaurant");
            while (resultSet.next()){
                if(resultSet.getString("restaurantName").equals(restaurant.getRestaurantName())){
                    branches.add(resultSet.getString("location"));
                }
            }
            return branches;
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
    }

}
